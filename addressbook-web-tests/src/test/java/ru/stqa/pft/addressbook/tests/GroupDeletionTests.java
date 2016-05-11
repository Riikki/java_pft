package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

	@BeforeMethod
	public void ensurePreCondition() {
		app.goTo().group();
		if (app.group().list().size() == 0) {
			app.group().create(new GroupData().withName("test1"));
		}
	}

	@Test
	public void testGroupDeletionTests() {
		app.goTo().group();
		if (! app.group().isThereAGroup()) {
		app.group().create(new GroupData().withName("test1"));
		}
		List<GroupData> before = app.group().list();
		int index = before.size() - 1;
		app.group().delete(index);
		List<GroupData> after = app.group().list();
		Assert.assertEquals(after.size(), index);

		before.remove(before.size() - 1);
		Assert.assertEquals(before, after);
		}
}

