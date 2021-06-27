package com.finalproject.bcs.attendancemanagement.web;

import com.finalproject.bcs.attendancemanagement.datamodel.*;
import com.finalproject.bcs.attendancemanagement.service.AttendanceService;
import com.finalproject.bcs.attendancemanagement.service.DataEntryService;
import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class AttendanceManagementController {
    @Autowired StudentRepository studentRepository;
    @Autowired
    DataEntryService dataEntryService;

    @Autowired
    AttendanceService attendanceService;
// @ResponseBody
    @GetMapping("/index")
    public String getIndex(){

        return "theme/login";
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        model.addAttribute("subjects",dataEntryService.getSubjects());
        return "theme/subjects";
    }
    @GetMapping("/studentview")
    public String getStudentView(){

        return "theme/indexStudent";
    }


    @GetMapping("/subjects")
    public String getSubjectsPage(Model model){
        model.addAttribute("subjects",dataEntryService.getSubjects());
        return "theme/subjects";
    }

    @GetMapping("/get/subjects")
    @ResponseBody
    public List<Subject> getSubjectsPage(){

        return dataEntryService.getSubjects();
    }

    @GetMapping("/subject/{id}")
//    @ResponseBody
    public void getSubjectReport(Model model, @PathVariable("id") String subjectId, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=report.csv");
        dataEntryService.getSubjectReport(subjectId,response.getWriter()) ;
//        return dataEntryService.getSubjectReport(subjectId);
//        return "theme/subjects";
    }

    @GetMapping("/get/semester")
    @ResponseBody
    public List<Semester> getSemeseters(){
        return dataEntryService.getSemesters();
    }

//    @PostMapping("/save/subject")
//    public String saveSubject(Model model, @ModelAttribute("subject") Subject subject){
//        dataEntryService.saveSubject(subject);
//        model.addAttribute("subjects",dataEntryService.getSubjects());
//        return "subjects";
//    }

    @PostMapping("/subject")
    @ResponseBody
    public List<Subject> addSubject(@RequestBody SubjectDTO subject){
//        dataEntryService.saveSubject(subject);
//        model.addAttribute("subjects",dataEntryService.getSubjects());

        dataEntryService.saveSubject(subject);
        return dataEntryService.getSubjects();
    }




    @GetMapping("/students")
    public String getStudentPag(Model model) {
        model.addAttribute("students", dataEntryService.getStudent());
        return "theme/students";

    }

//    @GetMapping("/get/teacherpage")
//    public String getTeacherPage(Model model){
//        model.addAttribute("teachers",dataEntryService.getTeachers());
//        return "teachers";
//    }
//    @GetMapping("/get/addteacherpage")
//    public  String getAddteacherpage (Model model){
//        Teacher teacher = new Teacher();
//        model.addAttribute("teacher",teacher);
//        return "add-teacher";
//    }

//    @PostMapping("/save/teacher")
//    public String saveTeacher(Model model, @ModelAttribute("teacher") Teacher teacher){
//        dataEntryService.saveTeacher(teacher);
//        model.addAttribute("teachers",dataEntryService.getTeachers());
//        return "teachers";
//    }
    @GetMapping("/subject/{subjectId}/attendancepage")
    public  String getAttandancePage (Model model,@PathVariable("subjectId") String subjectId){
        model.addAttribute("subjectId",subjectId);
        return "theme/takeAttendance";
    }

    @PostMapping("/save/pic")
    @ResponseBody
    public AttendanceResponse savePicture(@RequestBody AttendanceAttempt attendanceAttempt){


        return attendanceService.saveImage(attendanceAttempt);
    }
    @GetMapping("/subjects/today")
    @ResponseBody
    public  List<Subject> getTodaySubjects (){

        return dataEntryService.getTodaySubjects();
    }

    @GetMapping("/upload")
    public  String getUploadPage (){

        return "theme/upload";
    }


    @PostMapping("/upload/students")
//    @ResponseBody
    public String uploadSaveStudents(@RequestParam("file") MultipartFile file,@RequestParam("subject") Long subjectId,Model model){

        attendanceService.uploadStudentData(file,subjectId);
//        model.addAttribute("subjects",dataEntryService.getSubjects());
//        return "theme/subjects";
//        return "Success";
        return "redirect:/subjects";
    }

    @PostMapping("/teacher/login")
    @ResponseBody
    public LoginResponse teacherLogin(@RequestBody LoginDTO loginDTO){
        //To be completed here real Auth.
        LoginResponse loginResponse=new LoginResponse();
        if(loginDTO.getPassword().isEmpty() || loginDTO.getUsername().isEmpty()){
            loginResponse.setMessage("ERROR");
            loginResponse.setStatus("KO");
        }else{
            loginResponse.setMessage("SUCCESS");
            loginResponse.setStatus("OK");
        }
        return loginResponse;
    }

}
