/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Doutriem Pro
 */
public class findIndexInTheDictionaryTest {
    
    public findIndexInTheDictionaryTest() {
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
     * Test of getIndex method, of class findIndexInTheDictionary.
     */
    @Test
    public void testGetIndex() throws Exception {
        System.out.println("getIndex");
        String searchedWord = "";
        int[] expResult = null;
        int[] result = findIndexInTheDictionary.getIndex(searchedWord);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
