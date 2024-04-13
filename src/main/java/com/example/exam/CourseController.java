package com.example.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;


    // Endpoint to add a new training course
    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    // Endpoint to delete a training course
    @DeleteMapping("delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }

    // Endpoint to update the details of a training course
    @PostMapping("update/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(updatedCourse.getName());
                    course.setDescription(updatedCourse.getDescription());
                    course.setOu(updatedCourse.getOu());
                    course.setStatus(updatedCourse.getStatus());
                    return courseRepository.save(course);
                })
                .orElseGet(() -> {
                    updatedCourse.setId(id);
                    return courseRepository.save(updatedCourse);
                });
    }


    // Endpoint to get all training courses
    @GetMapping("/list")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Endpoint to get a training course by ID
    @GetMapping("list/{id}")
    public Course getCourseById(@PathVariable Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return courseOptional.orElse(null); // Return null if course is not found
    }

    // Endpoint to filter training courses by OU
    @GetMapping("list/ou/{ou}")
    public List<Course> getCoursesByOU(@PathVariable String ou) {
        return courseRepository.findByOu(ou);
    }

    // Endpoint to filter training courses by status
    @GetMapping("list/status/{status}")
    public List<Course> getCoursesByStatus(@PathVariable String status) {
        return courseRepository.findByStatus(status);
    }

}
