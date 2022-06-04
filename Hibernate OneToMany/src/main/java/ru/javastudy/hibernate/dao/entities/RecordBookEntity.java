package ru.javastudy.hibernate.dao.entities;

import javax.persistence.*;

@Entity
@Table(name = "record_book")
public class RecordBookEntity {
    private long id;
    private int code;

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
    @Column(name = "code", nullable = false, insertable = true, updatable = true, unique = true)
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private StudentEntity student;

    @OneToOne (mappedBy = "recordBook")

    public StudentEntity getStudent() {
        return this.student;
    }
    public void setStudent(StudentEntity student) {
        student.setRecordBook(this);
        this.student = student;
    }


    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        RecordBookEntity that = (RecordBookEntity) o;
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
