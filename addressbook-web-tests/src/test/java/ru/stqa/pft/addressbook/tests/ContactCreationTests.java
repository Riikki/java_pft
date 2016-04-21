package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreationTests() {
		app.initContactCreation();
		app.fillContactForm(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "TestNickname", "+111111111111", "testmail@test.com"));
		app.submitContactCreation();
		app.getNavigationHelper().goToHomePage();
	}
}


