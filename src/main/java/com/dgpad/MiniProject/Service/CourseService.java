package com.dgpad.MiniProject.Service;

import com.dgpad.MiniProject.Model.Course;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {
    private List<Course> courses = new ArrayList<>();

    private final ReadWriteService readWriteService;

    @Autowired
    private CourseService(ReadWriteService readWriteService) throws Exception {
        this.readWriteService = readWriteService;
        courses = getList();
    }

//
public boolean create(Course course) throws Exception {
    if(contain(course))
        return false;
    courses.add(course);
    writeUpdates();
    return true;
}

    private boolean contain(Course course) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course c = iterator.next();
            if (c.getCourseSymbol().equals(course.getCourseSymbol())) {
                return true;
            }
        }
        return false;
    }

    public void deleteCourse(String ID) throws Exception {
        Course CourseObj = read(ID);
        courses.remove(CourseObj);
        writeUpdates();
    }

    public  Course read(String ID) {
        for(Course course : courses)
            if (course.getID().toString().equals(ID))
                return course;
        return null;
    }



    public List<Course> getList() throws Exception {
        JSONArray array=(JSONArray)readWriteService.Read("data", "courses");
        List <Course> courses=new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            courses.add(deserialize(array.getJSONObject(i)));
        }
        return courses;
    }

    public Course deserialize(JSONObject object) {
        String ID = object.getString("ID");
        String courseName = object.getString("courseName");
        String courseSymbol = object.getString("courseSymbol");
        Integer courseCredits = object.getInt("courseCredits");
        String courseInstructor = object.getString("courseInstructor");
        String instructorEmail = object.getString("instructorEmail");
        return new Course(UUID.fromString(ID),courseName,courseSymbol,courseCredits,courseInstructor,instructorEmail);
    }


    public JSONObject serialize(Course obj) {
        JSONObject object = new JSONObject();
        object.put("ID", obj.getID().toString());
        object.put("courseName", obj.getCourseName());
        object.put("courseSymbol", obj.getCourseSymbol());
        object.put("courseCredits", obj.getCourseCredits());
        object.put("courseInstructor", obj.getCourseInstructor());
        object.put("instructorEmail",obj.getInstructorEmail());
        return object;
    }

    public JSONArray serialize(List<Course> objects) {
        JSONArray CourseArray = new JSONArray();
        for(Course course : objects)
            CourseArray.put(serialize(course));
        return CourseArray;
    }

    private List<String> convertJSONArrayToList(JSONArray array){
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            stringList.add(array.getString(i));
        }
        return stringList;
    }

    public void writeUpdates() throws Exception {
        JSONArray array = serialize(courses);
        readWriteService.Write("data","courses",array);
    }

//    public Integer summation(List<String> courses){
//        int sum=0;
//        List<Course> originalList = getList();
//        for(int i=0; i<courses.size();i++){
//            sum += originalList.get(contain(courses.get(i))).getCourseCredits();
//
//        }
//        return sum;
//    }
//    private Integer contain(String course) {
//        for (int i = 0; i < courses.size(); i++) {
//            if(courses.get(i).getCourseName().equals(course)){
//                return i;
//            }
//        }
//        return -1;
//    }

}
