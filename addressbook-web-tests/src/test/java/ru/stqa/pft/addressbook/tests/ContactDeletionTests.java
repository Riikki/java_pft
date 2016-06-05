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

public class ContactDeletionTests extends TestBase {

	private ContactData contact;

	@BeforeMethod
	public void ensurePreConditions() throws IOException {
		Contacts contacts = app.db().contacts();
		if(contacts.size()>0){
			this.contact = contacts.iterator().next();
		}else{
			ContactData contact = new ContactDataProvider().getOneContactFromJson();
			app.db().createContact(contact);
			this.contact = app.db().getContact(contact);

		}
		app.goTo().home();
	}

	@Test
	public void testContactDeletionTests() {
		Contacts before = app.db().contacts();

		ContactData contact = this.contact;
		app.contact().delete(contact);

		Contacts after = app.db().contacts();
		assertThat(after,equalTo(
				before.withoutAdded(contact)));
	}


}