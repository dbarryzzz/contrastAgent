package com.example;

import com.example.data.DataRecorder;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.log4j.BasicConfigurator;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
        BasicConfigurator.configure();
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Nominal testing
     */
    public void testDataRecorder() {
        DataRecorder.startRequest();
        DataRecorder.recordClassLoad("java.lang.String");
        DataRecorder.recordObjectCreation("java.lang.String");
        DataRecorder.endRequest();
        assert(DataRecorder.getAllDetails().length() > 0);
    }
}
