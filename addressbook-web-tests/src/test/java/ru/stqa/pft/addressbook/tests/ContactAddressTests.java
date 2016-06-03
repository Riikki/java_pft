package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.ContactDataProvider;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

    @BeforeMethod
    public void ensurePreConditions() throws IOException {
        Contacts contacts = new ContactDataProvider().getContactsFromJson();
        for (ContactData contactData : contacts) {
            ContactData contact = app.db().getContact(contactData);
            if (contact == null) {
                GroupData group = app.db().groupByName(contactData.getGroup());
                if (group == null) {
                    group = new GroupData().withName(contactData.getGroup()).withHeader("").withFooter("");
                    app.db().createGroup(group);
                }
                contact = new ContactData().withFirstName(contactData.getFirstName()).withMiddleName(contactData.getMiddleName())
                        .withLastName(contactData.getLastName()).withNickName(contactData.getNickName()).withGroup(contactData.getGroup()).withAddress(contactData.getAddress())
                        .withHomePhone(contactData.getHomePhone()).withWorkPhone(contactData.getWorkPhone()).withMobilePhone(contactData.getMobilePhone())
                        .withEmail1(contactData.getEmail1()).withEmail2(contactData.getEmail2()).withEmail3(contactData.getEmail3());
                app.db().createContact(contact);
            }
        }
        app.goTo().home();
    }

    @Test(dataProvider = "validContactsFromJson", dataProviderClass = ContactDataProvider.class)
    public void testAddress(ContactData contact){
        ContactData contactData = app.db().getContact(contact);
        contact.withId(contactData.getId());
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
    }

}