package com.appcoretech.expensemanager.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class ServerPropertySupport {

	private Properties props = new Properties();

	private Environment env;

	private static final Logger LOG = Logger
			.getLogger(ServerPropertySupport.class.getName());

	private void loadProperty() {

		if (props.size() == 0) {
			// String serverConf = System.getProperty("serverconf");
			String serverConf = env.getServerConf();
			FileInputStream fis;
			try {
				fis = new FileInputStream(serverConf
						+ "/conf/server.properties");
				props.load(fis);
				LOG.warn("Property File is loaded with properties count : "
						+ props.size());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * returns the property for the given key.
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		loadProperty();
		String value = null;
		if (props.containsKey(key)) {
			value = props.getProperty(key);
		} else {
			value = "";
			LOG.warn("The property for the requested key : " + key
					+ " is not present");
		}
		return value;
	}

	/**
	 * returns the list of properties for the given key.
	 * 
	 * @param key
	 * @return
	 */
	public List<String> getPropertiesList(String key) {
		loadProperty();
		Set<Object> keys = props.keySet();
		List<String> list = new ArrayList<String>();
		for (Object k : keys) {
			if (((String) k).equals(key)) {
				list.add(props.getProperty((String) k));
			}
		}
		return list;
	}
}
