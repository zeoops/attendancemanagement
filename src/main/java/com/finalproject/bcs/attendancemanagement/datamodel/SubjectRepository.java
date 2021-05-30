package com.finalproject.bcs.attendancemanagement.datamodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    public Subject getSubjectBySubjectCode(String subjectCode);
}
