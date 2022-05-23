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
        return session.createQuery("from PersonEntity").list();
    }

    public List<PersonEntity> GetWithLetter(char letter) {
        return session.createQuery("from PersonEntity pe " +
                        "where pe.lastName like :letter " +
                        "or pe.firstName like :letter " +
                        "or pe.middleName like :letter")
                .setParameter("letter", "%" + letter + "%")
                .list();
    }

    public List<PersonEntity> GetWithLetterCriteria(char letter)
    {
        Criteria criteria = session.createCriteria(PersonEntity.class);
        SimpleExpression lastName = Restrictions.like("lastName", "%" + letter + "%");
        SimpleExpression firstName = Restrictions.like("firstName", "%" + letter + "%");
        SimpleExpression middleName = Restrictions.like("middleName", "%" + letter + "%");

        return criteria.add(Restrictions.or(lastName, firstName, middleName)).list();
    }

    public void save(PersonEntity person) {
        session.save(person);
    }

    public void saveList(List<PersonEntity> personList) {
        for (PersonEntity person : personList) {
            session.save(person);
        }
    }

    public void setStudents(List<PersonEntity> persons, List<StudentEntity> students) {
        for (int i = 0; i < students.size(); i++)
        {
            persons.get(i).addStudent(students.get(i));
        }
    }

}
