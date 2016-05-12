package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

	public GroupHelper(WebDriver wd) {
		super(wd);
	}

	public void initGroupCreation() {
		click(By.name("new"));
	}

	public void fillGroupForm(GroupData groupData) {
		type(By.name("group_name"), groupData.getName());
		type(By.name("group_header"), groupData.getHeader());
		type(By.name("group_footer"), groupData.getFooter());
	}

	public void submitGroupCreation() {
		click(By.name("submit"));
	}

	public void returnToGroupPage() {
		click(By.linkText("group page"));
	}

	public void deletedSelectedGroups() {
		click(By.name("delete"));
	}

	public void editGroup() {
		click(By.name("edit"));
	}

	public void updateGroup(){
		wd.findElement(By.name("update")).click();
	}

	public void create(GroupData group) {
		initGroupCreation();
		fillGroupForm(group);
		submitGroupCreation();
		groupCache = null;
		returnToGroupPage();
	}

	public void modify(GroupData group) {
		selectGroupById(group.getId());
		editGroup();
		fillGroupForm(group);
		updateGroup();
		groupCache = null;
		returnToGroupPage();
	}

	public void delete(GroupData group) {
		selectGroupById(group.getId());
		deletedSelectedGroups();
		groupCache = null;
		returnToGroupPage();
	}

	private void selectGroupById(int id) {
		wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
	}

	public boolean isThereAGroup() {
		return isElementPresent(By.name("selected[]"));
	}

	public int count() {
		return wd.findElements(By.name("selected[]")).size();
	}

	private Groups groupCache = null;

	public Groups all() {
		if(groupCache != null){
			return new Groups(groupCache);
		}
		Groups groupCache = new Groups();
		List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
		for (WebElement element : elements) {
			String name = element.getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			GroupData group = new GroupData().withId(id).withName(name);
			groupCache.add(group);
		}
		return new Groups(groupCache);
	}
}
