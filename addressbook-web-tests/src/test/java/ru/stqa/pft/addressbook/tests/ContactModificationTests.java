package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

	@Test
	public void testContactModificationTests() {
		app.getNavigationHelper().goToHomePage();
		if (! app.getContactHelper().isThereAContact()) {
			app.getContactHelper().createContact(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "TestNickname", "+111111111111", "test1"));
		}
		List<ContactData> before = app.getContactHelper().getContactList();
		ContactData contact = new ContactData("TestFirstName", null, "TestLastName", null, null, "test2");
		app.getContactHelper().editContact();
		app.getContactHelper().fillContactForm(new ContactData("Edit_TestFirstName", "Edit_TestMiddleName", "Edit_TestLastName", "Edit_TestNickname", "+111111111112", null), false);
		app.getContactHelper().updateContact();
		app.getNavigationHelper().goToHomePage();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size());

		before.remove(before.size()-1);
		before.add(contact);
		Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}
}
