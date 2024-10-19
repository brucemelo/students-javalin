package com.brucemelo.app.infrastructure;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

class AppHibernateSessionFactory {

    private static final Logger logger = Logger.getLogger(AppHibernateSessionFactory.class.getName());

    private static SessionFactory sessionFactory;

    static SessionFactory getSessionFactory() {
        if (Objects.isNull(sessionFactory)) {
            try {
                Logger.getLogger("org.hibernate").setLevel(Level.OFF);
                var configuration = AppHibernateConfig.configuration();
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

}
