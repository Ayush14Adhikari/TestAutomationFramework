package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {
	
	public static String readProperty(Env env,String propertyName) {
		File propFile=new File(System.getProperty("user.dir")+"\\config\\"+env+".properties");
		System.out.println(propFile);
		FileReader fileReader = null;
		Properties properties = new Properties();
		try {
			fileReader = new FileReader(propFile);
			
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value=properties.getProperty(propertyName);
		System.out.println(value.toUpperCase());
		return value;
	}

}
