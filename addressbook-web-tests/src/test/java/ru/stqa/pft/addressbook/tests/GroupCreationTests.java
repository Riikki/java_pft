package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.GroupDataProvider;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

	@Test (dataProvider = "validGroupsFromJson", dataProviderClass = GroupDataProvider.class)
	public void testGroupCreationTests(GroupData group) {
		app.goTo().group();

		Groups before = app.db().groups();

		app.group().create(group);

		Groups after = app.db().groups();
		assertThat(after, equalTo(
				before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
		verifyGroupListInUI();
	}


	@Test(enabled = false, dataProvider = "validGroupsFromJson", dataProviderClass = GroupDataProvider.class)
	public void testBadGroupCreationTests(GroupData group) {
		app.goTo().group();

		Groups before = app.db().groups();

		app.group().create(group);

		Groups after = app.db().groups();
		assertThat(after,equalTo(
				before.withoutAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
		verifyGroupListInUI();
	}

}