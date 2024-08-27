package com.brucemelo.web.model;

import com.brucemelo.domain.Course;
import com.brucemelo.domain.Student;

import java.util.List;

public record ResultCourse(List<Course> courses) {
}
