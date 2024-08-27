package com.brucemelo.web;

import com.brucemelo.domain.Student;
import com.brucemelo.domain.Student_;
import com.brucemelo.infrastructure.AppHibernate;
import com.brucemelo.web.model.NewStudent;
import com.brucemelo.web.model.ResultStudent;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.hibernate.query.criteria.CriteriaDefinition;


public class StudentHandler {

    public static Handler listAll = (context) -> {
        var result = AppHibernate.fromTransaction(session -> {
            var query = new CriteriaDefinition<>(session, Student.class) {
                {
                    var student = from(Student.class);
                    orderBy(asc(student.get(Student_.NAME)));
                }
            };
            return session.createQuery(query).getResultList();
        });
        context.json(new ResultStudent(result));
    };

    public static Handler save = (context) -> {
        var newStudent = context.bodyAsClass(NewStudent.class);
        var result = AppHibernate.fromTransaction(session -> {
            var insertedId = session.insert(Student.newStudent(newStudent.name()));
            return session.get(Student.class, insertedId);
        });
        context.json(result).status(HttpStatus.CREATED);
    };

}
