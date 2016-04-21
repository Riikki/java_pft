package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreationTests() {
		initContactCreation();
		fillContactForm(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "TestNickname", "+111111111111", "testmail@test.com"));
		submitContactCreation();
		goToHomePage();
	}
}


