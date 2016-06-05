package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.ContactDataProvider;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactViewContactTests extends TestBase{

    private ContactData contact;

    @BeforeMethod
    public void ensurePreConditions() throws IOException {
        Contacts contacts = app.db().contacts();
        if(contacts.size()>0){
            this.contact = contacts.iterator().next();
        }else{
            ContactData contact = new ContactDataProvider().getOneContactFromJson();
            app.db().createContact(contact);
            this.contact = app.db().getContact(contact);

        }
        app.goTo().home();
    }

    @Test
    public void testViewContact(){
        ContactData contact = this.contact;

        String contactInfoFromEditForm = app.contact().infoFromEditFormToString(contact);
        String contactInfoFromViewPage = app.contact().infoFromViewPage(contact);

        assertThat(contactInfoFromViewPage,equalTo(contactInfoFromEditForm));
    }
}