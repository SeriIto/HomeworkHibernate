package ru.javastudy.hibernate.main;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import ru.javastudy.hibernate.dao.entities.PersonEntity;
import ru.javastudy.hibernate.dao.entities.RecordBookEntity;
import ru.javastudy.hibernate.dao.entities.StudentEntity;
import ru.javastudy.hibernate.dao.implementations.PersonDAOImpl;
import ru.javastudy.hibernate.dao.implementations.RecordBookDAOImpl;
import ru.javastudy.hibernate.dao.implementations.StudentDAOImpl;
import ru.javastudy.hibernate.dao.interfaces.StudentEntityDeleteEventListener;
import ru.javastudy.hibernate.utils.EntitiesGenerator;
import ru.javastudy.hibernate.utils.HibernateSessionFactory;
import ru.javastudy.hibernate.utils.MyPrint;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AppMain {

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

        //Сохранение данных
        personDAO.saveList(persons);
        studentDAO.saveList(students);
        recordBookDAO.saveList(recordBooks);

        MyPrint myPrint = new MyPrint();
        myPrint.PrintPersons(personDAO.GetAll());

        //вывод только с "а"
        myPrint.PrintPersons(personDAO.GetWithLetter('а'));
        myPrint.PrintPersons(personDAO.GetWithLetterCriteria('а'));

        //вывлд без зачетки
        myPrint.PrintStudents(studentDAO.GetWithoutRecordBook());
        myPrint.PrintStudents(studentDAO.GetWithoutRecordBookCriteria());


        session.getTransaction().commit();
        session.close();

        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        //удаление
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ru.javastudy.hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManagerFactory.unwrap(SessionFactoryImplementor.class)
                .getServiceRegistry()
                .getService(EventListenerRegistry.class)
                .prependListeners(EventType.PRE_DELETE, new StudentEntityDeleteEventListener());

        studentDAO = new StudentDAOImpl(session);

        List<StudentEntity> allStudents = studentDAO.GetAll();
        entityManager.getTransaction().begin();
        for (StudentEntity studentEntity : allStudents)
        {
            entityManager.remove(entityManager.getReference(StudentEntity.class, studentEntity.getId()));
        }
        entityManager.getTransaction().commit();

        session.getTransaction().commit();
        session.close();
    }
}
