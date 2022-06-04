package ru.javastudy.hibernate.dao.implementations;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ru.javastudy.hibernate.dao.entities.StudentEntity;
import ru.javastudy.hibernate.dao.interfaces.StudentDAO;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Session session;

    public StudentDAOImpl(Session session)
    {
        this.session = session;
    }

    public List<StudentEntity> GetAll() {
        return session.createQuery("from StudentEntity").list();
    }

    public void save(StudentEntity student) {
        session.save(student);
    }

    public void saveList(List<StudentEntity> studentList) {
        session.beginTransaction();
        for (StudentEntity student : studentList) {
            session.save(student);
        }
        session.getTransaction().commit();
    }

    public List<StudentEntity> GetWithoutRecordBook() {
        session.beginTransaction();
        List list = session.createQuery("from StudentEntity se " +
                "where se.recordBook is null").list();
        session.getTransaction().commit();
        return list;
    }

    public List<StudentEntity> GetWithoutRecordBookCriteria() {

        session.beginTransaction();
        Criteria criteria = session.createCriteria(StudentEntity.class);
        List recordBook = criteria.add(Restrictions.isNull("recordBook"))
                .list();
        session.getTransaction().commit();
        return recordBook;
    }

    public void deleteAll() {
        session.createQuery("delete from StudentEntity");
    }
}
