package br.com.faculdade.devmobile.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_MEDICO")
public class DoctorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MEDICO")
    private Integer id;

    @Column(name = "NOME_PROFISSIONAL", nullable = false)
    private String name;

    @Column(name = "CRM", nullable = false)
    private Integer crm;

    @Column(name = "CRM_ESTADO", nullable = false)
    private String stateCRM;

    @Column(name = "ESPECIALIDADE", nullable = false)
    private String specialty;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<ScheduleModel> schedules;
}
