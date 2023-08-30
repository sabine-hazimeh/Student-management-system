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


import java.util.*;

@Controller
public class RegularAPI {
    List<Course> courseList = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();
    private final StudentService studentService;
    private final CourseService courseService;
    @Autowired
    private RegularAPI(StudentService studentService, CourseService courseService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }


    @GetMapping(value = "/get-students")
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getList());
        return "display-students";
    }

    @GetMapping(value="/add-student-form")
    public String showStudentForm(Model model) throws Exception {
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


    @GetMapping(value = "/update-student/{ID}")
    public String UpdateStudents(@PathVariable UUID ID,Model model) throws Exception {
        model.addAttribute("student", studentService.read(ID.toString()));
        model.addAttribute("courses", courseService.getList());
        return "Update-form";
    }
    @PostMapping(value = "/update-student")
    public String updateStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult) throws Exception {

//            if (bindingResult.hasErrors() || student.getCredits()< courseService.summation(student.getRegisteredCourses())) {
//               // return "redirect:/update-student/{ID}(ID=${student.getID()})";
//                String ID = student.getID().toString();
//                return "redirect:/update-student/" + ID;
//            }
//            studentService.update(student);
//            return "redirect:/get-students";
        if (bindingResult.hasErrors()) {

            return "Update-form";
        }
        studentService.update(student);
        return "redirect:/get-students";

    }

}



