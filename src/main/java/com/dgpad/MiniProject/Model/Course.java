package com.dgpad.MiniProject.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Course {
    @NotEmpty(message = "please fill this field")
    private String courseName;
    @NotEmpty(message = "please fill this field")
    @Size(min=6, max = 6,message = ("the symbol must consist from 6 chars"))
    private String courseSymbol;
    @Max(15)
    private int courseCredits;
    @NotEmpty(message = "please fill this field")
    private String courseInstructor;
    @Email
    private String instructorEmail;

    public Course() {
    }

    public Course(String courseName, String courseSymbol, int courseCredits, String courseInstructor, String instructorEmail) {
        this.courseName = courseName;
        this.courseSymbol = courseSymbol;
        this.courseCredits = courseCredits;
        this.courseInstructor = courseInstructor;
        this.instructorEmail = instructorEmail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseSymbol() {
        return courseSymbol;
    }

    public void setCourseSymbol(String courseSymbol) {
        this.courseSymbol = courseSymbol;
    }

    public int getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }
}
