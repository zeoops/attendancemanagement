package com.finalproject.bcs.attendancemanagement.service;


import com.finalproject.bcs.attendancemanagement.datamodel.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
public class AttendanceService {


    private String path="/home/deeplearning/images";

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;

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

    public void uploadStudentData(MultipartFile file){
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CsvToBean<StudentRecord> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(StudentRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();


            List<StudentRecord> students = csvToBean.parse();
            String test="";

        for(StudentRecord studentRecord:students){
            Student student=new Student();
            Subject subject= subjectRepository.getSubjectBySubjectCode(studentRecord.getSubject());
            student.setFirstName(studentRecord.getFirstName());
            student.setLastName(studentRecord.getLastName());
            student.setSubject(subject);
            studentRepository.save(student);
        }

        } catch (Exception ex) {
        }
    }
}
