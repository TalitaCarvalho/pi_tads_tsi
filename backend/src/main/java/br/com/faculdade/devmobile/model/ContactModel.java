package br.com.faculdade.devmobile.model;

import br.com.faculdade.devmobile.controller.body.ContactRequestBody;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@Table(name = "TB_CONTATO")
public class ContactModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "MENSAGEM", nullable = false)
    private String mensagem;

    public static ContactModel fromContactBody(ContactRequestBody body) {
        return ContactModel.builder()
                .name(body.name())
                .email(body.email())
                .mensagem(body.message())
                .build();
    }

}
