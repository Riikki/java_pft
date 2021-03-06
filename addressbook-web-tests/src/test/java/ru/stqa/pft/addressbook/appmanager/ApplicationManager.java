package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

	Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
	WebDriver wd;
	private final Properties properties;

	private ContactHelper contactHelper;
	private SessionHelper sessionHelper;
	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private String browser;
	private DbHelper dbHelper;

	public ApplicationManager(String browser) {
		this.browser = browser;
		properties = new Properties();
	}

	public void init() throws IOException {
		System.out.println(System.getProperty("target"));
		String target = System.getProperty("target","local");
		properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

		dbHelper = new DbHelper();

		if("".equals(properties.getProperty("selenium.server"))){
			if (browser.equals(BrowserType.FIREFOX)) {
				wd = new FirefoxDriver();
			} else if (browser.equals(BrowserType.CHROME)) {
				wd = new ChromeDriver();
			} else if (browser.equals(BrowserType.IE)) {
				wd = new InternetExplorerDriver();
			}
		}else{
			System.out.printf("remote");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(browser);
			capabilities.setPlatform(Platform.fromString(properties.getProperty("platform","win7")));
			wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")),capabilities);
		}

		wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		wd.get(properties.getProperty("web.baseUrl"));
		groupHelper = new GroupHelper(wd);
		contactHelper = new ContactHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		sessionHelper = new SessionHelper(wd);
		sessionHelper.login(properties.getProperty("web.userLogin"), properties.getProperty("web.userPassword"));
	}

	public void stop() {
		wd.quit();
	}

	public GroupHelper group() {
		return groupHelper;
	}

	public NavigationHelper goTo() {
		return navigationHelper;
	}

	public ContactHelper contact() {
		return contactHelper;
	}

	public Properties getProperties(){
		return properties;
	}

	public DbHelper db(){
		return dbHelper;
	}
}
