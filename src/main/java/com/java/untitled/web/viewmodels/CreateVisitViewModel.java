package com.java.untitled.web.viewmodels;

import com.java.untitled.data.entity.Doctor;
import com.java.untitled.data.entity.Patient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class CreateVisitViewModel {

    private Patient patient;

    private Doctor doctor;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "The date has to be in the past!")
    private Date date;

    @NotBlank
    @Size(min = 5, max = 50, message = "Min 5, Max 50")
    private String diagnosis;

    @NotBlank
    @Size(min = 5, max = 100, message = "Min 5, Max 100")
    private String treatment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sickLeaveStart;

    @Max(value = 30, message = "Max 30")
    private Integer sickLeaveDays;
}
