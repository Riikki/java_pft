package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.GroupDataProvider;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

	@BeforeMethod
	public void ensurePreCondition() throws IOException {
		app.goTo().group();
		if(app.db().groups().size() == 0){
			GroupData group = new GroupDataProvider().getOneGroupFromJson();
			app.group().create(new GroupData().withName(group.getName()).withHeader(group.getHeader()).withFooter(group.getFooter()));
		}
	}

	@Test
	public void testGroupModificationTests() {
		Groups before = app.db().groups();

		GroupData modifiedGroup = before.iterator().next();
		GroupData group = new GroupData()
				.withId(modifiedGroup.getId()).withName(String.format("%s-%s",modifiedGroup.getName(),"modified"))
				.withHeader(modifiedGroup.getHeader()).withFooter(modifiedGroup.getFooter());

		app.group().modify(group);

		assertThat(app.group().count(), equalTo(before.size()));
		Groups after = app.db().groups();
		assertThat(after,equalTo(
				before.withoutAdded(modifiedGroup).withAdded(group)));
	}

}