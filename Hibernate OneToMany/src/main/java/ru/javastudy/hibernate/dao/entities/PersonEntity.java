package ru.javastudy.hibernate.dao.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class PersonEntity implements Serializable {
    private long id;
    private int passportSeria;
    private int passportNumber;
    private String lastName;
    private String firstName;
    private String middleName;


    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "passport_seria", nullable = false, insertable = true, updatable = true)
    public int getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(int passportSeria) {
        this.passportSeria = passportSeria;
    }

    @Basic
    @Column(name = "passport_number", nullable = false, insertable = true, updatable = true)
    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Basic
    @Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = 60)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "first_name", nullable = false, insertable = true, updatable = true, length = 60)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "middle_name", nullable = false, insertable = true, updatable = true, length = 60)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    private Set<StudentEntity> studentEntitySet = new HashSet<StudentEntity>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<StudentEntity> getStudentEntitySet() {
        return this.studentEntitySet;
    }

    public void setStudentEntitySet(Set<StudentEntity> studentEntitySet) {
        this.studentEntitySet = studentEntitySet;
    }

    public void addStudent(StudentEntity student) {
        student.setPerson(this);
        getStudentEntitySet().add(student);
    }

    public void removeStudent(StudentEntity student) {
        getStudentEntitySet().remove(student);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        PersonEntity that = (PersonEntity) o;
//
//        if (id != that.id) return false;
//        if (version != that.version) return false;
//        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
//        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
//        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
//
//        return true;
//    }

//    @Override
//    public int hashCode() {
//        int result = id;
//        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
//        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
//        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
//        result = 31 * result + version;
//        return result;
//    }
}
