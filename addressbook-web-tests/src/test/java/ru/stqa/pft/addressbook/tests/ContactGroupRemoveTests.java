package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.dataproviders.ContactDataProvider;
import ru.stqa.pft.addressbook.dataproviders.GroupDataProvider;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupRemoveTests extends TestBase {

    private ContactData contact = null;
    private GroupData group;
    private SecureRandom random = new SecureRandom();

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        if(contacts.size() == 0){
            ContactData contact = new ContactDataProvider().getOneContactFromJson();
            app.db().createContact(contact);
        }
        if(groups.size() == 0){
            GroupData group = new GroupDataProvider().getOneGroupFromJson();
            app.db().createGroup(group);
        }
        for(ContactData contact: contacts){
            if(this.contact != null){
                break;
            }
            for(GroupData group: groups){
                if(contact.getGroups().contains(group)){
                    this.contact = contact;
                    this.group = group;
                    break;
                }
            }
        }
        if(this.contact == null){
            app.goTo().home();
            ContactData contact = contacts.iterator().next();
            this.group = app.db().groups().iterator().next();
            app.contact().addToGroup(contact, this.group);
            this.contact = contact;
        }

        app.goTo().home();
        app.goTo().allContacts();
    }

    @Test
    public void contactGroupAdd(){
        Contacts beforeAllGroups = app.db().contacts();

        app.goTo().allContactsFromGroup(this.group);
        Contacts beforeSelectedGroup = app.contact().all();

        app.contact().removeFromGroup(this.contact, this.group);

        Contacts afterSelectedGroup = app.contact().all();

        app.goTo().allContacts();
        Contacts afterAllGroups = app.db().contacts();

        assertThat(afterAllGroups, equalTo(
                beforeAllGroups));
        assertThat(afterSelectedGroup, equalTo(
                beforeSelectedGroup.withoutAdded(contact)));


    }
}
