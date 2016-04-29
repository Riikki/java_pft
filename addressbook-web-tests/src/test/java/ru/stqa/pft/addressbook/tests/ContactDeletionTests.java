package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

	@Test
	public void ContactDeletionTests() {
		app.getNavigationHelper().goToHomePage();
		if (! app.getContactHelper().isThereAContact()) {
			app.getContactHelper().createContact(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "TestNickname", "+111111111111", "test1"));
		}
		app.getNavigationHelper().goToHomePage();
		app.getContactHelper().selectContact();
		app.getContactHelper().deleteSelectedContact();
		app.getNavigationHelper().goToHomePage();
	}

}
