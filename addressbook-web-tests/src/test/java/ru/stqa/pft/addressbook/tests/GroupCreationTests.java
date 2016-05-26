package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.ContactDataProvider;
import ru.stqa.pft.addressbook.dataproviders.GroupDataProvider;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {



	@Test (dataProvider = "validGroupsFromJson", dataProviderClass = GroupDataProvider.class)
	public void testGroupCreationTests(GroupData group) {
		app.goTo().group();
		Groups before = app.group().all();
		app.group().create(group);
		assertThat(app.group().count(), equalTo(before.size() + 1));
		Groups after = app.group().all();
		assertThat(after, equalTo(
				before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
	}


	@Test(enabled = false, dataProvider = "validGroupsFromJson", dataProviderClass = GroupDataProvider.class)
	public void testBadGroupCreationTests(GroupData group) {
		app.goTo().group();
		Groups before = app.group().all();
		app.group().create(group);
		assertThat(app.group().count(), equalTo(before.size()));
		Groups after = app.group().all();
		assertThat(after,equalTo(
				before.withoutAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
	}

}