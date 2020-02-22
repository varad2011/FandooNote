package com.bridgelabz.FundooNote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.FundooNote.model.CollaboratorOut;

@Repository
public interface CollaboratorRepository extends JpaRepository<CollaboratorOut, Long>{
	
}
