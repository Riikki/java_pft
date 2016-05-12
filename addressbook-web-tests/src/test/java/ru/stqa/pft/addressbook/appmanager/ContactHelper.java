package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {
	ContactHelper(WebDriver wd) {
		super(wd);
	}

	private void initCreation() {
		click(By.linkText("add new"));
	}

	private void fillForm(ContactData contactData, boolean creation) {
		type(By.name("firstname"), contactData.getFirstName());
		type(By.name("middlename"), contactData.getMiddleName());
		type(By.name("lastname"), contactData.getLastName());
		type(By.name("nickname"), contactData.getNickName());
		type(By.name("mobile"), contactData.getMobile());

		if (creation) {
			new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}
	}

	private void submitCreation() {
		click(By.name("submit"));
	}

	private void deleteSelected() {
		click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
		wd.switchTo().alert().accept();
	}

	private void clickUpdateContact() {
		click(By.name("update"));
	}

	private void clickEditContact(int index) {
		WebElement row = findContactById(index);
		row.findElements(By.tagName("td")).get(7).click();
	}

	private void selectContactForDelete(ContactData contact) {
		WebElement row = findContactById(contact.getId());
		row.findElement(By.tagName("input")).click();
	}

	private void goHome() {
		click(By.linkText("home"));
	}

	public void create(ContactData contact) {
		initCreation();
		fillForm(contact, true);
		submitCreation();
		goHome();
	}

	public void delete(ContactData contact) {
		selectContactForDelete(contact);
		deleteSelected();
		goHome();
	}

	public void modify(ContactData contact) {
		clickEditContact(contact.getId());
		fillForm(contact, false);
		clickUpdateContact();
		goHome();
	}

	private WebElement findContactById(int index) {
		List<WebElement> tableRows = wd.findElements(By.name("entry"));
		WebElement result = tableRows.get(0);
		for(WebElement row : tableRows){
			WebElement input = row.findElement(By.cssSelector("input"));

			if(input.getAttribute("value").equals(Integer.toString(index))){
				result = row;
				break;
			}
		}
		return result;
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int getGroupCount() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public Contacts all() {
		Contacts contacts = new Contacts();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			List<WebElement> info = element.findElements(By.tagName("td"));
			String lastName = info.get(1).getText();
			String firstName = info.get(2).getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			ContactData contact = new ContactData()
					.withId(id)
					.withFirstName(firstName).withMiddleName("TestMiddleName").withLastName(lastName).withNickName("TestNickname")
					.withMobile("+111111111111").withGroup("test2");
			contacts.add(contact);
		}
		return contacts;
	}
}
