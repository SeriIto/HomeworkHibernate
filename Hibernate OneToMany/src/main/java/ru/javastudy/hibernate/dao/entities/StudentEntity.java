package ru.javastudy.hibernate.dao.entities;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class StudentEntity {
    private long id;
    private String groupName;

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
    @Column(name = "group_name", nullable = false, insertable = true, updatable = true, length = 20)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String group_name) {
        this.groupName = group_name;
    }

    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = "person_id")
    public PersonEntity getPerson() {return this.person;}

    public void setPerson(PersonEntity person) { this.person = person;}

    private RecordBookEntity recordBook;

    @OneToOne
    @JoinColumn(name = "record_book_id")

    public RecordBookEntity getRecordBook() {
        return this.recordBook;
    }
    public void setRecordBook(RecordBookEntity recordBook) {
        this.recordBook = recordBook;
    }


    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        StudentEntity that = (StudentEntity) o;
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
