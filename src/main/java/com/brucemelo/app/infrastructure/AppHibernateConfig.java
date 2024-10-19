package com.brucemelo.app.infrastructure;

import com.brucemelo.app.domain.Course;
import com.brucemelo.app.domain.Student;
import com.brucemelo.app.domain.StudentCourse;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.schema.Action;

import java.util.Properties;

class AppHibernateConfig {

    static Configuration configuration() {
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
