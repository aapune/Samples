package com.gl.system;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author Aniruddha Anikhindi
 * @version 1.0
 * 
 * This is main running class for demo for printing maximum share data from test.csv file
 *          
 * 
 */
public class Main {
	
	/**
	 * Configuration property variable for configuration properties
	 */
	private Properties configProp = null;
	
	/**
	 * List of all company names from csv file
	 */
	
	private List<String> companyNameList = null;
	
	/**
	 * List of complex data object in which company wise  share data is stored
	 */
	private List<ShareDataObject> listShareData = null;

	public static void main(String[] args) {

		Main obj = new Main();
		obj.prepareDataFromCSV();
		obj.printMaxShareDataForCompanies();

	}

	
	/**
	 * This method prints maximum share price available for all available companies
	 */
	public void printMaxShareDataForCompanies() {
		if ((companyNameList != null && companyNameList.size() > 0)
				&& (listShareData != null && listShareData.size() > 0)) {

			for (int i = 0; i < companyNameList.size(); i++) {
				printMaximumSharePriceData(companyNameList.get(i),
						listShareData.get(i).getDataMap());
			}

		}
	}

	/**
	 * This is additional supportive method can be used for future reporting kind of thing.
	 * Sorts company data map w.r.t year
	 * For example 1990-1, 1991-2, 1992-4
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<Integer, Double> getSortedMapByValue(Map map) {
		Map<Integer, Double> result = new LinkedHashMap<Integer, Double>();
		List<Double> list = new LinkedList<Double>(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put((Integer) entry.getKey(), (Double) entry.getValue());
		}
		return result;

	}

	/**
	 * This method sorts company map according to value i.e. max share price and prints to console with its key i.e.month year.
	 * @param companyName
	 * @param map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void printMaximumSharePriceData(String companyName, Map map) {

		Double maxValueInMap = null;
		Integer maxKey = null;
		List<Double> list = new LinkedList<Double>(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		Collections.reverse(list);
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			maxValueInMap = (Double) entry.getValue();
			maxKey = (Integer) entry.getKey();
			break;

		}
		String yymmStr = Integer.toString(maxKey);
		String year = yymmStr.substring(0,4);
		String month = Integer.toString(maxKey).substring(4,yymmStr.length());
		String monthString = new DateFormatSymbols().getMonths()[Integer.parseInt(month) -1];
		System.out.println("For company Name:" + companyName
				+ "------maximum share value is :" + maxValueInMap
				+ "  In year :" + year + "  In month of : "+monthString);

	}

	/**
	 * This is business logic method where csv file is parsed and all complex objects are filled
	 */
	public boolean prepareDataFromCSV() {

		loadConfigurationFile();
		companyNameList = new ArrayList<String>();
		listShareData = new ArrayList<ShareDataObject>();
		String csvFile = System.getProperty("user.dir") + "/src/csv/test.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int noOfComapnies = 0;
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] csvCol = line.split(cvsSplitBy);
				if (csvCol[0].equalsIgnoreCase("Year")
						&& csvCol[1].equalsIgnoreCase("Month")) {
					for (int i = 2; i < csvCol.length; i++) {
						companyNameList.add(csvCol[i]);
					}

					noOfComapnies = companyNameList.size();
					for (int i = 0; i < noOfComapnies; i++) {
						ShareDataObject shareDatai = new ShareDataObject();
						listShareData.add(shareDatai);
					}
					continue;
				} else {
					String year = csvCol[0];
					String month = getMonth(csvCol[1]);
					String appStr = year + month;
					Integer yyyymm = Integer.parseInt(appStr);
					for (int i = 2; i < noOfComapnies + 2; i++) {
						listShareData.get(i - 2).getDataMap()
								.put(yyyymm, new Double(csvCol[i]));
					}

				}

			}
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (br != null) {
				try {
					br.close();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method loads configuration property file.
	 */
	private void loadConfigurationFile() {
		configProp = new Properties();
		try {
			configProp.load(this.getClass().getResourceAsStream(
					"config.properties"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * This method returns integer from month in string variable.
	 * @param key
	 * @return
	 */
	private String getMonth(String key) {
		String monthVal = null;
		if (configProp != null) {
			monthVal = configProp.getProperty(key);
		}
		return monthVal;
	}

	
	/**
	 * Getter for config prop
	 * @return
	 */
	public Properties getConfigProp() {
		return configProp;
	}

	/**
	 * Setter for config prop
	 * @param configProp
	 */
	public void setConfigProp(Properties configProp) {
		this.configProp = configProp;
	}

	
	/**
	 * Getter for company list
	 * @return
	 */
	public List<String> getCompanyNameList() {
		return companyNameList;
	}

	/**
	 * Setter for company list
	 * @param companyNameList
	 */
	public void setCompanyNameList(List<String> companyNameList) {
		this.companyNameList = companyNameList;
	}

	/**
	 * Getter for share data list
	 * @return
	 */
	public List<ShareDataObject> getListShareData() {
		return listShareData;
	}

	/**
	 * Setter for share data list
	 * @param listShareData
	 */
	public void setListShareData(List<ShareDataObject> listShareData) {
		this.listShareData = listShareData;
	}
	
	

}