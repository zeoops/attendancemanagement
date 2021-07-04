package com.finalproject.bcs.attendancemanagement.datamodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    public Subject getSubjectBySubjectCode(String subjectCode);

    public List<Subject> getSubjectByTeacher(Teacher teacher);
}
