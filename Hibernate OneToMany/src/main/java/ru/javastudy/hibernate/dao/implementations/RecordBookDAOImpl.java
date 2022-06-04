package ru.javastudy.hibernate.dao.implementations;

import org.hibernate.Session;
import ru.javastudy.hibernate.dao.entities.RecordBookEntity;
import ru.javastudy.hibernate.dao.entities.StudentEntity;
import ru.javastudy.hibernate.dao.interfaces.RecordBookDAO;

import java.util.List;

public class RecordBookDAOImpl implements RecordBookDAO {

    private Session session;

    public RecordBookDAOImpl(Session session)
    {
        this.session = session;
    }
    public List<RecordBookEntity> GetAll() {
        return null;
    }

    public void save(RecordBookEntity recordBook) {
        session.save(recordBook);
    }

    public void saveList(List<RecordBookEntity> recordBookList) {
        session.beginTransaction();
        for (RecordBookEntity recordBook : recordBookList) {
            session.save(recordBook);
        }
        session.getTransaction().commit();
    }

    public void setStudents(List<RecordBookEntity> recordBooks, List<StudentEntity> students) {
        for(int i = 0; i < recordBooks.size(); i++)
        {
            recordBooks.get(i).setStudent(students.get(i));
        }
    }
}
