package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

	@Test
	public void testGroupDeletionTests() {
		app.getNavigationHelper().gotoGroupPage();
		if (! app.getGroupHelper().isThereAGroup()) {
		app.getGroupHelper().createGroup(new GroupData("test1", null, null));
		}
		app.getGroupHelper().selectGroup();
		app.getGroupHelper().deletedSelectedGroups();
		app.getNavigationHelper().gotoGroupPage();
	}

}
