package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreationTests() {
		app.getNavigationHelper().gotoGroupPage();
				if (! app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createGroup(new GroupData("test2", null, null));
		}
		app.getNavigationHelper().goToHomePage();
		List<ContactData> before = app.getContactHelper().getContactList();
		ContactData contact = new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "TestNickname", "+111111111111", "test2");
		app.getContactHelper().initContactCreation();
		app.getContactHelper().fillContactForm(contact, true);
		app.getContactHelper().submitContactCreation();
		app.getNavigationHelper().goToHomePage();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() + 1);

		before.add(contact);
		Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
		}
	}


