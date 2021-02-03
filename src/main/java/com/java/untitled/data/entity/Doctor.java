package com.java.untitled.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "doctor")
public class Doctor extends BaseEntity {

    @NotBlank
    @Size(min = 5, max = 15, message = "Min 5, Max 15")
    private String name;

    @NotBlank
    @Size(min = 9, max = 10, message = "Min 9, Max 10")
    private String identification;

    @Enumerated
    private Specialty specialty;

    @NotNull
    private String isGP;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnoreProperties("doctor")
    private List<Visit> visits;
}
