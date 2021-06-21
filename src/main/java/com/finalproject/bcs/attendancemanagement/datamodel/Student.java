package com.finalproject.bcs.attendancemanagement.datamodel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
//   @OneToMany(targetEntity = Subject.class,cascade = CascadeType.ALL)
//   private List<Subject> subject=new ArrayList<>();

   @OneToMany(mappedBy = "student")
   @JsonIgnore
   private List<Attendance> attendances;

    @ManyToOne()
    @JoinColumn(name="subject_id")
    @JsonBackReference
    private Subject subject;
}
