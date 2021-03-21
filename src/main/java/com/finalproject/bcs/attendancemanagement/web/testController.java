package com.finalproject.bcs.attendancemanagement.web;

import com.finalproject.bcs.attendancemanagement.datamodel.Student;
import com.finalproject.bcs.attendancemanagement.datamodel.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class testController {
@Autowired
 StudentRepository studentRepository;
 @ResponseBody
    @GetMapping("/index")
    public List<Student> getIndex(){
        return studentRepository.findAll();
    }
}
