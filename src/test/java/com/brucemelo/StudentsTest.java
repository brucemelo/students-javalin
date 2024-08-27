package com.brucemelo;

import com.brucemelo.web.model.NewStudent;
import com.brucemelo.web.model.ResultStudent;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StudentsTest {

    Javalin app = JavalinApp.create();
    JavalinJackson javalinJackson = new JavalinJackson();

    @Test
    @DisplayName("Should save and list students")
    void test1() {
        JavalinTest.test(app, (server, client) -> {
            var newStudent = new NewStudent("Bruce");
            var postResponse = client.post("/students", newStudent);
            assertEquals(postResponse.code(), HttpStatus.CREATED.getCode());
            var getResponse = client.get("/students");
            assertEquals(getResponse.code(),HttpStatus.OK.getCode());
            ResultStudent students = javalinJackson
                    .fromJsonString(getResponse.body().string(), ResultStudent.class);
            assertEquals(students.students(). stream().findFirst().get().getName(), newStudent.name());
        });
    }

}