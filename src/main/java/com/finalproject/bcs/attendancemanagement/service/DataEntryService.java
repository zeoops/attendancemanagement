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
    @Autowired
    SubjectDatesRepository subjectDatesRepository;

    public void saveSubject(SubjectDTO subject){
        Subject subject1=new Subject();
        subject1.setSubjectCode(subject.getSubjectCode());
        subject1.setSubjectName(subject.getSubjectName());
        Semester semester=semesterRepository.findById(Long.valueOf(subject.getSemesterId())).get();
        subject1.setSemester(semester);
        subject1=subjectRepository.save(subject1);
//        List<SubjectDates> subjectDates=new ArrayList<>();
        if(subject.getDates().size()>0){
            for(Date date:subject.getDates()){
                SubjectDates subjectDat=new SubjectDates();
                subjectDat.setSubject(subject1);
                subjectDat.setDate(date);
                subjectDatesRepository.save(subjectDat);
            }
        }
//        subject1.setDates(subjectDates);


    }

    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    public List<Subject> getTodaySubjects(){
        List<Subject> subjects=subjectRepository.findAll();
        List<Subject> todaySubjects=new ArrayList<>();
        for(Subject subject:subjects){
            Date date=new Date();
            for(SubjectDates subjectDate:subject.getDates()){
                if(subjectDate.getDate().getMonth() == date.getMonth() && subjectDate.getDate().getDate() == date.getDate()){
                    todaySubjects.add(subject);
                }
            }
        }
        return todaySubjects;
    }

    public void getSubjectReport(String subjectId,String month){
        Subject subject=subjectRepository.getOne(Long.valueOf(subjectId));
        List<String> months=new ArrayList<>();
        String str="";
        for(SubjectDates subjectDates:subject.getDates()){
            for(Student student:subject.getStudents()){
                for(Attendance attendance:student.getAttendances()){
                    if(subjectDates.getDate().getMonth() == attendance.getDate().getMonth()
                            && subjectDates.getDate().getDate() ==attendance.getDate().getDate()){
                        str+="Present ";
                    }
                }
            }
        }
//            for(Student student:subject.getStudents()){
//                for(Attendance attendance:student)
//            }
//        subject.
//        return subjectRepository.findAll();
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
