package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

	@BeforeMethod
	public void ensurePreConditions() {
		app.goTo().group();
		if (app.group().all().size() == 0) {
			app.group().create(new GroupData().withName("TestGroup"));
		}
		app.goTo().home();
	}

	@Test
	public void testContactCreationTests() {

		Contacts before = app.contact().all();
		File photo = new File("src/test/resources/stru.png");
		ContactData contact = new ContactData().withDefaultData().withPhoto(photo);
		app.contact().create(contact);
		Contacts after = app.contact().all();

		assertThat(after.size(),equalTo(before.size() + 1));

		assertThat(after,equalTo(
				before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
		}

}


