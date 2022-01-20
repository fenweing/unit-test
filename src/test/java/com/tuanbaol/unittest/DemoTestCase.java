package com.tuanbaol.unittest;

import junit.framework.TestCase;
import junit.textui.TestRunner;

public class DemoTestCase extends TestCase {
    public static void main(String[] args) {
        TestRunner.run(DemoTestCase.class);
    }

    public void setUp(){
        System.out.println("set up");
    }
    public void testAddNumer(){
        assertEquals(1+3,5);
        System.out.println("testing");
    }
    public void tearDown(){
        System.out.println("tear down");
    }
}
