package br.com.faculdade.devmobile.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_PERFIL_USUARIO")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer id;

    @Column(name = "NOME", nullable = false)
    @NotNull(message = "O campo nome deve ser preenchido")
    private String name;

    @Column(name = "CPF", nullable = false)
    @NotNull(message = "O campo cpf deve ser preenchido")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos")
    private String cpf;

    @Column(name = "EMAIL", nullable = false)
    @NotNull(message = "O campo email deve ser preenchido")
    @Email(message = "O campo email deve ser um email válido")
    private String email;

    @Column(name = "ENDERECO")
    private String address;

    @Column(name = "CEP")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 dígitos")
    private String cep;

    @Column(name = "CIDADE")
    private String city;

    @Column(name = "ESTADO")
    private String state;

    @Column(name = "UF")
    @Size(min = 2, max = 2, message = "A UF deve conter exatamente 2 caracteres")
    private String uf;

    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter 10 ou 11 dígitos")
    @Column(name = "TELEFONE")
    private String phone;

    @Past(message = "A data de nascimento não pode ser maior que a data atual")
    @JsonProperty("birth_date")
    @Column(name = "DATA_DE_NASCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "PACIENTE")
    private Boolean isPatient;

    @Column(name = "FAMILIAR")
    private Boolean isFamily;

    @Column(name = "SENHA", nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ScheduleModel> schedules;

}
