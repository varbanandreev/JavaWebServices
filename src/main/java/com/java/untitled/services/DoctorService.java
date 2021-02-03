package com.java.untitled.services;

import com.java.untitled.data.entity.Doctor;
import com.java.untitled.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {
    List<DoctorDTO> get();

    DoctorDTO get(long id);

    Doctor create(DoctorDTO doctorDTO);

    Doctor update(long id, DoctorDTO doctorDTO);

    void delete(long id);

    List<DoctorDTO> getGps();
}
