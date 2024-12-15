package com.ui.dataProviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;

public class LoginDataProvider {

	@DataProvider(name="LoginTestDataProvider")
	public Iterator<Object[]> loginDataProvider() throws FileNotFoundException {
		Gson gson = new Gson();
		File testDataFile= new File(System.getProperty("user.dir")+"\\testData\\logindata.json");
		System.out.println(testDataFile);
		FileReader fileReader = new FileReader(testDataFile);//deserialization
		
		TestData data=gson.fromJson(fileReader, TestData.class);
		List<Object[]> dataToReturn = new ArrayList<Object[]>();
		for(User user:data.getData()) {
			dataToReturn.add(new Object[] {user});
		}
		return dataToReturn.iterator();
	}
	@DataProvider(name="LoginCSVDataProvider")
	public Iterator<User> loginDataCsv() {
		
		return CSVReaderUtility.readCsvFile("loginData.csv");
		
	}
	
	@DataProvider(name="LoginExcelDataProvider")
	public Iterator<User> loginDataExcel() {
		
		return ExcelReaderUtility.readExcelFile("loginData.xlsx");
		
	}
}
