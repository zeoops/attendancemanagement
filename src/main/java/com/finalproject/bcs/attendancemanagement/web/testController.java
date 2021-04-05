package com.finalproject.bcs.attendancemanagement.web;

import com.finalproject.bcs.attendancemanagement.datamodel.StudentRepository;
import com.finalproject.bcs.attendancemanagement.datamodel.Subject;
import com.finalproject.bcs.attendancemanagement.service.DataEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class testController {
@Autowired
 StudentRepository studentRepository;
@Autowired
    DataEntryService dataEntryService;
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
}
