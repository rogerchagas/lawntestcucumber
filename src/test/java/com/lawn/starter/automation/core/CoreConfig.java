package com.lawn.starter.automation.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * <h1>Class CoreConfig</h1>
 * @author Rogerio Reis
 * <p>details: CoreConfig class will extract config values from config.json, and then <br>
 * 	allow user to access these config values</p>
 */
public class CoreConfig {
	private Map<String, String> configMap = new HashMap<String, String>();
	Gson gson = new Gson();
	BufferedReader br = null;
	private static String DEFAULT_FILE = "config.json";
	
	public CoreConfig() {
		loadFile();
	}

	public CoreConfig(String fileName){
		loadFile(fileName);
	}
	
	public void loadFile(){
		loadFile(DEFAULT_FILE);
	}
	
	public void loadFile(String fileName){
		
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		java.lang.reflect.Type mapType = new TypeToken<Map<String, String>>(){}.getType();
		this.configMap = gson.fromJson(br, mapType);
	}

	public Map<String, String> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, String> configMap) {
		this.configMap = configMap;
	}
	
	public String getValue(String fieldName){
		return configMap.get(fieldName);
	}
}
