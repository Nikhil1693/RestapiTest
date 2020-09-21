package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	
	
	
	public Properties prop;
	
		public TestBase(){
			prop=new Properties();
			try {
				FileInputStream fis=new FileInputStream("C:\\Users\\50024558\\eclipse-workspace\\restapitest\\src\\main\\java\\com\\qa\\config\\config,properties");
				try {
					prop.load(fis);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

}
