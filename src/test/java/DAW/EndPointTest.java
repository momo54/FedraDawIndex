/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAW;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pascal
 */
public class EndPointTest {

    public EndPointTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of processDump method, of class EndPoint.
     */
    @Test
    public void testProcessDump() throws Exception {
        System.out.println("processDump");
        EndPoint instance = new EndPoint(EndPoint.class.getResource("/test.nt").getFile(), "http://myurl.com");
        instance.processDump();
        assertTrue("Capabilities should be 1", instance.getCapabilities().keySet().size() == 1);
        System.out.println(instance.toString());
    }

    @Test
    public void testProcessDumpAverage() throws Exception {
        System.out.println("processDump");
        EndPoint instance = new EndPoint(EndPoint.class.getResource("/testAverage.nt").getFile(), "http://myurl.com");
        instance.processDump();
        assertTrue("Capabilities should be 1", instance.getCapabilities().keySet().size() == 1);
        System.out.println(instance.toString());
    }

    @Test
    public void testProcessDumpLong() throws Exception {
        System.out.println("processDump");
        EndPoint instance = new EndPoint(EndPoint.class.getResource("/testLong.nt").getFile(), "http://myurl.com");
        instance.processDump();
        assertTrue("Capabilities should be 2", instance.getCapabilities().keySet().size() == 2);
        System.out.println(instance.toString());
    }

}
