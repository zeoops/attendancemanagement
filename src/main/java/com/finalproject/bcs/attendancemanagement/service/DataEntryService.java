package com.finalproject.bcs.attendancemanagement.service;

import com.finalproject.bcs.attendancemanagement.datamodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataEntryService {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;


    public void saveSubject(Subject subject){
        subjectRepository.save(subject);
    }

    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }


    public void saveStudent (Student student) { studentRepository.save(student); }

    public List<Student> getStudent () { return studentRepository.findAll(); }

    public List<Teacher> getTeachers() { return  teacherRepository.findAll();}

    public void saveTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

//    public void updateTeacher(){}

}
