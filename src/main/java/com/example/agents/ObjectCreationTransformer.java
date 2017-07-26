package com.example.agents;

import com.example.data.DataRecorder;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import org.apache.log4j.Logger;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


public class ObjectCreationTransformer implements ClassFileTransformer{

	Logger log = Logger.getLogger(ObjectCreationTransformer.class);

	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer)
			throws IllegalClassFormatException {
		log.info("transforming " + className);

		// record class load
		DataRecorder.recordClassLoad(className);

		try {
			ClassPool pool = ClassPool.getDefault();
			CtClass cc = pool.get(className.replaceAll("/", "."));
			for(CtConstructor ctc : cc.getConstructors()){
				ctc.insertBeforeBody("System.out.println(\"constructor " + ctc.getName() +  "\")");
				ctc.insertBeforeBody("com.example.data.DataRecorder.recordObjectCreation(" + ctc.getName()+ ")");
			}

			byte[] byteCode = cc.toBytecode();
			classfileBuffer = byteCode;
		}catch (Exception e){
			// oops
		}

		return classfileBuffer;
	}

}
