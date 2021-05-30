//package com.finalproject.bcs.attendancemanagement.datamodel;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//public class Attendance {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long Id;
//    private Date date;
//    @ManyToOne
//    @JoinColumn(name = "student_id",nullable = false)
//    private Student student;
//    @ManyToOne
//    @JoinColumn(name = "subject_id",nullable = false)
//    private Subject subject;
//
//}
