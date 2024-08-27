package com.brucemelo.web;

import com.brucemelo.domain.Course;
import com.brucemelo.domain.Student;
import com.brucemelo.domain.StudentCourse;
import com.brucemelo.infrastructure.AppHibernate;
import com.brucemelo.web.model.NewRegister;
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
