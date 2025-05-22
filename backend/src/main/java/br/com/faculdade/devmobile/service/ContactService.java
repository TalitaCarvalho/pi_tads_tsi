package br.com.faculdade.devmobile.service;

import org.springframework.stereotype.Service;

import br.com.faculdade.devmobile.controller.body.ContactRequestBody;
import br.com.faculdade.devmobile.model.ContactModel;
import br.com.faculdade.devmobile.repository.ContactRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactModel createContact(ContactRequestBody body) {
        ContactModel contact = ContactModel.fromContactBody(body);
        return contactRepository.save(contact);
    }

}
