package com.example.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.example.objective.Hit;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.io.Serializable;
import java.util.List;


/**
 * Класс HitService предоставляет реализацию интерфейса HitDao для доступа к данным о точках в базе данных с использованием Hibernate.
 */
public class HitService implements Serializable, HitDao {

    /**
     * Объект SessionFactory, используемый для создания и управления сессиями Hibernate.
     * SessionFactory создается при инициализации класса HitService и предоставляет доступ к сессиям для взаимодействия с базой данных.
     */
    private final SessionFactory manager = ConnectionManager.getSessionFactory();


    /**
     * Добавляет точку в базу данных.
     *
     * @param hit Объект Hit, представляющий точку, которую необходимо добавить в базу данных.
     */
    @Override
    public void add(Hit hit) {
        Session session = manager.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(hit);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK || transaction.isActive())
                transaction.rollback();
        }
    }


    /**
     * Получает список всех точек из базы данных.
     *
     * @return Список объектов Hit, представляющих все сохраненные точки.
     */
    @Override
    public List<Hit> getAll() {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        List<Hit> listAnswer = currentSession.createQuery("FROM Hit", Hit.class).list();
        currentSession.getTransaction().commit();
        return listAnswer;
    }


    /**
     * Очищает базу данных, удаляя все данные о точках.
     */
    @Override
    public void clear() {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createQuery("DELETE FROM Hit", Hit.class).executeUpdate();
        currentSession.getTransaction().commit();
    }
}
