package com.java.untitled.services.impl;

import com.java.untitled.data.entity.Doctor;
import com.java.untitled.data.repository.DoctorRepository;
import com.java.untitled.dto.DoctorDTO;
import com.java.untitled.services.DoctorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<DoctorDTO> get() {
        return doctorRepository.findAll().stream()
                .map(this::convertToDoctorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDTO get(long id) {
        return modelMapper.map(doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id:" + id)), DoctorDTO.class);
    }

    @Override
    public Doctor create(DoctorDTO createDoctorDTO) {
        return doctorRepository.save(modelMapper.map(createDoctorDTO, Doctor.class));
    }

    @Override
    public Doctor update(long id, DoctorDTO updateDoctorDTO) {
        Doctor doctor = modelMapper.map(updateDoctorDTO, Doctor.class);
        doctor.setId(id);
        return doctorRepository.save(doctor);
    }

    @Override
    public void delete(long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public List<DoctorDTO> getGps() {
        return doctorRepository.findAllGps().stream()
                .map(this::convertToDoctorDTO)
                .collect(Collectors.toList());
    }

    private DoctorDTO convertToDoctorDTO(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDTO.class);
    }
}
