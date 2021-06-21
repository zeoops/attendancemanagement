package dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttendanceResponse implements Serializable {

    private Boolean attendanceAttempt;
    private String message;
    private String student;
}
