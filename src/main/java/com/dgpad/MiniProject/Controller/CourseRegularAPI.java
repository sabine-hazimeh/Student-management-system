package com.dgpad.MiniProject.Controller;


import com.dgpad.MiniProject.Model.Course;
import com.dgpad.MiniProject.Model.Student;
import com.dgpad.MiniProject.Service.CourseService;
import com.dgpad.MiniProject.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CourseRegularAPI {
    List<Course> courseList = new ArrayList<>();

    private final CourseService courseService;

    @Autowired
    private CourseRegularAPI(CourseService courseService) {
        this.courseService = courseService;

    }

    @GetMapping(value = "/get-courses")
    public String getCourses(Model model) throws Exception {
        model.addAttribute("courses", courseService.getList());
        return "display-courses";
    }
    @GetMapping(value="/add-course-form")
    public String showCourseForm(Model model){
        model.addAttribute("course",new Course());
        return "Course-form";
    }
    @PostMapping(value = "/add-course-to-list")
    public String addCourse(@Valid @ModelAttribute Course course, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "Course-form";
        }
       courseService.create(course);
        return "redirect:/get-courses";
    }

    @GetMapping(value = "/delete-course/{ID}")
    public String deleteCourse(@PathVariable UUID ID) throws Exception {
        courseService.deleteCourse(ID.toString());
        return "redirect:/get-courses";
    }

}



