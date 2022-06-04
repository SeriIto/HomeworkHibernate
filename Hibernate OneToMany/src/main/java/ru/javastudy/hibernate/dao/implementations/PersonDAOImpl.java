package ru.javastudy.hibernate.dao.implementations;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import ru.javastudy.hibernate.dao.entities.PersonEntity;
import ru.javastudy.hibernate.dao.entities.StudentEntity;
import ru.javastudy.hibernate.dao.interfaces.PersonDAO;

import java.util.List;

public class PersonDAOImpl implements PersonDAO {
    private Session session;

    public PersonDAOImpl(Session session)
    {
        this.session = session;
    }

    public List<PersonEntity> GetAll() {
        session.beginTransaction();
        List list = session.createQuery("from PersonEntity").list();
        session.getTransaction().commit();
        return list;
    }

    public List<PersonEntity> GetWithLetter(char letter) {

        session.beginTransaction();
        List list = session.createQuery("from PersonEntity pe " +
                        "where pe.lastName like :letter " +
                        "or pe.firstName like :letter " +
                        "or pe.middleName like :letter")
                .setParameter("letter", "%" + letter + "%")
                .list();
        session.getTransaction().commit();
        return list;
    }

    public List<PersonEntity> GetWithLetterCriteria(char letter)
    {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(PersonEntity.class);
        SimpleExpression lastName = Restrictions.like("lastName", "%" + letter + "%");
        SimpleExpression firstName = Restrictions.like("firstName", "%" + letter + "%");
        SimpleExpression middleName = Restrictions.like("middleName", "%" + letter + "%");

        List list = criteria.add(Restrictions.or(lastName, firstName, middleName)).list();
        session.getTransaction().commit();

        return list;
    }

    public void save(PersonEntity person) {
        session.save(person);
    }

    public void saveList(List<PersonEntity> personList) {
        session.beginTransaction();
        for (PersonEntity person : personList) {
            session.save(person);
        }
        session.getTransaction().commit();
    }

    public void setStudents(List<PersonEntity> persons, List<StudentEntity> students) {
        for (int i = 0; i < students.size(); i++)
        {
            persons.get(i).addStudent(students.get(i));
        }
    }

}
