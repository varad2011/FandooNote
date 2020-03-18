package com.bridgelabz.FundooNote.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooNote.Util.TokenGeneratorDecoder;
import com.bridgelabz.FundooNote.model.LabelsModel;
import com.bridgelabz.FundooNote.model.Note;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.repository.LabelRepository;
import com.bridgelabz.FundooNote.repository.NoteRepository;
import com.bridgelabz.FundooNote.repository.RegistrationPageRepository;
import com.bridgelabz.FundooNote.response.RecordNotFoundException;
import com.bridgelabz.FundooNote.response.Response;

@Service
public class LabelService {

	@Autowired
	private TokenGeneratorDecoder tokenGeneratorDecoder;

	@Autowired
	private RegistrationPageRepository registrationPagerepository;

	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private NoteRepository noteRepository;

	// new label create
	public Response labelCreate(LabelsModel labelModel, String token) {
		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		if (registrationModel.isPresent()) {
			labelModel.setCreateDate();
			labelModel.setRegistrationModel(registrationModel.get());
			labelRepository.save(labelModel);
			return new Response(200, "label Create successfully", null);
		} 
			throw new RecordNotFoundException( "not create label");
	}

	// label delete base on label id
	public Response labelDelete(LabelsModel labelsModel, String token) {
		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		long labelId = labelsModel.getLabelId();
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		if (registrationModel.isPresent()) {
			labelRepository.deleteById((int) labelId);
			return new Response(400, "label Delete Successfully", null);
		} 
			 throw new RecordNotFoundException( "label not delete");
	}

	// display list of label
	public Response  displayLabel(String token) {
		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		if (registrationModel.isPresent()) {
			List<LabelsModel> labelModelAll = labelRepository.findAll();
			List<LabelsModel> labelModelUserCreate = labelModelAll.stream().filter(i -> (i.getRegistrationModel().getId() == id)).collect(Collectors.toList());
			return new Response(401, "label display", labelModelUserCreate );
		} throw new RecordNotFoundException( "not present");
	}

	// update label
	public Response labelUpdate(LabelsModel labelsModel, String token) {
		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		long labelId = labelsModel.getLabelId();
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<LabelsModel> labelModel1 = labelRepository.findBylabelId((int) labelId);
		if (registrationModel.isPresent()) {
			labelModel1.get().setLabelName(labelsModel.getLabelName());
			labelModel1.get().setUpdateDate();
			labelRepository.save(labelModel1.get());
			return new Response(200, "label update", null);
		} 
			throw new RecordNotFoundException( "not present");
	}

	// add note to label
	public Response addNoteToLabel(int noteId, int labelId, String token) {
		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		if (registrationModel.isPresent()) {
			Optional<Note> noteModel = noteRepository.findByNoteId(noteId);
			Optional<LabelsModel> labelModel = labelRepository.findByLabelId(labelId);
			if (noteModel.isPresent() && labelModel.isPresent()) {
				labelModel.get().getNoteModel().add(noteModel.get());
				noteRepository.save(noteModel.get());
				return new Response(200, "added to label", null);
			}
			throw new RecordNotFoundException( "not available in list");
		} 
			throw new RecordNotFoundException( "invalid user");
	}

	// add label to note
	public Response addLabelToNote(int noteId, int labelId, String token) {
		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		if (registrationModel.isPresent()) {
			Optional<Note> noteModel = noteRepository.findByNoteId(noteId);
			Optional<LabelsModel> labelModel = labelRepository.findByLabelId(labelId);
			if (noteModel.isPresent() && labelModel.isPresent()) {
				noteModel.get().getLabelModel().add(labelModel.get());
				noteRepository.save(noteModel.get());
				return new Response(200, "added to note", null);
			}
			throw new RecordNotFoundException( "not available list ");

		} 	throw new RecordNotFoundException( "invalid user");
	}
}
