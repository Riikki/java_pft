package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;

public class GroupModificationTests extends TestBase {

	@Test
	public void testGroupModificationTests() {
		app.getNavigationHelper().gotoGroupPage();
		if (! app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createGroup(new GroupData("test1", null, null));
		}
		List<GroupData> before = app.getGroupHelper().getGroupList();
		app.getGroupHelper().selectGroup(before.size() - 1);
		app.getGroupHelper().editGroup();
		GroupData group = new GroupData(before.get(before.size()-1).getId(),"Edit_test1", "Edit_test2", "Edit_test3");
		app.getGroupHelper().fillGroupForm(group);
		app.getGroupHelper().updateGroup();
		app.getGroupHelper().returnToGroupPage();
		List<GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size());

		before.remove(before.size()-1);
		before.add(group);
		Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}

}
