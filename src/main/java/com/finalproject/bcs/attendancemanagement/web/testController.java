package com.finalproject.bcs.attendancemanagement.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class testController {

    @GetMapping("/index")
    public String getIndex(){
        return "test";
    }
}
