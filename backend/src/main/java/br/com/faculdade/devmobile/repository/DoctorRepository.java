package br.com.faculdade.devmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdade.devmobile.model.DoctorModel;

public interface DoctorRepository extends JpaRepository<DoctorModel, Integer> {
}
