package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreationTests() {
		app.getContactHelper().initContactCreation();
		app.getContactHelper().fillContactForm(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "TestNickname", "+111111111111", "testmail@test.com"));
		app.getContactHelper().submitContactCreation();
		app.getNavigationHelper().goToHomePage();
	}
}


