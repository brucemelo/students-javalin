package com.brucemelo.domain;

import org.hibernate.StatelessSession;
import org.hibernate.annotations.processing.Find;

import java.util.List;

public interface CourseQueries {

    @Find
    List<Course> getAllCourses(StatelessSession session);

}
