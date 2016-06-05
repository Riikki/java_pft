package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;

public class NavigationHelper extends HelperBase {

	public NavigationHelper(WebDriver wd) {
		super(wd);
	}

	public void group() {
		if (isElementPresent(By.tagName("h1"))
						&& wd.findElement(By.tagName("h1")).getText().equals("Groups")
						&& isElementPresent(By.name("new"))) {
			return;
		}
		click(By.linkText("groups"));
	}

	public void home() {
		if (isElementPresent(By.name("maintable"))) {
			return;
		}
		click(By.linkText("home"));
	}

	public void allContacts() {
		new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
	}

	public void allContactsFromGroup(GroupData group){
		new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
	}
}
