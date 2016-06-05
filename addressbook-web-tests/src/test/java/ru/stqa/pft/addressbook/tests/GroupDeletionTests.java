package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.GroupDataProvider;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

	@BeforeMethod
	public void ensurePreCondition() throws IOException {
		app.goTo().group();
		if(app.db().groups().size() == 0){
			GroupData group = new GroupDataProvider().getOneGroupFromJson();
			app.group().create(new GroupData().withName(group.getName()).withHeader(group.getHeader()).withFooter(group.getFooter()));
		}
	}

	@Test
	public void testGroupDeletionTests() {
		Groups before = app.db().groups();

		GroupData group = before.iterator().next();
		app.group().delete(group);

		Groups after = app.db().groups();
		assertThat(after, equalTo(
				before.withoutAdded(group)));
		verifyGroupListInUI();
	}
}
