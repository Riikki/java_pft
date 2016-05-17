package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailsTests extends TestBase{

    @Test
    public void testContactEmails(){
        app.goTo().home();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllMails(),equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact){
        return Arrays.asList(contact.getGetEmail1(), contact.getGetEmail2(), contact.getGetEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
