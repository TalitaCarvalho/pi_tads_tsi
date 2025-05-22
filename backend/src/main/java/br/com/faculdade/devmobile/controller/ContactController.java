package br.com.faculdade.devmobile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.faculdade.devmobile.controller.body.ContactRequestBody;
import br.com.faculdade.devmobile.dto.AuthResponseBody;
import br.com.faculdade.devmobile.service.ContactService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contato")
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<AuthResponseBody> login(@RequestBody ContactRequestBody body) {
        contactService.createContact(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
