package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreationTests() {
		app.getNavigationHelper().gotoGroupPage();
		if (! app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createGroup(new GroupData("test1", null, null));
		}
		app.getNavigationHelper().goToHomePage();
		app.getContactHelper().initContactCreation();
		app.getContactHelper().fillContactForm(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "TestNickname", "+111111111111", "test1"), true);
		app.getContactHelper().submitContactCreation();
		app.getNavigationHelper().goToHomePage();
	}
}


