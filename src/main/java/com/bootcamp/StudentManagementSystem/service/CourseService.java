package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.model.dto.ClassDTO;
import com.bootcamp.StudentManagementSystem.model.dto.CourseDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Class;
import com.bootcamp.StudentManagementSystem.model.entity.Course;
import com.bootcamp.StudentManagementSystem.model.mapper.CourseMapper;
import com.bootcamp.StudentManagementSystem.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        List<Course> allCourses = courseRepository.findAll();
        return allCourses;
    }

    public Course getCourseById(Long id) {
        Optional<Course> byId = courseRepository.findById(id);
        return byId.orElseThrow(() -> new RuntimeException("Course not found!"));
    }

    public Course create(CourseDTO courseDTO) {
        Course course = CourseMapper.toEntity(courseDTO);
        return courseRepository.save(course);
    }
    public void delete(Long id) {
        getCourseById(id);
        courseRepository.deleteById(id);
    }

    public Course update(String code, CourseDTO course) {
        Optional<Course> courseByCode = courseRepository.findCourseByCode(code);
        if (!courseByCode.isPresent())
            return null;
        Course updatedCourse = courseByCode.get();
        if (!StringUtils.isEmpty(course.getCode())) {
            updatedCourse.setCode(course.getCode());
        }
        if (!StringUtils.isEmpty(course.getTitle())) {
            updatedCourse.setTitle(course.getTitle());
        }
        return courseRepository.save(updatedCourse);
    }
}
