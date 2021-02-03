package com.java.untitled.data.repository;

import com.java.untitled.data.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query("SELECT v FROM Visit v INNER JOIN Patient p ON v.patient.id = p.id WHERE p.name = :name")
    List<Visit> findAllByPatientName(@Param("name") String name);
}
