package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

public class JsonUtility {

	public static Environment readJson(Env env) {
		
	
		Gson gson= new Gson();
		File jsonFile = new File(System.getProperty("user.dir")+"//Config//config.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Config config= gson.fromJson(fileReader,Config.class);
		Environment enviroment = config.getEnvironments().get("QA");
		System.out.println(enviroment.getUrl());
		return enviroment;
	}
}
