package com.java.untitled.web.viewmodels;

import com.java.untitled.data.entity.Doctor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientViewModel {
    private long id;
    private String name;
    private String EGN;
    private Doctor GP;
}
