package com.java.untitled.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "visit")
public class Visit extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
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

    private Date sickLeaveStart;

    @Max(value = 30, message = "Max 30")
    private Integer sickLeaveDays;
}
