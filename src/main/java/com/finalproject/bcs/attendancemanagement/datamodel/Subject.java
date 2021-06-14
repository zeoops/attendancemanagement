package com.finalproject.bcs.attendancemanagement.datamodel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String subjectName;
//    @OneToMany(mappedBy = "subject")
//    private List<Attendance> attendances;
    private String subjectCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="semester_id")
    private Semester semester;

    @OneToMany(mappedBy = "subject",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Student> students=new ArrayList<>();

}

