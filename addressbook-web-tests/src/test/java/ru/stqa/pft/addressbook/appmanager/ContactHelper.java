package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

	public void selectContact(int index) {
		wd.findElements(By.name("selected[]")).get(index).click();
	}

	public void editContact() {
		click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
	}

	public void createContact(ContactData contact) {
		initContactCreation();
		fillContactForm(contact, true);
		submitContactCreation();
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int getGroupCount() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public List<ContactData> getContactList() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			List<WebElement> info = element.findElements(By.tagName("td"));
			String lastName = info.get(1).getText();
			String firstName = info.get(2).getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			ContactData contact = new ContactData(id, firstName, "TestMiddleName", lastName, "TestNickname", "+111111111111", "test1");
			contacts.add(contact);
		}
		return contacts;
	}
}
