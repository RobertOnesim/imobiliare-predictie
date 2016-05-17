/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mada
 */
public class ProprietatiTest {
    
    public ProprietatiTest() {
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
     * Test of getIdProprietate method, of class Proprietati.
     */
    @Test
    public void testGetIdProprietate() {
        System.out.println("getIdProprietate");
        Proprietati instance = new Proprietati();
        instance.setIdProprietate("#12323");
        char expResult = '#';
        String result = instance.getIdProprietate();
        //System.out.println("rezultatul este **************" + result);
        char firstCh = result.charAt(0);
        //System.out.println("fist caracteeer ----------" + firstCh);
        //System.out.println(firstCh);
        assertTrue(expResult==result.charAt(0));
    }


    /**
     * Test of getLink method, of class Proprietati.
     */
    @Test
    public void testGetLink() {
        System.out.println("getLink");
        Proprietati instance = new Proprietati();
        instance.setLink("http://www.casa-alba.ro/oferta/apartament-de-vanzare-3-camere-iasi-copou/1525");
        String expResult = "http://www.casa-alba.ro/";
        String result = instance.getLink();
        assertEquals(expResult, result.substring(0, 24));
    }

    /**
     * Test of getMoneda method, of class Proprietati.
     */
    @Test
    public void testGetMoneda() {
        System.out.println("getMoneda");
        Proprietati instance = new Proprietati();
        instance.setPret("RON");
        String expResult = "EUR";
        String result = instance.getPret();
        assertEquals(expResult, result);
    }
    /**
     * Test of getSuprafata method, of class Proprietati.
     */
    @Test
    public void testGetSuprafata() {
        System.out.println("getSuprafata");
        Proprietati instance = new Proprietati();
        instance.setSuprafata("67,00 mp");
        String expResult = "mp";
        String result = instance.getSuprafata();
        assertEquals(expResult, result.substring(6,8));
    }

    /**
     * Test of getSuprafataTeren method, of class Proprietati.
     */
    /*@Test
    public void testGetSuprafataTeren() {
        System.out.println("getSuprafataTeren");
        Proprietati instance = new Proprietati();
        String expResult = "";
        String result = instance.getSuprafataTeren();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
