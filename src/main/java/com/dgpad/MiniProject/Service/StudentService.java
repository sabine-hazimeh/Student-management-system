package com.dgpad.MiniProject.Service;

import com.dgpad.MiniProject.Model.Student;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
        List<Student> students = new ArrayList<>();

        private final ReadWriteService readWriteService;

        @Autowired
        private StudentService(ReadWriteService readWriteService) throws Exception {
            this.readWriteService = readWriteService;
            students = deserialize((JSONArray) readWriteService.Read("data","students"));
        }

        public boolean create(Student obj) throws Exception {
            students.add(obj);
            writeUpdates();
            return true;
        }

        public void deleteStudent(String ID) throws Exception {
            Student studentObj = read(ID);
            students.remove(studentObj);
            writeUpdates();
        }

        public Student read(String ID) {
            for(Student student : students)
                if (student.getID().toString().equals(ID))
                    return student;
            return null;
        }

        public Student update(Student obj) throws Exception {
            Student studentObj = read(obj.getID().toString());
            studentObj.setFirstName(obj.getFirstName());
            studentObj.setLastName(obj.getLastName());
            studentObj.setCredits(obj.getCredits());
            studentObj.setRegisteredCourses(obj.getRegisteredCourses());
            writeUpdates();
            return obj;
        }

        public List<Student> getList() {
            return students;
        }

        public Student deserialize(JSONObject object) {
            String ID = object.getString("ID");
            String firstName = object.getString("firstName");
            String lastName = object.getString("lastName");
            Integer allowedCredits = object.getInt("credits");
            List<String> courses = convertJSONArrayToList(object.getJSONArray("registeredCourses"));
            return new Student(UUID.fromString(ID), firstName,lastName,allowedCredits,courses);
        }

        public List<Student> deserialize(JSONArray objects) {
            List<Student> studentList = new ArrayList<>();
            for(int i = 0 ; i< objects.length() ; i++)
                studentList.add(deserialize(objects.getJSONObject(i)));
            return studentList;
        }

        public JSONObject serialize(Student obj) {
            JSONObject object = new JSONObject();
            object.put("ID", obj.getID().toString());
            object.put("firstName", obj.getFirstName());
            object.put("lastName", obj.getLastName());
            object.put("credits", obj.getCredits());
            object.put("registeredCourses",new JSONArray(obj.getRegisteredCourses()));
            return object;
        }

        public JSONArray serialize(List<Student> objects) {
            JSONArray studentArray = new JSONArray();
            for(Student student : objects)
                studentArray.put(serialize(student));
            return studentArray;
        }

        private List<String> convertJSONArrayToList(JSONArray array){
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                stringList.add(array.getString(i));
            }
            return stringList;
        }

        public void writeUpdates() throws Exception {
            JSONArray array = serialize(students);
            readWriteService.Write("data","students",array);
        }



}
