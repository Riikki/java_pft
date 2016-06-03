package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main (String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
//            saveAsCsv(contacts, new File(file));
        }else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        }else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        }else {
            save(contacts, new File(file));
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try(Writer writer = new FileWriter(file)){
            for (ContactData contact : contacts) {
                String row = new StringBuilder(contact.getFirstName())
                        .append(contact.getMiddleName())
                        .append(contact.getLastName())
                        .append(contact.getNickName())
                        .append(contact.getHomePhone())
                        .append(contact.getMobilePhone())
                        .append(contact.getWorkPhone())
                        .append(contact.getAddress())
                        .append(contact.getEmail1())
                        .append(contact.getEmail2())
                        .append(contact.getEmail3())
                        .toString();
                writer.write(row);
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("TestFirstName %s",i))
                    .withMiddleName(String.format("TestMiddleName %s",i))
                    .withLastName(String.format("TestLastName %s",i))
                    .withNickName(String.format("TestNickName %s",i))
                    .withGroup(String.format("TestGroup %s",i))
                    .withHomePhone(String.format("111 %1$s%1$s%1$s",i))
                    .withMobilePhone(String.format("+7(111) %1$s%1$s%1$s",i))
                    .withWorkPhone(String.format("111-%1$s%1$s%1$s",i))
                    .withAddress(String.format("TestAddress %s",i))
                    .withEmail1(String.format("email1.test-%s@test.com",i))
                    .withEmail2(String.format("email2.test-%s@mail.ru",i))
                    .withEmail3(String.format("email3.test-%s@mail.com",i))
            );
        }
        return contacts;
    }
}