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
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.*;

@Controller
public class RegularAPI {
    List<Course> courseList = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();
    private final CourseService courseService;
    private final StudentService studentService;
    @Autowired
    private RegularAPI(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }


    @GetMapping(value = "/get-students")
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getList());
        return "display-students";
    }
    @GetMapping(value = "/get-courses")
    public String getCourses(Model model) {
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
    @GetMapping(value="/add-student-form")
    public String showStudentForm(Model model){
        model.addAttribute("student",new Student());
        model.addAttribute("courses", courseService.getList());
        return "Student-form";
    }
    @PostMapping(value = "/add-student-to-list")
    public String addStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "Student-form";
        }
        studentService.create(student);
        return "redirect:/get-students";
    }

    @GetMapping(value = "/delete-student/{ID}")
    public String deleteStudents(@PathVariable UUID ID) throws Exception {
        studentService.deleteStudent(ID.toString());
        return "redirect:/get-students";
    }

    @GetMapping(value = "/delete-course/{Name}")
    public String deleteCourse(@PathVariable String Name) throws Exception {
        courseService.deleteCourse(Name);
        return "redirect:/get-courses";
    }


    @GetMapping(value="/testing")
    public String test(Model model){

        return "dark";
    }
    @GetMapping(value = "/update-student/{ID}")
    public String UpdateStudents(@PathVariable UUID ID,Model model) {
        model.addAttribute("student", studentService.read(ID.toString()));
        model.addAttribute("courses", courseService.getList());
        return "Update-form";
    }
    @PostMapping(value = "/update-student")
    public String updateStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult) throws Exception {

            if (bindingResult.hasErrors() || student.getCredits()< courseService.summation(student.getRegisteredCourses())) {
               // return "redirect:/update-student/{ID}(ID=${student.getID()})";
                String ID = student.getID().toString();
                return "redirect:/update-student/" + ID;
            }
            studentService.update(student);
            return "redirect:/get-students";

    }

}



