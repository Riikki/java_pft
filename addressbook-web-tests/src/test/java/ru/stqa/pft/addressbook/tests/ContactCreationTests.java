package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

	@BeforeMethod
	public void ensurePreConditions() {
		app.goTo().group();
		if (app.group().all().size() == 0) {
			app.group().create(new GroupData().withName("test2"));
		}
		app.goTo().home();
	}

	@Test
	public void testContactCreationTests() {

		Contacts before = app.contact().all();
		ContactData contact = new ContactData()
				.withFirstName("TestFirstName").withMiddleName("TestMiddleName").withLastName("TestLastName").withNickName("TestNickname")
				.withMobilePhone("+111111111111").withGroup("test2");
		app.contact().create(contact);
		Contacts after = app.contact().all();

		assertThat(after.size(),equalTo(before.size() + 1));

		assertThat(after,equalTo(
				before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
		}


}


