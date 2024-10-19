package com.brucemelo.app;

import com.brucemelo.app.web.model.NewCourse;
import com.brucemelo.app.web.model.ResultCourse;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CoursesTest {

    Javalin app = JavalinApp.create();
    JavalinJackson javalinJackson = new JavalinJackson();

    @Test
    @DisplayName("Should save and list courses")
    void test1() {
        JavalinTest.test(app, (server, client) -> {
            var newValue = new NewCourse("Course1");
            var postResponse = client.post("/courses", newValue);
            assertEquals(postResponse.code(), HttpStatus.CREATED.getCode());
            var getResponse = client.get("/courses");
            assertEquals(getResponse.code(),HttpStatus.OK.getCode());
            ResultCourse result = javalinJackson.fromJsonString(getResponse.body().string(), ResultCourse.class);
            assertEquals(result.courses(). stream().findFirst().get().getName(), newValue.name());
        });
    }

}