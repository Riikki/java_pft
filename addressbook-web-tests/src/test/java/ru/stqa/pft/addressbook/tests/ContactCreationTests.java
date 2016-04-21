package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreationTests() {
		app.getGroupHelper().initContactCreation();
		app.getGroupHelper().fillContactForm(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "TestNickname", "+111111111111", "testmail@test.com"));
		app.getGroupHelper().submitContactCreation();
		app.getNavigationHelper().goToHomePage();
	}
}


