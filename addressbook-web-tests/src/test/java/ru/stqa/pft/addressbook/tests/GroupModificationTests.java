package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

	@Test
	public void testGroupModificationTests() {
		app.getNavigationHelper().gotoGroupPage();
		if (! app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createGroup(new GroupData("test1", null, null));
		}
		app.getGroupHelper().selectGroup();
		app.getGroupHelper().editGroup();
		app.getGroupHelper().fillGroupForm(new GroupData("Edit_test1", "Edit_test2", "Edit_test3"));
		app.getGroupHelper().updateGroup();
		app.getGroupHelper().returnToGroupPage();
	}

}
