package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

    @BeforeMethod
    public void ensurePreConditions(){
        app.goTo().home();
        if (app.contact().all().size() == 0) {
            app.goTo().group();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withName("TestGroup"));
            }
            app.goTo().home();
            app.contact().create(new ContactData().withDefaultData());
        }
    }

    @Test
    public void testAddress(){
        app.goTo().home();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
    }

}
