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
@Table(name = "TB_CLINICAS")
public class ClinicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOCAL")
    private Integer id;

    @Column(name = "NOME_CLINICA", nullable = false)
    private String name;

    @Column(name = "ENDERECO", nullable = false)
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<ScheduleModel> schedules;
}
