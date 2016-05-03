package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

	@Test
	public void testGroupDeletionTests() {
		app.getNavigationHelper().gotoGroupPage();
		int before  = app.getGroupHelper().getGroupCount();
		if (! app.getGroupHelper().isThereAGroup()) {
		app.getGroupHelper().createGroup(new GroupData("test1", null, null));
		}
		app.getGroupHelper().selectGroup(before - 1);
		app.getGroupHelper().deletedSelectedGroups();
		app.getNavigationHelper().gotoGroupPage();
		int after  = app.getGroupHelper().getGroupCount();
		Assert.assertEquals(after, before - 1);
	}

}
