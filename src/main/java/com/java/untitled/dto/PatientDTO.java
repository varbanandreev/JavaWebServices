package com.java.untitled.dto;

import com.java.untitled.data.entity.Doctor;
import com.java.untitled.data.entity.Visit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatientDTO {
    private long id;
    private String name;
    private String EGN;
    private Doctor GP;
    private List<Visit> visits;
}
