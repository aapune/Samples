package com.gl.system.test;

import java.util.List;

import com.gl.system.Main;

import junit.framework.TestCase;

/**
 * 
 * @author Aniruddha Anikhindi
 * @version 1.0
 * 
 * Unit test case for retrieval of company data
 * this test case depends on PrepareDataTest testcase
 *
 */
public class CompanyDataTest extends TestCase {
	
	
	Main main = null; 
	protected void setUp() throws Exception {
		super.setUp();
		main = new Main();
		main.prepareDataFromCSV();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		main = null;
	}
	
	public void testCompanyData(){
		List<String> list = main.getCompanyNameList();
		assertNotNull(list);
	}
	
	

}
