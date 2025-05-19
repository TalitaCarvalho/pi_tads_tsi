package br.com.faculdade.devmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdade.devmobile.model.ClinicModel;

public interface ClinicRepository extends JpaRepository<ClinicModel, Integer> {
}
