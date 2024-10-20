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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CoursesTest {

    Javalin app = JavalinApp.create();
    JavalinJackson javalinJackson = new JavalinJackson();

    @Test
    @DisplayName("Should save and list courses")
    void test1() {
        JavalinTest.test(app, (server, client) -> {
            var newCourse = new NewCourse("Course1");
            var postResponse = client.post("/courses", newCourse);
            assertEquals(postResponse.code(), HttpStatus.CREATED.getCode());
            var response = client.get("/courses");
            assertEquals(response.code(), HttpStatus.OK.getCode());
            assertNotNull(response.body());
            ResultCourse result = javalinJackson.fromJsonString(response.body().string(), ResultCourse.class);
            assertNotNull(result.courses());
            var firstCourse = result.courses().stream().findFirst();
            assertTrue(firstCourse.isPresent());
            assertEquals(firstCourse.get().getName(), newCourse.name());
        });
    }

}