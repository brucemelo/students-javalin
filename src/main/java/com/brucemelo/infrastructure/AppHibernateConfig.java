package com.brucemelo.infrastructure;

import com.brucemelo.domain.Course;
import com.brucemelo.domain.Student;
import com.brucemelo.domain.StudentCourse;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.schema.Action;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

class AppHibernateConfig {

    private static final Logger logger = Logger.getLogger(AppHibernateConfig.class.getName());

    private static SessionFactory sessionFactory;

    static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Logger.getLogger("org.hibernate").setLevel(Level.OFF);
                var configuration = getConfiguration();
                var serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Throwable ex) {
                logger.log(Level.SEVERE, "Failed to create session factory", ex);
            }
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        var configuration = new Configuration();
        var settings = new Properties();
        settings.put(AvailableSettings.JAKARTA_JDBC_DRIVER, "org.postgresql.Driver");
        settings.put(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5432/mydatabase");
        settings.put(AvailableSettings.JAKARTA_JDBC_USER, "sa");
        settings.put(AvailableSettings.JAKARTA_JDBC_PASSWORD, "sa");
        settings.put(AvailableSettings.SHOW_SQL, true);
        settings.put(AvailableSettings.FORMAT_SQL, true);
        settings.put(AvailableSettings.HIGHLIGHT_SQL, true);
        settings.put(AvailableSettings.HBM2DDL_AUTO, Action.ACTION_CREATE);

        configuration.setProperties(settings);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(StudentCourse.class);
        return configuration;
    }

}
