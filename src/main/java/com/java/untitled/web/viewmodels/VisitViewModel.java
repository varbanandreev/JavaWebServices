package com.java.untitled.web.viewmodels;

import com.java.untitled.data.entity.Doctor;
import com.java.untitled.data.entity.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class VisitViewModel {
    private long id;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String diagnosis;
    private String treatment;
    private Date sickLeaveStart;
    private Integer sickLeaveDays;
}
