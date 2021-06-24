package com.finalproject.bcs.attendancemanagement.service;

import com.finalproject.bcs.attendancemanagement.datamodel.*;
import dto.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public void getSubjectReport(String subjectId,PrintWriter writer) throws IOException {
        Subject subject=subjectRepository.getOne(Long.valueOf(subjectId));
        List<String> months=new ArrayList<>();
        String column="Student";
        Date today=new Date();
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");

        List<SubjectDates> forTodayReport=new ArrayList<>();

        for(SubjectDates subjectDates:subject.getDates()){
            String s = formatter.format(subjectDates.getDate());
            if(subjectDates.getDate().before(today)){
                column+=","+s;
                forTodayReport.add(subjectDates);
            }
        }
        List<String> students=new ArrayList<>();
        for(Student student:subject.getStudents()){
            String studentColumn=student.getFirstName()+" "+student.getLastName();
            for(SubjectDates subjectDates:forTodayReport){
                if(existsAttendance(student.getAttendances(),subjectDates.getDate())){
                    studentColumn+=",Present";
                }else{
                    studentColumn+=",Absent";
                }
            }
            students.add(studentColumn);
        }
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        FileWriter writer = new FileWriter("C:/tmp/sto1.csv");
//        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
//                CsvPreference.STANDARD_PREFERENCE);
        writer.write(column);
        writer.write("\n");
        for(String student:students){
            writer.write(student);
            writer.write("\n");
        }
//        writer.close();



    }

    private Boolean existsAttendance(List<Attendance> attendances,Date date){
        boolean exists=false;
        for(Attendance attendance:attendances){
            if(attendance.getDate().getDate() == date.getDate()){
                exists=true;
            }
        }
        return exists;
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
