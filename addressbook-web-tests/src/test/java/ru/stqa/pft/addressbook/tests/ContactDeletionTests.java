package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

	@BeforeMethod
	public void ensurePreConditions(){
		app.goTo().home();
		if (app.contact().all().size() == 0) {
			app.contact().create(new ContactData().withDefaultData());
		}

	}
	@Test
	public void testContactDeletionTests() {

		Contacts before = app.contact().all();
		ContactData deletedContact = before.iterator().next();

		app.contact().delete(deletedContact);
		Contacts after = app.contact().all();

		assertThat(after.size(),equalTo(before.size() - 1));
		assertThat(after,equalTo(
				before.withoutAdded(deletedContact)));
	}


}
