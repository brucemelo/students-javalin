package com.brucemelo;

import com.brucemelo.web.CourseHandler;
import com.brucemelo.web.RegisterHandler;
import com.brucemelo.web.StudentHandler;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class JavalinApp {

    public static Javalin create() {
        return Javalin.create((var config) -> config.router.apiBuilder(() -> {
            path("/", () -> get(ctx -> ctx.json("Ok")));
            path("/students", () -> {
                get(StudentHandler.listAll);
                post(StudentHandler.save);
            });
            path("/courses", () -> {
                get(CourseHandler.listAll);
                post(CourseHandler.save);
            });
            path("/register", () -> {
                post(RegisterHandler.register);
            });
        }));
    }

}