package ru.javastudy.hibernate.dao.interfaces;

import org.hibernate.event.internal.DefaultDeleteEventListener;
import org.hibernate.event.spi.DeleteEventListener;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import ru.javastudy.hibernate.dao.entities.PersonEntity;
import ru.javastudy.hibernate.dao.entities.StudentEntity;

public class StudentEntityDeleteEventListener implements PreDeleteEventListener{

    public static final StudentEntityDeleteEventListener INSTANCE =
            new StudentEntityDeleteEventListener();

    @Override
    public boolean onPreDelete(PreDeleteEvent preDeleteEvent) {

        Object entity = preDeleteEvent.getEntity();

        if(entity instanceof StudentEntity)
        {
            StudentEntity studentEntity = (StudentEntity) entity;
            System.out.println("Удаление студента:");
            studentEntity.print();
        }
        return false;
    }
}
