package com.finalproject.bcs.attendancemanagement.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRecord {
    private String firstName;
    private String lastName;
    private String contact;
    private String subject;

}
