package com.example.data;

import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataRecorder {

    static Logger log = Logger.getLogger(DataRecorder.class);

    //TODO: handle concurrent requests.  Use thread/session id? and convert a long to a map
    private static long activeRequestId = 0L;
    private static Map<Long, RequestDetails> requestDataMap = new HashMap<Long, RequestDetails>();

    public static void startRequest(){
        RequestDetails rd = createNewDetail();
        requestDataMap.put(rd.getId(), rd);
        activeRequestId = rd.getId();
    }

    public static void endRequest(){
        RequestDetails rd = requestDataMap.get(activeRequestId);
        if(rd != null) {
            rd.setEnd(new Date());
            log.info("finishing request, details: " + rd.outputLog());
        }
    }

    public static void recordClassLoad(String className){
        RequestDetails rd = requestDataMap.get(activeRequestId);
        log.info("recording class load for for : " + className);
        if(rd == null){
            rd = createNewDetail();
        }
        rd.addLoadedClass(className);
    }

    public static void recordObjectCreation(String className){
        RequestDetails rd = requestDataMap.get(activeRequestId);
        log.info("recording object creation for : " + className);
        if(rd == null){
            rd = createNewDetail();
        }
        if(className.equals("java.lang.String")){
            rd.incrementNumStrings();
        }
    }

    private static RequestDetails createNewDetail(){
        return new RequestDetails(getNextId(), new Date());
    }

    private static long getNextId(){
       return activeRequestId++;
    }

    public static String getAllDetails(){
        StringBuffer sb = new StringBuffer();
        sb.append(RequestDetails.outputCsvHeader());
        for(RequestDetails rd : requestDataMap.values()){
            sb.append(rd.outputCsv());
        }
        return sb.toString();
    }

}
