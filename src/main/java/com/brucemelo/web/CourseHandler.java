package com.brucemelo.web;

import com.brucemelo.domain.Course;
import com.brucemelo.domain.CourseQueries_;
import com.brucemelo.infrastructure.AppHibernate;
import com.brucemelo.web.model.NewCourse;
import com.brucemelo.web.model.ResultCourse;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;


public class CourseHandler {

    public static Handler listAll = (context) -> {
        var result = AppHibernate.fromTransaction(CourseQueries_::getAllCourses);
        context.json(new ResultCourse(result));
    };

    public static Handler save = (context) -> {
        var newCourse = context.bodyAsClass(NewCourse.class);
        var result = AppHibernate.fromTransaction(session -> {
            var insertedId = session.insert(Course.newCourse(newCourse.name()));
            return session.get(Course.class, insertedId);
        });
        context.json(result).status(HttpStatus.CREATED);
    };

}
