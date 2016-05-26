package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.GroupDataProvider;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

	@BeforeMethod
	public void ensurePreCondition(Object[] groups) {
		app.goTo().group();
		GroupData group = app.group().getGroupFromDataProvider(groups);
		int existedGroupId = app.group().getPresentGroupId(group);
		if (existedGroupId == 0) {
			app.group().create(new GroupData().withName(group.getName()));
			existedGroupId = app.group().getPresentGroupId(group);
			group.withId(existedGroupId);
		}else{
			group.withId(existedGroupId);
		}
		groups[0] = group;

	}

	@Test(dataProvider = "validGroupsFromJson", dataProviderClass = GroupDataProvider.class)
	public void testGroupDeletionTests(GroupData group) {
		Groups before = app.group().all();
		app.group().delete(group);

		assertThat(app.group().count(), equalTo(before.size()-1));
		Groups after = app.group().all();
		assertThat(after, equalTo(
				before.withoutAdded(group)));
	}
}
