package com.example.agents;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.apache.log4j.Logger;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class RequestFilterTransformer implements ClassFileTransformer {

    Logger log = Logger.getLogger(RequestFilterTransformer.class);

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
        //Modify sakai's RequestFilter.doFilter to add start/stop

        try{
            if(className.equals("org/sakaiproject/util/RequestFilter")){
                log.info("modifying request filter");
                ClassPool pool = ClassPool.getDefault();
                CtClass cc = pool.get(className.replaceAll("/", "."));
                CtMethod doMethod =  cc.getDeclaredMethod("doFilter");
                doMethod.insertBefore("com.example.data.DataRecorder.startRequest();");
                doMethod.insertAfter("com.example.data.DataRecord.endRequest();");

                byte[] byteCode = cc.toBytecode();
                classfileBuffer = byteCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classfileBuffer;
    }
}
