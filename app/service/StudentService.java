package service;

import entity.Student;

import java.util.HashMap;
import java.util.Map;


public class StudentService {
    private Map<Integer, Student> students = new HashMap<>();

    public Student addStudent(Student student) {
        int id = students.size()+1;
        student.setId(id);
        students.put(id, student);
        return student;
    }

}
