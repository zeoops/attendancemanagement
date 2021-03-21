package com.finalproject.bcs.attendancemanagement.datamodel;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "teacher_id",nullable = false)
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "subject_id",nullable = false)
    private Subject subject;

}
