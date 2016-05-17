package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactViewContactTests extends TestBase{

    @Test
    public void testViewContact(){
        app.goTo().home();
        ContactData contact = app.contact().all().iterator().next();
        String contactInfoFromEditForm = app.contact().infoFromEditFormToString(contact);

        String contactInfoFromViewPage = app.contact().infoFromViewPage(contact);
        assertThat(contactInfoFromViewPage,equalTo(contactInfoFromEditForm));
    }
}
