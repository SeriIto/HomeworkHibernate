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
        return null;
    }

    public void save(StudentEntity student) {
        session.save(student);
    }

    public void saveList(List<StudentEntity> studentList) {
        for (StudentEntity student : studentList) {
            session.save(student);
        }
    }

    public List<StudentEntity> GetWithoutRecordBook() {
        return session.createQuery("from StudentEntity se " +
                "where se.recordBook is null").list();
    }

    public List<StudentEntity> GetWithoutRecordBookCriteria() {
        Criteria criteria = session.createCriteria(StudentEntity.class);
        return criteria.add(Restrictions.isNull("recordBook"))
                .list();
    }
}
