package ru.javastudy.hibernate.dao.interfaces;

import ru.javastudy.hibernate.dao.entities.RecordBookEntity;
import ru.javastudy.hibernate.dao.entities.StudentEntity;

import java.util.List;

public interface RecordBookDAO {

    List<RecordBookEntity> GetAll();

    void save(RecordBookEntity recordBook);

    void saveList(List<RecordBookEntity> recordBookList);

    void setStudents(List<RecordBookEntity> recordBooks, List<StudentEntity> students);
}
