package ru.javastudy.hibernate.utils;

import ru.javastudy.hibernate.dao.entities.PersonEntity;
import ru.javastudy.hibernate.dao.entities.RecordBookEntity;
import ru.javastudy.hibernate.dao.entities.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class EntitiesGenerator {

    public static List<PersonEntity> GeneratePersonList()
    {
        List<PersonEntity> personEntities = new ArrayList<PersonEntity>() ;
        String [] LastName_Array = new String[] {"Ковалева", "Поляков", "Пименова", "Устинова", "Васильев",
                "Лукин", "Кузина", "Абрамова", "Фомин", "Голубев"};
        String [] FirstName_Array = new String[] {"Ксения", "Дмитрий", "Анастасия", "Ольга", "Константин",
                "Владимир", "Екатерина", "Марина", "Виктор", "Сергей"};
        String [] MiddleName_Array = new String[] {"Владимировна", "Анатольевич", "Сергеевна", "Николаевна", "Ярославович",
                "Федорович", "Андреевна", "Максимовна", "Евгеньевич", "Александрович"};
        for (int i = 0; i < 10; i++)
        {
            PersonEntity personEntity = new PersonEntity();
            personEntity.setLastName(LastName_Array[i]);
            personEntity.setFirstName(FirstName_Array[i]);
            personEntity.setMiddleName(MiddleName_Array[i]);
            personEntity.setPassportSeria((int) (Math.random() * 10000));
            personEntity.setPassportNumber((int) (Math.random() * 1000000));
            personEntities.add(personEntity);
        }
        return personEntities;
    }

    public static List<RecordBookEntity> GenerateRecordBookList()
    {
        List<RecordBookEntity> recordBookEntities = new ArrayList<RecordBookEntity>();
        int countBookEntity = (int) (Math.random() * 5) + 4;
        for(int i = 0; i < countBookEntity; i++)
        {
            RecordBookEntity recordBookEntity = new RecordBookEntity();
            recordBookEntity.setCode((int) (Math.random() * 100000));
            recordBookEntities.add(recordBookEntity);
        }
        return recordBookEntities;
    }

    public static List<StudentEntity> GenerateStudentList()
    {
        List<StudentEntity> studentEntities = new ArrayList<StudentEntity>();
        for(int i = 0; i < 9; i++)
        {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setGroupName(i % 2 == 0 ? "РИМ-110961" : "РИМ-110960");
            studentEntities.add(studentEntity);
        }
        return studentEntities;
    }
}
