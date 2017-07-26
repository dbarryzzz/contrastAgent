package com.example.agents;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.lang.instrument.Instrumentation;

public class WebAgent {

	static Logger log = Logger.getLogger(WebAgent.class);

	public static void premain(String args, Instrumentation instrumentation){
		// configure logging
		BasicConfigurator.configure();

		log.info("creating webagent transformers");
		System.out.println("WebAgent.premain");
		ObjectCreationTransformer objectTransformer = new ObjectCreationTransformer();
		instrumentation.addTransformer(objectTransformer);
		RequestFilterTransformer requestTransformer = new RequestFilterTransformer();
		instrumentation.addTransformer(requestTransformer);
	}
}
