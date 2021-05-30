package com.finalproject.bcs.attendancemanagement.datamodel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceReporsitory extends JpaRepository<Attendance,Long> {
}
