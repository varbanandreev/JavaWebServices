package com.java.untitled.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends BaseEntity {

    @NotBlank
    @Size(min = 5, max = 15, message = "Min 5, Max 15")
    private String name;

    @NotBlank
    @Size(min = 10, max = 11, message = "Min 10, Max 11")
    private String EGN;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor GP;

    @OneToMany(mappedBy = "patient")
    @JsonIgnoreProperties("patient")
    private List<Visit> visits;
}
