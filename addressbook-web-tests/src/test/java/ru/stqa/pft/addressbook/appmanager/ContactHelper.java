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
		findContactById(index).findElements(By.tagName("td")).get(7).findElement(By.tagName("a")).click();
	}

	private void selectContactForDelete(ContactData contact) {
		findContactById(contact.getId()).findElement(By.cssSelector(String.format("input[value='%s']",contact.getId()))).click();
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

	private WebElement findContactById(int id) {
		return wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]", id)));
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
					.withMobilePhone("+111111111111").withGroup("test2");
			contacts.add(contact);
		}
		return contacts;
	}

	public ContactData infoFromEditForm(ContactData contact) {
		clickEditContact(contact.getId());
		String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
		String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
		String home = wd.findElement(By.name("home")).getAttribute("value");
		String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
		String work = wd.findElement(By.name("work")).getAttribute("value");
		wd.navigate().back();
		return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
				.withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
	}
}
