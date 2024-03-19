package com.qa.democart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public static String highlight;
	private OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the driver
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {
		String browserName=prop.getProperty("browser");
		highlight=prop.getProperty("highlight");
		System.out.println("Browser name is :" + browserName);
		
		optionsManager=new OptionsManager(prop);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}

		else if (browserName.equalsIgnoreCase("safari")) {
//			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}

		else {
			System.out.println("Please pass correct browser name");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();

	}
	
	public WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * This method is used to initialize the properties on the basis of given environment.
	 * @return
	 */
	public Properties initProperties() {
		Properties prop=null;
		FileInputStream ip=null;
	
		String env=System.getProperty("env");//mvn clean install -Denv="qa"
		
		try {
		if(env.equals(null)) {
			System.out.println("Running on Environment : Prod env....");
        	ip= new FileInputStream("./src/test/resources/Config/config.properties");
		}
		else {
			System.out.println("Running on Environment : "+env);
			switch (env) {
			case "qa":
				ip= new FileInputStream("./src/test/resources/Config/qa.config.properties");
			break;
			case "dev":
				ip= new FileInputStream("./src/test/resources/Config/dev.config.properties");
			break;
			case "stage":
				ip= new FileInputStream("./src/test/resources/Config/stage.config.properties");
			break;
			default:
				System.out.println("No environment found...");
				throw new Exception("NOENVFOUNDEXCEPTION");
			}
		}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			
			prop = new Properties();
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * This method is used to take screenshot
	 * @return
	 */
	public String getScreenshot() {
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path= System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination= new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
}
