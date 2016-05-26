package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.ContactDataProvider;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

	@BeforeMethod
	public void ensurePreConditions(Object[] contacts){
		app.goTo().home();
		ContactData contact = app.contact().getContactFromDataProvider(contacts);
		app.goTo().group();
		contact = app.group().ensureGroupExistAndCreate(contact);
		app.goTo().home();
		int existedContactId = app.contact().getPresentContactId(contact);
		if (existedContactId == 0) {
			app.contact().create(contact);
			existedContactId = app.contact().getPresentContactId(contact);
			contact.withId(existedContactId);
		}else{
			contact.withId(existedContactId);
		}
		contacts[0] = contact;
	}
	@Test(dataProvider = "validContactsFromJson", dataProviderClass = ContactDataProvider.class)
	public void testContactDeletionTests(ContactData contact) {
		Contacts before = app.contact().all();
		app.contact().delete(contact);
		Contacts after = app.contact().all();

		assertThat(after.size(),equalTo(before.size() - 1));
		assertThat(after,equalTo(
				before.withoutAdded(contact)));
	}


}