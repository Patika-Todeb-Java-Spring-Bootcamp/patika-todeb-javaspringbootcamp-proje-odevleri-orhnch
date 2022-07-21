package com.bootcamp.StudentManagementSystem.model.mapper;

import com.bootcamp.StudentManagementSystem.model.dto.CourseDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Course;

public class CourseMapper {
    public static CourseDTO toDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setTitle(course.getTitle());
        courseDTO.setCourseClass(course.getCourseClass());
        courseDTO.setCode(course.getCode());
        courseDTO.setQuota(course.getQuota());
        courseDTO.setPrelector(course.getPrelector());
        courseDTO.setLetterGrade(course.getLetterGrade());
        courseDTO.setGrade(course.getGrade());
        return courseDTO;
    }

    public static Course toEntity(CourseDTO courseDTO){
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setCourseClass(courseDTO.getCourseClass());
        course.setCode(courseDTO.getCode());
        course.setQuota(courseDTO.getQuota());
        course.setPrelector(courseDTO.getPrelector());
        course.setLetterGrade(courseDTO.getLetterGrade());
        course.setGrade(courseDTO.getGrade());
        return course;
    }
}
