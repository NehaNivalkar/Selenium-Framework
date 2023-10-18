package com.ErosNow.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;

	public ReadConfig()
	{
		File src = new File("./Configuration/configuration.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		}catch(Exception e) {
			System.out.println("Exception is"+ e.getMessage());
			
		}
		
	}
		public String getApplicationURL()
		{
			String url = pro.getProperty("baseURL");
			return url;
		}
		
		public String getChromePath()
		{
			String chromepath = pro.getProperty("chromepath");
			return chromepath;
		}
		
		public String getFirefoxPath()
		{
			String firefoxpath = pro.getProperty("firefoxpath");
			return firefoxpath;
		}
		
		public String getFirefoxBinaryPath() {
		    String firefoxBinaryPath = pro.getProperty("firefoxpath");
		    return firefoxBinaryPath;
		}
		
		public String entercred()
		{
			String cred = pro.getProperty("cred");
			return cred;
		}
		
		public String enterpass()
		{
			String password = pro.getProperty("pass");
			return password;
		}
		
		public String invalidusername()
		{
			String invalidusername = pro.getProperty("invalidusername");
			return invalidusername;
		}
		
		public String invalidpassword()
		{
			String invalidpassword = pro.getProperty("invalidpassword");
			return invalidpassword;
		}
		
}
