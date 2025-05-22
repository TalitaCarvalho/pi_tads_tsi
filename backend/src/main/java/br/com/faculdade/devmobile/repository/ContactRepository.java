package br.com.faculdade.devmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdade.devmobile.model.ContactModel;

public interface ContactRepository extends JpaRepository<ContactModel, Integer> {
}
