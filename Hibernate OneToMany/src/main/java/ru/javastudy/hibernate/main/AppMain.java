package ru.javastudy.hibernate.main;

import org.hibernate.Query;
import org.hibernate.Session;
import ru.javastudy.hibernate.dao.entities.PersonEntity;
import ru.javastudy.hibernate.dao.entities.RecordBookEntity;
import ru.javastudy.hibernate.dao.entities.StudentEntity;
import ru.javastudy.hibernate.dao.implementations.PersonDAOImpl;
import ru.javastudy.hibernate.dao.implementations.RecordBookDAOImpl;
import ru.javastudy.hibernate.dao.implementations.StudentDAOImpl;
import ru.javastudy.hibernate.utils.EntitiesGenerator;
import ru.javastudy.hibernate.utils.HibernateSessionFactory;
import ru.javastudy.hibernate.utils.MyPrint;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.util.List;

public class AppMain {

    @PersistenceUnit
    static EntityManager emf;

    public static void main(String[] args) {
        EntitiesGenerator entitiesGenerator = new EntitiesGenerator();

        List<PersonEntity> persons = entitiesGenerator.GeneratePersonList();
        List<RecordBookEntity> recordBooks = entitiesGenerator.GenerateRecordBookList();
        List<StudentEntity> students = entitiesGenerator.GenerateStudentList();

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        PersonDAOImpl personDAO = new PersonDAOImpl(session);
        StudentDAOImpl studentDAO = new StudentDAOImpl(session);
        RecordBookDAOImpl recordBookDAO = new RecordBookDAOImpl(session);

        personDAO.setStudents(persons, students);
        recordBookDAO.setStudents(recordBooks, students);

        personDAO.saveList(persons);
        studentDAO.saveList(students);
        recordBookDAO.saveList(recordBooks);

        MyPrint myPrint = new MyPrint();
        myPrint.PrintPersons(personDAO.GetAll());

        myPrint.PrintPersons(personDAO.GetWithLetter('а'));
        myPrint.PrintPersons(personDAO.GetWithLetterCriteria('а'));

        myPrint.PrintStudents(studentDAO.GetWithoutRecordBook());
        myPrint.PrintStudents(studentDAO.GetWithoutRecordBookCriteria());


        session.getTransaction().commit();

        session.close();


//        Session session = HibernateSessionFactory.getSessionFactory().openSession();
//
//        session.beginTransaction();
//
//        ContactEntity contactEntity = new ContactEntity();
//
//        contactEntity.setBirthDate(new java.util.Date());
//        contactEntity.setFirstName("Ivan");
//        contactEntity.setLastName("Petrov");
//        session.save(contactEntity);
//
//        ContactTelDetailEntity contactTelDetail = new ContactTelDetailEntity();
//
//        contactTelDetail.setTelNumber("84884848");
//        contactTelDetail.setTelType("home");
//        contactEntity.addContactTelDetail(contactTelDetail);
//        session.save(contactTelDetail);
//
//        session.getTransaction().commit();
//
////        Query query = session.createQuery("from ContactEntity where firstName = :paramName");
////        query.setParameter("paramName", "Nick");
////        List list = query.list();
//
//        ContactDAOImpl contactDAO = new ContactDAOImpl();
//        contactDAO.setSession(session);
//
//        Transaction tx = session.beginTransaction();
//
//        List<ContactEntity> contacts = contactDAO.findAll();
//        for (ContactEntity contact : contacts) {
//            System.out.println(contact);
//        }
//
//        listContactsWithDetail(contacts);
//
//        tx.commit();
//        session.close();

    }
}
