package com.finalproject.bcs.attendancemanagement.datamodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
public Student findStudentByFirstName(String firstName);

public Student findByFirstNameAndSubject(String firstName,Subject subject);


}
