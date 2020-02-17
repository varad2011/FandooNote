package com.bridgelabz.FundooNote.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.FundooNote.model.RegistrationModel;

@Repository
public interface RegistrationPageRepository extends JpaRepository<RegistrationModel, Long>  {

	Optional<RegistrationModel> findByEmailId(String emailId);
}
