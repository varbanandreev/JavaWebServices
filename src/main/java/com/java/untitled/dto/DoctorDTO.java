package com.java.untitled.dto;

import com.java.untitled.data.entity.Specialty;
import com.java.untitled.data.entity.Visit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDTO {
    private long id;
    private String name;
    private String identification;
    private Specialty specialty;
    private String isGP;
    private List<Visit> visits;
}
