package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.security.acl.Group;
import java.util.List;

public class GroupHelper extends HelperBase {

	private static final String noGroupName = "other";

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

	public GroupData getGroupFromDataProvider(Object[] groups) {
		GroupData group = new GroupData();
		for(Object groupObject : groups){
			group = (GroupData) groupObject;
		}
		return group;
	}

	public boolean checkIsGroupExistByName(String groupName) {
		Groups allGroups = this.all();
		for(GroupData group: allGroups){
			if(group.getName().equals(groupName)){
				return true;
			}
		}
		return false;
	}

	public ContactData ensureGroupExistAndCreate(ContactData contact) {
		String groupName = contact.getGroup();
		Groups allGroups = all();
		if(allGroups.size()>0){
			if(groupName==null){
				groupName = allGroups.iterator().next().getName();
				contact.withGroup(groupName);
			}
			if(!checkIsGroupExistByName(groupName)){
				create(new GroupData().withName(groupName));
			}
		}else{
			if(groupName != null){
				create(new GroupData().withName(groupName));
			}else{
				create(new GroupData().withName(noGroupName));
				contact.withGroup(noGroupName);
			}
		}
		return contact;
	}

	public int getPresentGroupId(GroupData group) {
		Groups allGroups = all();
		for(GroupData existingGroup: allGroups){
			if(existingGroup.hasSameData(group)){
				return existingGroup.getId();
			}
		}
		return 0;
	}
}