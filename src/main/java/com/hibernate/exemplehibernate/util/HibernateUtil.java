package com.hibernate.exemplehibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import com.hibernate.exemplehibernate.Dto.Student;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings équivalents aux propriétés de hibernate.cfg.xml
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver"); // Utilisation du pilote PostgreSQL
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/hibernate"); // URL PostgreSQL
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "youcode2023");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect"); // Dialecte PostgreSQL

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Student.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
