package com.dgpad.MiniProject.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public class Student {
    private UUID ID;
    @NotEmpty(message = "please fill this field")
   private String firstName;
    @NotEmpty(message = "please fill this field")
   private String lastName;
    @Max(value= 15, message = "The maximum allowed credits is 15")
    @Min(value= 0, message = "The minimum allowed credits is 0")
   private int credits;

   private List<String> RegisteredCourses;

    public Student() {
        this.ID= UUID.randomUUID();
    }

    public Student(UUID ID, String firstName, String lastName, int credits, List<String> registeredCourses) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.credits = credits;
        RegisteredCourses = registeredCourses;
    }

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<String> getRegisteredCourses() {
        return RegisteredCourses;
    }

    public void setRegisteredCourses(List<String> registeredCourses) {

        RegisteredCourses = registeredCourses;
    }
}
