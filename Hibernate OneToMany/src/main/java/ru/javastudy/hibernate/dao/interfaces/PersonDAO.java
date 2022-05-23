package ru.javastudy.hibernate.dao.interfaces;

import ru.javastudy.hibernate.dao.entities.PersonEntity;
import ru.javastudy.hibernate.dao.entities.StudentEntity;

import java.util.List;

public interface PersonDAO {
    List<PersonEntity> GetAll();

    List<PersonEntity> GetWithLetter(char letter);

    List<PersonEntity> GetWithLetterCriteria(char letter);

    void save(PersonEntity person);

    void saveList(List<PersonEntity> personList);

    void setStudents(List<PersonEntity> persons, List<StudentEntity> students);
}
