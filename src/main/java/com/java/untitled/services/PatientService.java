package com.java.untitled.services;

import com.java.untitled.data.entity.Patient;
import com.java.untitled.dto.PatientDTO;

import java.util.List;

public interface PatientService {
    List<PatientDTO> get();

    PatientDTO get(long id);

    Patient create(PatientDTO patientDTO);

    Patient update(long id, PatientDTO patientDTO);

    void delete(long id);
}
