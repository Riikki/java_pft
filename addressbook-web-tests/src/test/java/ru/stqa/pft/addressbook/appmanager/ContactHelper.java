package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
		type(By.name("address"), contactData.getAddress());
		type(By.name("home"), contactData.getHomePhone());
		type(By.name("mobile"), contactData.getMobilePhone());
		type(By.name("work"), contactData.getWorkPhone());
		type(By.name("email"), contactData.getEmail1());
		type(By.name("email2"), contactData.getEmail2());
		type(By.name("email3"), contactData.getEmail3());

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

	private void clickViewContact(int index) {
		findContactById(index).findElements(By.tagName("td")).get(6).findElement(By.tagName("a")).click();
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
			String allPhones = info.get(5).getText();
			String address = info.get(3).getText();
			String allMails = info.get(4).getText();
			ContactData contact = new ContactData()
					.withId(id)
					.withFirstName(firstName).withLastName(lastName)
					.withAllPhones(allPhones)
					.withAddress(address)
					.withAllMails(allMails);
			contacts.add(contact);
		}
		return contacts;
	}

	public ContactData infoFromEditForm(ContactData contact) {
		clickEditContact(contact.getId());
		String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
		String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
		String middleName = wd.findElement(By.name("middlename")).getAttribute("value");
		String nickname = wd.findElement(By.name("nickname")).getAttribute("value");
		String home = wd.findElement(By.name("home")).getAttribute("value");
		String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
		String work = wd.findElement(By.name("work")).getAttribute("value");
		String address = wd.findElement(By.name("address")).getText();
		String email1 = wd.findElement(By.name("email")).getAttribute("value");
		String email2 = wd.findElement(By.name("email2")).getAttribute("value");
		String email3 = wd.findElement(By.name("email3")).getAttribute("value");
		wd.navigate().back();
		return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName).withMiddleName(middleName).withNickName(nickname)
				.withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
				.withEmail1(email1).withEmail2(email2).withEmail3(email3);
	}

	public String infoFromViewPage(ContactData contact) {
		clickViewContact(contact.getId());
		String contactData = wd.findElement(By.cssSelector("#content")).getText().replaceAll("\\n\\n\\nMember of.*","");
		wd.navigate().back();
		return contactData;
	}

	public String infoFromEditFormToString(ContactData contact) {
		ContactData contactData = infoFromEditForm(contact);
		String delimiter = "\n";

		return String.join(delimiter,
					//First Name, MiddleName, LastName
					String.join(" ",contactData.getFirstName(), contactData.getMiddleName(), contactData.getLastName()),
					//NickName
					contactData.getNickName(),
					//Address
					String.join("",contactData.getAddress(),delimiter),
					//Mobile Phones
					String.join("",Arrays.asList(String.join(": ","H",contactData.getHomePhone()),
									String.join(": ","M",contactData.getMobilePhone()),
									String.join(": ","W",contactData.getWorkPhone()))
							.stream().filter((s) -> s.matches(".*\\d.*"))
							.collect(Collectors.joining(delimiter)),delimiter),
					//Emails
					Arrays.asList(contactData.getEmail1(), contactData.getEmail2(),contactData.getEmail3())
							.stream().filter((s) -> s.matches(".*@.*"))
							.map(ContactHelper::createMailString)
							.collect(Collectors.joining(delimiter))
				) ;
	}

	private static String createMailString(String mail) {
		String result = mail;

		String[] splitedMail = mail.split("@");
		String domain = splitedMail[splitedMail.length-1];
		if(domain.matches("mail.*")){
			result = String.join(" ",result,String.format("(www.%s)",domain));
		}
		return result;
	}
}
