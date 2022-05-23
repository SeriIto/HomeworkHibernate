package ru.javastudy.hibernate.dao.interfaces;

import ru.javastudy.hibernate.dao.entities.StudentEntity;

import java.util.List;

public interface StudentDAO {
    List<StudentEntity> GetAll();

    void save(StudentEntity student);

    void saveList(List<StudentEntity> studentList);

    List<StudentEntity> GetWithoutRecordBook();

    List<StudentEntity> GetWithoutRecordBookCriteria();
}
