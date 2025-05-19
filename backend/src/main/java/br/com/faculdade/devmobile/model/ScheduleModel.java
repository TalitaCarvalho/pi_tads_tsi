package br.com.faculdade.devmobile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_AGENDAMENTO")
public class ScheduleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATENDIMENTO")
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    @NotNull(message = "O campo usuário é obrigatório")
    private UserModel user;

    @NotNull(message = "O campo tratamento é obrigatório")
    @Column(name = "TRATAMENTO", nullable = false)
    private String treatment;

    @NotNull(message = "O campo horário é obrigatório")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORARIO", nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "ID_MEDICO", nullable = false)
    @NotNull(message = "O campo médico é obrigatório")
    private DoctorModel doctor;

    @NotNull(message = "O campo virtual é obrigatório")
    @Column(name = "VIRTUAL", nullable = false)
    private Boolean online;

    @NotNull(message = "O campo presencial é obrigatório")
    @Column(name = "PRESENCIAL", nullable = false)
    private Boolean inPerson;

    @ManyToOne
    @JoinColumn(name = "ID_LOCAL", nullable = false)
    @NotNull(message = "O campo clínica é obrigatório")
    private ClinicModel clinic;
}
