package com.bridgelabz.FundooNote.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.FundooNote.model.LabelsModel;

@Repository
public interface LabelRepository extends JpaRepository<LabelsModel, Integer>{

	Optional<LabelsModel> findBylabelId(Integer labelId);

	Optional<LabelsModel> findByLabelId(int labelId);

}
