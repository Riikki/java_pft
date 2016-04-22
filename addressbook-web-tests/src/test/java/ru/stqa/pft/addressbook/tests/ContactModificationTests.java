package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

	@Test
	public void ContactModificationTests() {
		app.getNavigationHelper().goToHomePage();
		app.getContactHelper().editContact();
		app.getContactHelper().fillContactForm(new ContactData("Edit_TestFirstName", "Edit_TestMiddleName", "Edit_TestLastName", "Edit_TestNickname", "+111111111112", "testmailedit@test.com"));
		app.getContactHelper().updateContact();
		app.getNavigationHelper().goToHomePage();
	}
}
