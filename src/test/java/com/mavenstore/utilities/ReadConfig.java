package com.mavenstore.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties properties;

	String path = System.getProperty("user.dir") + "\\configuration\\" + "\\config.properties\\";

	public ReadConfig() {

		try {
			properties = new Properties();

			FileInputStream fis = new FileInputStream(path);

			properties.load(fis);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getbaseURL() {

		String value = properties.getProperty("baseURL");

		if (value != null) {
			return value;
		} else
			throw new RuntimeException("baseURL not found in config file");
	}

	public String getBrowser() {

		String value = properties.getProperty("browser");

		if (value != null) {
			return value;
		} else
			throw new RuntimeException("browser not found in config file");
	}

}
