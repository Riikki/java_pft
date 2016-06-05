package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public GroupData groupByName(String groupName){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from GroupData where group_name = '" + groupName + "'").list();
        session.close();

        if(result.size() == 0){
            return null;
        }
        return (GroupData) result.get(0);
    }

    public Contacts contacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from ContactData where deprecated = null" ).list();
        return new Contacts(result);
    }

    public ContactData getContact(ContactData contact){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List result = session.createQuery( "from ContactData where deprecated = null AND firstname = '" + contact.getFirstName() + "'AND lastname = '" + contact.getLastName() + "'").list();
        session.close();

        if(result.size() == 0){
            return null;
        }
        return (ContactData) result.get(0);
    }

    public void createGroup(GroupData group) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(group);

        session.getTransaction().commit();
        session.close();
    }

    public void createContact(ContactData contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(contact);

        session.getTransaction().commit();
        session.close();
    }
}
