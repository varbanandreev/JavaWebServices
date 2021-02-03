package com.java.untitled.web.controllers;

import com.java.untitled.dto.PatientDTO;
import com.java.untitled.services.DoctorService;
import com.java.untitled.services.PatientService;
import com.java.untitled.web.viewmodels.CreateUpdatePatientModel;
import com.java.untitled.web.viewmodels.PatientViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String getPatients(Model model) {
        final List<PatientViewModel> patients = patientService.get()
                .stream()
                .map(this::convertToPatientViewModel)
                .collect(Collectors.toList());
        model.addAttribute("patients", patients);
        return "/patients/patients";
    }

    @GetMapping("/create-patient")
    public String createPatientForm(Model model) {
        model.addAttribute("patient", new CreateUpdatePatientModel());
        model.addAttribute("gps", doctorService.getGps());
        return "/patients/create-patient";
    }

    @PostMapping("/create")
    public String createPatient(@Valid @ModelAttribute("patient") CreateUpdatePatientModel patient,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("gps", doctorService.getGps());
            return "/patients/create-patient";
        }
        patientService.create(modelMapper.map(patient, PatientDTO.class));
        return "redirect:/patients";
    }

    @GetMapping("/edit-patient/{id}")
    public String editPatientForm(Model model, @PathVariable Long id) {
        model.addAttribute("patient", modelMapper.map(patientService.get(id),
                CreateUpdatePatientModel.class));
        model.addAttribute("gps", doctorService.getGps());
        return "/patients/edit-patient";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable long id, @Valid @ModelAttribute("patient") CreateUpdatePatientModel patient,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("gps", doctorService.getGps());
            return "/patients/edit-patient";
        }
        patientService.update(id, modelMapper.map(patient, PatientDTO.class));
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        patientService.delete(id);
        return "redirect:/patients";
    }

    private PatientViewModel convertToPatientViewModel(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, PatientViewModel.class);
    }
}
