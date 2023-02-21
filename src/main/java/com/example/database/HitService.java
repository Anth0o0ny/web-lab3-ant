package com.example.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.example.objective.Hit;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.io.Serializable;
import java.util.List;


public class HitService implements Serializable, HitDao {

    private final SessionFactory manager = ConnectionManager.getSessionFactory();


    @Override
    public void add(Hit hit) {
        Session session = manager.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(hit);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK || transaction.isActive())
                transaction.rollback();
        }
    }

    @Override
    public List<Hit> getAll() {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        List<Hit> listAnswer = currentSession.createQuery( "FROM Hit ").list();
        currentSession.getTransaction().commit();
        return listAnswer;
    }

    @Override
    public void clear() {
        Session currentSession = manager.getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createQuery("delete from Hit").executeUpdate();
        currentSession.getTransaction().commit();
    }
}
