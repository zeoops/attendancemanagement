package com.finalproject.bcs.attendancemanagement.service;

import com.finalproject.bcs.attendancemanagement.datamodel.*;
import dto.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DataEntryService {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SemesterRepository semesterRepository;

    public void saveSubject(SubjectDTO subject){
        Subject subject1=new Subject();
        subject1.setSubjectCode(subject.getSubjectCode());
        subject1.setSubjectName(subject.getSubjectName());
        Semester semester=semesterRepository.findById(Long.valueOf(subject.getSemesterId())).get();
        subject1.setSemester(semester);

        List<SubjectDates> subjectDates=new ArrayList<>();
        if(subject.getDates().size()>0){
            for(Date date:subject.getDates()){
                SubjectDates subjectDat=new SubjectDates();
//                subjectDat.setSubject(subject1);
                subjectDates.add(subjectDat);
            }
        }
        subject1.setDates(subjectDates);

        subjectRepository.save(subject1);
    }

    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    public List<Semester> getSemesters(){
        return semesterRepository.findAll();
    }


    public void saveStudent (Student student) { studentRepository.save(student); }

    public List<Student> getStudent () {
//        List<Subject> subjects=subjectRepository.findAll();
//        for(Subject subject:subjects){
//            if(subject.getStudents().size()>0){
//
//            }
//        }
        return studentRepository.findAll();
    }

    public List<Teacher> getTeachers() { return  teacherRepository.findAll();}

    public void saveTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

//    public void updateTeacher(){}

}
