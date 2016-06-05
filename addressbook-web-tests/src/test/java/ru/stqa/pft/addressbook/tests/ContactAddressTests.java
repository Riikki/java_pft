package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.ContactDataProvider;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

    ContactData contact;
    @BeforeTest
    public void ensurePreConditions() throws IOException {
        if(app.db().contacts().size() > 0){
            this.contact = app.db().contacts().iterator().next();
        }else{
            ContactData contact = new ContactDataProvider().getOneContactFromJson();
            app.db().createContact(contact);
            this.contact = app.db().getContact(contact);
        }
        app.goTo().home();
    }

    @Test
    public void testAddress(){
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(this.contact);
        assertThat(this.contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
    }

}