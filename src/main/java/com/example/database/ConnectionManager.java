package com.example.database;

import com.example.objective.Hit;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


/**
 * Класс ConnectionManager предоставляет доступ к сессии работы с базой данных с использованием Hibernate.
 * Он обеспечивает единственную точку доступа к сессии, чтобы гарантировать ее создание только один раз.
 */
public class ConnectionManager {
    private static SessionFactory sessionFactory;

    private ConnectionManager() {
    }

    /**
     * Получение фабрики сессий Hibernate для работы с базой данных.
     *
     * @return Фабрика сессий Hibernate.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(Hit.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Ошибка получения сессии работы с БД");
            }
        }
        return sessionFactory;

    }
}
