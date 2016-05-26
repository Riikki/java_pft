package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.ContactDataProvider;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailsTests extends TestBase{

    @BeforeMethod
    public void ensurePreConditions(Object[] contacts){
        app.goTo().home();
        ContactData contact = app.contact().getContactFromDataProvider(contacts);
        app.goTo().group();
        contact = app.group().ensureGroupExistAndCreate(contact);
        app.goTo().home();
        int existedContactId = app.contact().getPresentContactId(contact);
        if (existedContactId == 0) {
            app.contact().create(contact);
            existedContactId = app.contact().getPresentContactId(contact);
            contact.withId(existedContactId);
        }else{
            contact.withId(existedContactId);
        }
        contacts[0] = contact;
    }
    @Test(dataProvider = "validContactsFromJson", dataProviderClass = ContactDataProvider.class)
    public void testContactEmails(ContactData contact){
        ContactData contactInfoFromHomePage = app.contact().infoFromHomePage(contact);
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contactInfoFromHomePage.getAllMails(),equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact){
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}