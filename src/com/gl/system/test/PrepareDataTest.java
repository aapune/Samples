package com.gl.system.test;

import com.gl.system.Main;

import junit.framework.TestCase;
/**
 * 
 * @author Aniruddha Anikhindi
 * @version 1.0
 * 
 * Unit test case for successful parsing of csv data.
 *
 */
public class PrepareDataTest extends TestCase {
	
	/**
	 * Flag for successful parse of csv file
	 */
	private boolean successparseCsvFile;
	
	Main main = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		successparseCsvFile = true;
		main = new Main();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		successparseCsvFile = false;
		main = null;
	}
	
	
	public void testSuccessParseCSV() {
		 boolean retbooleanVal = main.prepareDataFromCSV();
		assertEquals(successparseCsvFile, retbooleanVal);
	}

}
