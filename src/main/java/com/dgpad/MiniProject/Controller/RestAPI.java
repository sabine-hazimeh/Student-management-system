package com.dgpad.MiniProject.Controller;

import com.dgpad.MiniProject.Model.Student;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestAPI {
    List<Student> students = new ArrayList<>();
    @GetMapping(value = "/get-students-list")
    public List<Student> getStudentList(){
        return students;
    }
}
