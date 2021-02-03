package com.java.untitled.web.viewmodels;

import com.java.untitled.data.entity.Specialty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorViewModel {
    private long id;
    private String name;
    private String identification;
    private Specialty specialty;
    private String isGP;
}
