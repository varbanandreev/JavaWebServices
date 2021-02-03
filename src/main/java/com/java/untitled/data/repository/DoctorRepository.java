package com.java.untitled.data.repository;

import com.java.untitled.data.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE d.isGP LIKE 'Yes'")
    List<Doctor> findAllGps();
}
