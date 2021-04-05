package com.finalproject.bcs.attendancemanagement.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name="student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String firstName;
   private String lastName;
   private String contact;
   private String imageLocation;
   @OneToMany(targetEntity = Subject.class,cascade = CascadeType.ALL)
   private List<Subject> subject=new ArrayList<>();
}