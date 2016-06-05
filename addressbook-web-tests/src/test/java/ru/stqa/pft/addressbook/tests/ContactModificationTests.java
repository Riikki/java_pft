package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.ContactDataProvider;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

	@BeforeMethod
	public void ensurePreConditions() throws IOException {
		Contacts contacts = app.db().contacts();
		if(contacts.size() == 0){
			ContactData contact = new ContactDataProvider().getOneContactFromJson();
			app.db().createContact(contact);
		}
		app.goTo().home();
	}

	@Test
	public void testContactModificationTests() {
		Contacts before = app.db().contacts();

		ContactData modifiedContact = before.iterator().next();
		ContactData contact = modifiedContact.withFirstName(String.format("%s-%s",modifiedContact.getFirstName(),modifiedContact.getId()));

		app.contact().modify(contact);

		Contacts after = app.db().contacts();
		assertThat(after,equalTo(
				before.withoutAdded(modifiedContact).withAdded(contact)));

	}
}