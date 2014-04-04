package com.gl.system.test;

import java.util.List;

import com.gl.system.Main;
import com.gl.system.ShareDataObject;

import junit.framework.TestCase;

/**
 * 
 * @author Aniruddha Anikhindi
 * @version 1.0
 * 
 * Unit test case for retrieval of company share data
 * this test case depends on PrepareDataTest testcase
 *
 */
public class ShareDataTest extends TestCase {
	
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
	
	public void testShareData(){
		List<ShareDataObject> listShareData = main.getListShareData();
		assertNotNull(listShareData);
	}

}
