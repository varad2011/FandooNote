package com.bridgelabz.FundooNote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.FundooNote.model.RegistrationModel;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationModel, Long> {
	
	RegistrationModel findByEmailId(String emailId);
		
}
