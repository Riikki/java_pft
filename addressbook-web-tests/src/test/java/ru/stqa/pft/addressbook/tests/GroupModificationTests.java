package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

	@BeforeMethod
	public void ensurePreCondition() {
		app.goTo().group();
		if (app.group().all().size() == 0) {
			app.group().create(new GroupData().withName("test1"));
		}
	}

	@Test
	public void testGroupModificationTests() {
		Groups before = app.group().all();
		GroupData modifiedGroup = before.iterator().next();
		GroupData group = new GroupData().
				withId(modifiedGroup.getId()).withName("Edit_test1").withHeader("Edit_test2").withFooter("Edit_test3");
		app.group().modify(group);
		Groups after = app.group().all();

		assertThat(after.size(),equalTo(before.size()));
		assertThat(after,equalTo(
				before.withoutAdded(modifiedGroup).withAdded(group)));
	}

}
