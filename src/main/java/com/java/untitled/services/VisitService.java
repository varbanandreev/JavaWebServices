package com.java.untitled.services;

import com.java.untitled.data.entity.Visit;
import com.java.untitled.dto.VisitDTO;

import java.util.List;

public interface VisitService {
    List<VisitDTO> get();

    VisitDTO get(long id);

    Visit create(VisitDTO visitDTO);

    Visit update(long id, VisitDTO visitDTO);

    List<VisitDTO> getVisitsByPatientName(String patientName);

    void delete(long id);
}
