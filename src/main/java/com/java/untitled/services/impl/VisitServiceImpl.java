package com.java.untitled.services.impl;

import com.java.untitled.data.entity.Visit;
import com.java.untitled.data.repository.VisitRepository;
import com.java.untitled.dto.VisitDTO;
import com.java.untitled.services.VisitService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<VisitDTO> get() {
        return visitRepository.findAll().stream()
                .map(this::convertToVisitDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VisitDTO get(long id) {
        return modelMapper.map(visitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid visit Id:" + id)), VisitDTO.class);
    }

    @Override
    public Visit create(VisitDTO createVisitDTO) {
        return visitRepository.save(modelMapper.map(createVisitDTO, Visit.class));
    }

    @Override
    public Visit update(long id, VisitDTO updateVisitDTO) {
        Visit visit = modelMapper.map(updateVisitDTO, Visit.class);
        visit.setId(id);
        return visitRepository.save(visit);
    }

    @Override
    public void delete(long id) {
        visitRepository.deleteById(id);
    }

    @Override
    public List<VisitDTO> getVisitsByPatientName(String patientName) {
        return visitRepository.findAllByPatientName(patientName).stream()
                .map(this::convertToVisitDTO)
                .collect(Collectors.toList());
    }

    private VisitDTO convertToVisitDTO(Visit visit) {
        return modelMapper.map(visit, VisitDTO.class);
    }
}
