package br.com.faculdade.devmobile.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequestBody {

    Integer id;

    @NotNull(message = "O campo usuário é obrigatório")
    Integer user;

    @NotNull(message = "O campo tratamento é obrigatório")
    String treatment;

    @NotNull(message = "O campo horário é obrigatório")
    String date;

    @NotNull(message = "O campo médico é obrigatório")
    Integer doctor;

    Boolean online = true;

    Boolean inPerson = false;

    @NotNull(message = "O campo clínica é obrigatório")
    Integer clinic;
}
