package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.exception.EntityNotFoundException;
import com.bootcamp.StudentManagementSystem.model.dto.CourseDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Class;
import com.bootcamp.StudentManagementSystem.model.entity.Course;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
import com.bootcamp.StudentManagementSystem.model.mapper.CourseMapper;
import com.bootcamp.StudentManagementSystem.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final ClassService classService;
    private final PrelectorService prelectorService;

    public List<Course> getAllCourses() {
        List<Course> allCourses = courseRepository.findAll();
        return allCourses;
    }

    public Course getCourseById(Long id) {
        Optional<Course> byId = courseRepository.findById(id);
        return byId.orElseThrow(() ->{
            log.error("Course could not found by id: " +id);
            return new EntityNotFoundException("Course", "id :" + id);
        });
    }
    public Course getCourseByCode(String code) {
        Optional<Course> byId = courseRepository.findCourseByCode(code);
        return byId.orElseThrow(() -> new EntityNotFoundException("Course", "code :" + code));
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
        Course updatedCourse = getCourseByCode(code);
        if (!StringUtils.isEmpty(course.getCode())) {
            updatedCourse.setCode(course.getCode());
        }
        if (!StringUtils.isEmpty(course.getTitle())) {
            updatedCourse.setTitle(course.getTitle());
        }
        if (!StringUtils.isEmpty(course.getLetterGrade())) {
            updatedCourse.setLetterGrade(course.getLetterGrade());
        }
        if (course.getQuota() >= 0) {
            updatedCourse.setQuota(course.getQuota());
        }
        if (course.getGrade() >= 0) {
            updatedCourse.setGrade(course.getGrade());
        }

        return courseRepository.save(updatedCourse);
    }

    public void deleteAll() {
        courseRepository.deleteAll();
    }

    public List<Course> getAllByTitleContainingIgnoreCase(String title) {
        List<Course> allCourses = courseRepository.getAllByTitleContainingIgnoreCase(title);
        return allCourses;
    }

    public Course addClassToCourse(Long id, Class class1) {
        Course course = getCourseById(id);
        Class addClass = classService.getClassById(class1.getId());
        course.setCourseClass(addClass);
        return courseRepository.save(course);
    }

    public Course addPrelectorToCourse(Long id, Prelector prelector) {
        Course course = getCourseById(id);
        Prelector addPrelector = prelectorService.getPrelectorById(prelector.getId());
        course.setPrelector(addPrelector);
        return courseRepository.save(course);
    }

}
