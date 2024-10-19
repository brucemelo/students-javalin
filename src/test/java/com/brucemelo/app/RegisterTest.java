package com.brucemelo.app;

import com.brucemelo.app.web.model.NewCourse;
import com.brucemelo.app.web.model.NewRegister;
import com.brucemelo.app.web.model.NewStudent;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RegisterTest {

    Javalin app = JavalinApp.create();

    @Test
    @DisplayName("Should register student with a course")
    void test1() {
        JavalinTest.test(app, (server, client) -> {
            var newStudent = new NewStudent("Bruce");
            client.post("/students", newStudent);
            var newValue = new NewCourse("Course1");
            client.post("/courses", newValue);
            var newRegister = new NewRegister(1, 1);
            var postResponseRegister = client.post("/register", newRegister);
            assertEquals(postResponseRegister.code(), HttpStatus.CREATED.getCode());
        });
    }

}