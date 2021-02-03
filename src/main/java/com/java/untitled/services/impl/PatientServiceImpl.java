package com.java.untitled.services.impl;

import com.java.untitled.data.entity.Patient;
import com.java.untitled.data.repository.PatientRepository;
import com.java.untitled.dto.PatientDTO;
import com.java.untitled.services.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<PatientDTO> get() {
        return patientRepository.findAll().stream()
                .map(this::convertToPatientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO get(long id) {
        return modelMapper.map(patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id)), PatientDTO.class);
    }

    @Override
    public Patient create(PatientDTO createPatientDTO) {
        return patientRepository.save(modelMapper.map(createPatientDTO, Patient.class));
    }

    @Override
    public Patient update(long id, PatientDTO updatePatientDTO) {
        Patient patient = modelMapper.map(updatePatientDTO, Patient.class);
        patient.setId(id);
        return patientRepository.save(patient);
    }

    @Override
    public void delete(long id) {
        patientRepository.deleteById(id);
    }

    private PatientDTO convertToPatientDTO(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
    }
}
