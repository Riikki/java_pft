package ru.stqa.pft.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreationTests() {
		initContactCreation();
		fillContactForm(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "TestNickname", "+111111111111", "testmail@test.com"));
		submitContactCreation();
		goToHomePage();
	}
}


