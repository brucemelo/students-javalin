package com.brucemelo.app.web;

import com.brucemelo.app.domain.Course;
import com.brucemelo.app.domain.Student;
import com.brucemelo.app.domain.StudentCourse;
import com.brucemelo.app.infrastructure.AppHibernate;
import com.brucemelo.app.web.model.NewRegister;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;


public class RegisterHandler {

    public static Handler register = (context) -> {
        var newRegister = context.bodyAsClass(NewRegister.class);
        var result = AppHibernate.fromTransaction(session -> {
            var course = session.get(Course.class, newRegister.idCourse());
            var student = session.get(Student.class, newRegister.idStudent());
            var entity = new StudentCourse();
            entity.setCourse(course);
            entity.setStudent(student);
            var insertId = session.insert(entity);
            return session.get(StudentCourse.class, insertId);
        });
        context.json(result).status(HttpStatus.CREATED);
    };

}
