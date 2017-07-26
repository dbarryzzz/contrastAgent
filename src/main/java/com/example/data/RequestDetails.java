package com.example.data;

import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RequestDetails {

    private long id;
    private int numStrings;
    private Date start;
    private Date end;
    private long totalMemory;
    private String requestedUrl;
    private Set<String> classesLoaded;

    public RequestDetails(long id, Date start){
        this.id = id;
        this.start = start;
        numStrings = 0;
        totalMemory = 0L;
        classesLoaded = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public int getNumStrings() {
        return numStrings;
    }

    public void setNumStrings(int numStrings) {
        this.numStrings = numStrings;
    }

    public void incrementNumStrings(){this.numStrings++;}

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public int getNumClasses() {
        return classesLoaded.size();
    }

    public boolean addLoadedClass(String clazz){
        return classesLoaded.add(clazz);
    }

    public String getRequestedUrl() {
        return requestedUrl;
    }

    public void setRequestedUrl(String requestedUrl) {
        this.requestedUrl = requestedUrl;
    }

    public Duration getDuration(){
        Duration d = Duration.ZERO;
        if(start!= null && end != null) {
            d =  Duration.between(start.toInstant(), end.toInstant());
        }
        return d;
    }

    public String outputLog(){
        Duration d = getDuration();
        return "id: " + id
                + ", number of Strings: " + numStrings
                + ", elapsed time: " + d
                + ", total memory: " + totalMemory
                + ", number of Classes: " + getNumClasses();
    }

    public String outputCsv(){
        Duration d = getDuration();
        return id + "," + numStrings + "," + d + "," + totalMemory + "," + getNumClasses();
    }

    public static String outputCsvHeader(){
        return "id,numStrings,time,totalMemory,numClasses";
    }

}
