package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

	FirefoxDriver wd;

	private SessionHelper sessionHelper;
	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;

	public void init() {
		wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/");
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper (wd);
		sessionHelper = new SessionHelper(wd);
		sessionHelper.login("admin", "secret");
	}

	public void stop() {
		navigationHelper.wd.quit();
	}

	public GroupHelper getGroupHelper() {
		return groupHelper;
	}

	public NavigationHelper getNavigationHelper() {
		return navigationHelper;
	}


	public void submitContactCreation() {
		wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
	}

	public void fillContactForm(ContactData contactData) {
		wd.findElement(By.name("firstname")).click();
		wd.findElement(By.name("firstname")).clear();
		wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
		wd.findElement(By.name("middlename")).click();
		wd.findElement(By.name("middlename")).clear();
		wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
		wd.findElement(By.name("lastname")).click();
		wd.findElement(By.name("lastname")).clear();
		wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
		wd.findElement(By.name("nickname")).click();
		wd.findElement(By.name("nickname")).clear();
		wd.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
		wd.findElement(By.name("mobile")).click();
		wd.findElement(By.name("mobile")).clear();
		wd.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
		wd.findElement(By.name("email")).click();
		wd.findElement(By.name("email")).clear();
		wd.findElement(By.name("email")).sendKeys(contactData.getMail());
	}

	public void initContactCreation() {
		wd.findElement(By.linkText("add new")).click();
	}
}
