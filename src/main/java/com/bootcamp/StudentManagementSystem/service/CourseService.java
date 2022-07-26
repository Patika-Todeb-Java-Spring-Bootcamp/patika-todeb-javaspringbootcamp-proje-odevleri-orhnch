package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.exception.EntityNotFoundException;
import com.bootcamp.StudentManagementSystem.model.dto.CourseDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Class;
import com.bootcamp.StudentManagementSystem.model.entity.Course;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
import com.bootcamp.StudentManagementSystem.model.mapper.CourseMapper;
import com.bootcamp.StudentManagementSystem.repository.ClassRepository;
import com.bootcamp.StudentManagementSystem.repository.CourseRepository;
import com.bootcamp.StudentManagementSystem.repository.PrelectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final ClassRepository classRepository;
    private final PrelectorRepository prelectorRepository;

    public List<Course> getAllCourses() {
        List<Course> allCourses = courseRepository.findAll();
        return allCourses;
    }

    public Course getCourseById(Long id) {
        Optional<Course> byId = courseRepository.findById(id);
        return byId.orElseThrow(() -> new EntityNotFoundException("Course", "id :" + id));
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
            throw new EntityNotFoundException("Course", "code :" + code);
        Course updatedCourse = courseByCode.get();
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
        Optional<Class> classById = classRepository.findById(class1.getId());
        if (!classById.isPresent()) {
            throw new EntityNotFoundException("Class", "id :" + class1.getId());
        }
        Class addClass = classById.get();
        course.setCourseClass(addClass);
        return courseRepository.save(course);
    }

    public Course addPrelectorToCourse(Long id, Prelector prelector) {
        Course course = getCourseById(id);
        Optional<Prelector> prelectorById = prelectorRepository.findById(prelector.getId());
        if (!prelectorById.isPresent()) {
            throw new EntityNotFoundException("Prelector", "id :" + prelector.getId());
        }
        Prelector addPrelector = prelectorById.get();
        course.setPrelector(addPrelector);
        return courseRepository.save(course);
    }

}
