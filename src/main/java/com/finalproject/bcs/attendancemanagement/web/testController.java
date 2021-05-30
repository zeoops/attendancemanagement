package com.finalproject.bcs.attendancemanagement.web;

import com.finalproject.bcs.attendancemanagement.datamodel.Student;
import com.finalproject.bcs.attendancemanagement.datamodel.StudentRepository;
import com.finalproject.bcs.attendancemanagement.datamodel.Subject;
import com.finalproject.bcs.attendancemanagement.datamodel.Teacher;
import com.finalproject.bcs.attendancemanagement.service.AttendanceService;
import com.finalproject.bcs.attendancemanagement.service.DataEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class testController {
    @Autowired StudentRepository studentRepository;
    @Autowired
    DataEntryService dataEntryService;

    @Autowired
    AttendanceService attendanceService;
// @ResponseBody
    @GetMapping("/index")
    public String getIndex(){

        return "index";
    }

    @GetMapping("/get/subjectpage")
    public String getSubjectPage(Model model){
        Subject subject=new Subject();
        model.addAttribute("subject",subject);
        return "add-subject";
    }

    @GetMapping("/get/subjects")
    public String getSubjectsPage(Model model){
        model.addAttribute("subjects",dataEntryService.getSubjects());
        return "subjects";
    }

    @PostMapping("/save/subject")
    public String saveSubject(Model model, @ModelAttribute("subject") Subject subject){
        dataEntryService.saveSubject(subject);
        model.addAttribute("subjects",dataEntryService.getSubjects());
        return "subjects";
    }
    @GetMapping("/get/studentspage")
    public String getStudentPage (Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "add-student";
    }
    @GetMapping("/get/students")
    public String getStudentPag(Model model) {
        model.addAttribute("students", dataEntryService.getStudent());
        return "students";

    }
    @PostMapping("/save/student")
    public String saveStudent(Model model,@ModelAttribute("student") Student student ){
        dataEntryService.saveStudent(student);
        model.addAttribute("students",dataEntryService.getStudent());
        return "students";
    }

    @GetMapping("/get/teacherpage")
    public String getTeacherPage(Model model){
        model.addAttribute("teachers",dataEntryService.getTeachers());
        return "teachers";
    }
    @GetMapping("/get/addteacherpage")
    public  String getAddteacherpage (Model model){
        Teacher teacher = new Teacher();
        model.addAttribute("teacher",teacher);
        return "add-teacher";
    }

    @PostMapping("/save/teacher")
    public String saveTeacher(Model model, @ModelAttribute("teacher") Teacher teacher){
        dataEntryService.saveTeacher(teacher);
        model.addAttribute("teachers",dataEntryService.getTeachers());
        return "teachers";
    }
    @GetMapping("/get/attendancepage")
    public  String getAttandancePage (){

        return "attendance-page";
    }

    @PostMapping("/save/pic")
    @ResponseBody
    public String savePicture(@RequestBody String imageString){


        return attendanceService.saveImage(imageString);
    }

    @GetMapping("/get/upload")
    public  String getUploadPage (){

        return "upload-students";
    }

    @PostMapping("/upload/students")
    @ResponseBody
    public String uploadSaveStudents(@RequestParam("file") MultipartFile file){

        attendanceService.uploadStudentData(file);

        return "Success";
    }

}
