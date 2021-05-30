package com.finalproject.bcs.attendancemanagement.service;


import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class AttendanceService {


    private String path="/home/deeplearning/images";


    public String saveImage(String imageString){
//        byte[] decodedBytes = Base64.getDecoder().decode(imageString);

        String student="Unknown Student";
        String partSeparator = ",";
        if (imageString.contains(partSeparator)) {
            String encodedImg = imageString.split(partSeparator)[1];
            byte[] decodedImg = Base64.getDecoder().decode(encodedImg.getBytes(StandardCharsets.UTF_8));
            try {
                FileUtils.writeByteArrayToFile(new File(path+"/unknown/unknown.jpeg"), decodedImg);
                String s;
                Process p;
                try {
                    String command="face_recognition "+path+"/known/ "+path+"/unknown/";
                    p = Runtime.getRuntime().exec(command);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(p.getInputStream()));
                    while ((s = br.readLine()) != null) {
                        System.out.println("line: " + s);
                        if (s.contains("unknown.jpeg")) {
                            student = "Student is " + s.split(",")[1];
                        }
                    }
                    p.waitFor();
                    System.out.println ("exit: " + p.exitValue());
                    p.destroy();
                } catch (Exception e) {}
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

return student;
    }
}
