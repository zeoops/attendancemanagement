package com.finalproject.bcs.attendancemanagement.service;

import com.finalproject.bcs.attendancemanagement.datamodel.Subject;
import com.finalproject.bcs.attendancemanagement.datamodel.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataEntryService {
    @Autowired
    SubjectRepository subjectRepository;
    public void saveSubject(Subject subject){
        subjectRepository.save(subject);
    }

}
