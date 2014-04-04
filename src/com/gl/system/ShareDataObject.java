package com.gl.system;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author Aniruddha Anikhindi
 * @version 1.0
 * 
 * This is complex object for storing company wise data map.
 * 
 */
public class ShareDataObject {
	
	/**
	 * Map used for compnay data key : yearmonth, value : share data
	 */
	private Map<Integer, Double> dataMap = new TreeMap<Integer, Double>();
	
	/**
	 * Getter for data map
	 * @return
	 */
	public Map<Integer, Double> getDataMap() {
		return dataMap;
	}
	
	/**
	 * Setter for data map
	 * @param dataMap
	 */
	public void setDataMap(Map<Integer, Double> dataMap) {
		this.dataMap = dataMap;
	}

}
