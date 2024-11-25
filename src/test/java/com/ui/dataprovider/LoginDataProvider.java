package com.ui.dataprovider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojos.TestData;
import com.ui.pojos.User;
import com.utility.ExcelReaderUtility;

public class LoginDataProvider {

	@DataProvider(name = "LoginTestDataProvider")
	public Iterator<Object[]> loginDataProvider() throws FileNotFoundException {
		Gson gson = new Gson();
		
		File testDataFile = new File(System.getProperty("user.dir")+ "\\testData\\loginData.json");
		FileReader fileReader = new FileReader(testDataFile);
		TestData data = gson.fromJson(fileReader, TestData.class);//deserialization, where we are converting the json object to get the data from the json object and we are creating a java object out of it.. this is the reference to the java object
		
		List<Object[]> dataToReturn = new ArrayList<Object[]>();
		for (User user : data.getData()) {
			dataToReturn.add(new Object[] {user});
			
		}
		return dataToReturn.iterator();
		
	}
	
	@DataProvider(name = "LoginTestExcelDatProvider")
	public Iterator<User> loginExcelDataProvider(){
		return ExcelReaderUtility.readExcelFile("loginData.xlsx");
	}
	
	
}
