package com.java.untitled.web.controllers;

import com.java.untitled.data.entity.Specialty;
import com.java.untitled.dto.DoctorDTO;
import com.java.untitled.services.DoctorService;
import com.java.untitled.web.viewmodels.CreateUpdateDoctorModel;
import com.java.untitled.web.viewmodels.DoctorViewModel;
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
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String getDoctors(Model model) {
        final List<DoctorViewModel> doctors = doctorService.get()
                .stream()
                .map(this::convertToDoctorViewModel)
                .collect(Collectors.toList());
        model.addAttribute("doctors", doctors);
        return "/doctors/doctors";
    }

    @GetMapping("/create-doctor")
    public String createDoctorForm(Model model) {
        model.addAttribute("doctor", new CreateUpdateDoctorModel());
        model.addAttribute("specialties", Specialty.values());
        return "/doctors/create-doctor";
    }

    @PostMapping("/create")
    public String createDoctor(@Valid @ModelAttribute("doctor") CreateUpdateDoctorModel doctor,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("specialties", Specialty.values());
            return "/doctors/create-doctor";
        }
        doctorService.create(modelMapper.map(doctor, DoctorDTO.class));
        return "redirect:/doctors";
    }

    @GetMapping("/edit-doctor/{id}")
    public String editDoctorForm(Model model, @PathVariable Long id) {
        model.addAttribute("doctor", modelMapper.map(doctorService.get(id),
                CreateUpdateDoctorModel.class));
        model.addAttribute("specialties", Specialty.values());
        return "/doctors/edit-doctor";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable long id, @Valid @ModelAttribute("doctor") CreateUpdateDoctorModel doctor,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("specialties", Specialty.values());
            return "/doctors/edit-doctor";
        }
        doctorService.update(id, modelMapper.map(doctor, DoctorDTO.class));
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        doctorService.delete(id);
        return "redirect:/doctors";
    }

    private DoctorViewModel convertToDoctorViewModel(DoctorDTO doctorDTO) {
        return modelMapper.map(doctorDTO, DoctorViewModel.class);
    }
}
