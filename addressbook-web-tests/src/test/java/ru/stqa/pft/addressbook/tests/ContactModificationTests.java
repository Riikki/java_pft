package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

	@BeforeMethod
	public void ensurePreConditions(){
		app.goTo().home();
		if (app.contact().all().size() == 0) {
			app.contact().create(new ContactData().withDefaultData());
		}
	}

	@Test
	public void testContactModificationTests() {
		Contacts before = app.contact().all();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData().withDefaultData()
				.withId(modifiedContact.getId())
				.withFirstName("TestFirstName").withLastName("TestLastName").withGroup("test2");

		app.contact().modify(contact);

		Contacts after = app.contact().all();

		assertThat(after.size(),equalTo(before.size()));
		assertThat(after,equalTo(
				before.withoutAdded(modifiedContact).withAdded(contact)));

	}
}
