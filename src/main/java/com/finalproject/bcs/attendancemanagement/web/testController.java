package com.finalproject.bcs.attendancemanagement.web;

import com.finalproject.bcs.attendancemanagement.datamodel.*;
import com.finalproject.bcs.attendancemanagement.service.DataEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.text.Normalizer;
import java.util.List;
@RequestMapping("/")
@Controller
public class testController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    DataEntryService dataEntryService;

    @ResponseBody
    @GetMapping("/index,")
    public List<Student> getIndex() {
        return studentRepository.findAll();
    }

    @GetMapping("admin")
    public String getAdminPage() {
        return "Admin";

    }
    @GetMapping("teacher")
    public String getTeacherPage(Model model){
        Teacher teacher =new Teacher();
        model.addAttribute("Teacher",teacher);
        return "add-teacher";
    }
    @GetMapping("attendance/subject")
    public String getSubjectPage(Model model){
        Subject subject =new Subject();
        model.addAttribute("Subject",subject);
        return "addsubjects";
    }
    @PostMapping("add/subject")
    public void saveSubject(@ModelAttribute("subject") Subject subject ){
        dataEntryService.saveSubject(subject);
    }




    }



