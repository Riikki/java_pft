package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.ContactDataProvider;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

	@BeforeMethod
	public void ensurePreConditions(Object[] contacts) {
		ContactData contact = app.contact().getContactFromDataProvider(contacts);
		app.goTo().group();
		contact = app.group().ensureGroupExistAndCreate(contact);
		app.goTo().home();

		contacts[0] = contact;
	}

	@Test(dataProvider = "validContactsFromJson", dataProviderClass = ContactDataProvider.class)
	public void testContactCreationTests(ContactData contact) {
		Contacts before = app.contact().all();
		app.contact().create(contact);
		Contacts after = app.contact().all();

		assertThat(after.size(),equalTo(before.size() + 1));
		assertThat(after,equalTo(
				before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
	}

}