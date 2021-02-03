package com.java.untitled.web.controllers;

import com.java.untitled.dto.VisitDTO;
import com.java.untitled.services.DoctorService;
import com.java.untitled.services.PatientService;
import com.java.untitled.services.VisitService;
import com.java.untitled.web.viewmodels.CreateVisitViewModel;
import com.java.untitled.web.viewmodels.VisitViewModel;
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
@RequestMapping("/visits")
public class VisitController {
    private final VisitService visitService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String getVisits(Model model) {
        return "/visits/search";
    }

    @GetMapping("/patient")
    public String getVisitsByPatientName(Model model, @RequestParam String patientName) {
        final List<VisitViewModel> visits = visitService.getVisitsByPatientName(patientName)
                .stream()
                .map(this::convertToVisitViewModel)
                .collect(Collectors.toList());
        model.addAttribute("visits", visits);
        return "/visits/visits";
    }

    @GetMapping("/create-visit")
    public String createVisitForm(Model model) {
        model.addAttribute("visit", new CreateVisitViewModel());
        model.addAttribute("patients", patientService.get());
        model.addAttribute("doctors", doctorService.get());
        return "/visits/create-visit";
    }

    @PostMapping("/create")
    public String createVisit(@Valid @ModelAttribute("visit") CreateVisitViewModel visit,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patients", patientService.get());
            model.addAttribute("doctors", doctorService.get());
            return "/visits/create-visit";
        }
        visitService.create(modelMapper.map(visit, VisitDTO.class));
        return "redirect:/visits";
    }

    private VisitViewModel convertToVisitViewModel(VisitDTO visitDTO) {
        return modelMapper.map(visitDTO, VisitViewModel.class);
    }
}
