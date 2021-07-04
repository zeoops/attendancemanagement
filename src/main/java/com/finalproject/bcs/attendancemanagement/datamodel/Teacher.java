package com.finalproject.bcs.attendancemanagement.datamodel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Teacher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private String firstName;
   private String lastName;
   private String email;
//    @OneToMany(targetEntity = Subject.class,cascade = CascadeType.ALL)
//   private List<Subject> subject = new ArrayList<>();
    @Column(unique = true)
    private String userName;

    private String password;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Subject> subjects;



}
