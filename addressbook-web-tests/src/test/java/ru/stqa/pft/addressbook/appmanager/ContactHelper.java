package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {
	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void initContactCreation() {
		click(By.linkText("add new"));
	}

	public void fillContactForm(ContactData contactData, boolean creation) {
		type(By.name("firstname"), contactData.getFirstname());
		type(By.name("middlename"), contactData.getMiddlename());
		type(By.name("lastname"), contactData.getLastname());
		type(By.name("nickname"), contactData.getNickname());
		type(By.name("mobile"), contactData.getMobile());


		if (creation) {
			new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}
	}

	public void submitContactCreation() {
		click(By.name("submit"));
	}

	public void deleteSelectedContact() {
		click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
		wd.switchTo().alert().accept();
	}

	public void updateContact() {
		click(By.name("update"));
	}

	public void selectContact() {
		click(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[3]/td[1]/input"));
	}

	public void editContact() {
		wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[3]/td[8]/a/img")).click();
	}
}
