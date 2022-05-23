package ru.javastudy.hibernate.utils;

import ru.javastudy.hibernate.dao.entities.PersonEntity;
import ru.javastudy.hibernate.dao.entities.StudentEntity;

import java.util.List;

public class MyPrint {
    public static void PrintPersons(List<PersonEntity> personList)
    {
        System.out.println("ФИО, номер и серия пасспорта:");
        for (int i = 0; i < personList.size(); i++) {
            PersonEntity person = personList.get(i);
            System.out.println(i + 1 + ") " +
                    person.getLastName() + " " +
                    person.getFirstName() + " " +
                    person.getMiddleName() + " " +
                    person.getPassportNumber() + " " +
                    person.getPassportSeria());
        }
        System.out.println();
    }

    public static void PrintStudents(List<StudentEntity> studentList)
    {
        System.out.println("Студенты, группа, Id, ФИО, номер и серия пасспорта:");
        for (int i = 0; i < studentList.size(); i++) {
            StudentEntity student = studentList.get(i);
            System.out.println(i + 1 + ") " +
                    student.getId() + " " +
                    student.getGroupName() + " " +
                    student.getPerson().getMiddleName() + " " +
                    student.getPerson().getFirstName() + " " +
                    student.getPerson().getMiddleName() + " " +
                    student.getPerson().getPassportNumber() + " " +
                    student.getPerson().getPassportSeria());
        }
        System.out.println();
    }
}
