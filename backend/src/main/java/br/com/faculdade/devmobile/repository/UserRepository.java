package br.com.faculdade.devmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdade.devmobile.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByEmail(String email);
}
