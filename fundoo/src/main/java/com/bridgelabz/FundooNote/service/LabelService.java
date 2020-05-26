package com.bridgelabz.FundooNote.service;

import java.util.ArrayList;
import java.util.Iterator;
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
		throw new RecordNotFoundException("not create label");
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
		throw new RecordNotFoundException("label not delete");
	}

	// display list of label
	public Response displayLabel(String token) {
		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		if (registrationModel.isPresent()) {
			List<LabelsModel> labelModelAll = labelRepository.findAll();
			List<LabelsModel> labelModelUserCreate = labelModelAll.stream()
					.filter(i -> (i.getRegistrationModel().getId() == id)).collect(Collectors.toList());
			return new Response(200, "label display", labelModelUserCreate);
		}
		throw new RecordNotFoundException("not present");
	}

	// get list of label added to note
	public Response displayLabelAddedToNote(String token, int noteId) {
		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		Optional<RegistrationModel> user = registrationPagerepository.findById(id);
		if (user.isPresent()) {
			List<LabelsModel> labelModelAll = labelRepository.findAll();
			List<LabelsModel> labelModelUserCreate = labelModelAll.stream()
					.filter(i -> (i.getRegistrationModel().getId() == id)).collect(Collectors.toList());
			List<LabelsModel> sortLabel = new ArrayList<LabelsModel>();
			for (int j = 0; j < labelModelUserCreate.size(); j++) {
				for (int j2 = 0; j2 < labelModelUserCreate.get(j).getNoteModel().size(); j2++) {
					if((labelModelUserCreate.get(j).getNoteModel().get(j2).getNoteId())  == noteId) {
						sortLabel.add(labelModelUserCreate.get(j));
						System.out.println("label"+labelModelUserCreate.get(j));
					}
				}
			}
			return new Response(200, "label display", sortLabel);
		}
		throw new RecordNotFoundException("not present user");
	}

	// get list of label not added to current open note
	public Response displayLabelUnAddedToNote(String token, int noteId) {
		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<Note> openNoteModel = noteRepository.findById(noteId);
		if (registrationModel.isPresent()) {
			List<LabelsModel> labelModelAll = labelRepository.findAll();
			List<LabelsModel> labelModelUserCreate = labelModelAll.stream()
					.filter(i -> (i.getRegistrationModel().getId() == id)).collect(Collectors.toList());
			List<LabelsModel> sortLabel = new ArrayList<LabelsModel>();
			sortLabel.addAll(labelModelUserCreate);
			for (int j = 0; j < labelModelUserCreate.size(); j++) {
				for (int j2 = 0; j2 < labelModelUserCreate.get(j).getNoteModel().size(); j2++) {
					if((labelModelUserCreate.get(j).getNoteModel().get(j2).getNoteId()) == noteId) {
						sortLabel.remove(labelModelUserCreate.get(j));
					}
				}
			}
			return new Response(200, "label display", sortLabel);
		}
		throw new RecordNotFoundException("not present user");
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
			System.out.println(labelModel1);
			return new Response(200, "label update", null);
		}
		throw new RecordNotFoundException("not present");
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
			throw new RecordNotFoundException("not available in list");
		}
		throw new RecordNotFoundException("invalid user");
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
			throw new RecordNotFoundException("not available list ");
		}
		throw new RecordNotFoundException("invalid user");
	}

	public Response getAllNoteByLabelId(int labelId, String token) {
		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<LabelsModel> labelModel = labelRepository.findByLabelId(labelId);
		if ((registrationModel.isPresent()) && (labelModel.isPresent())) {
//			List<Note> getNote = noteRepository.findAll();
			List<Note> labelNote = labelModel.get().getNoteModel();
//			List<Note>unTrashLabelNote = labelNote.stream().filter(i -> i.isTrash() == false).collect(Collectors.toList());
			List<Note> notPinListOfNote = labelNote.stream()
					.filter(i -> i.isPinUnpin() == false && i.isTrash() == false).collect(Collectors.toList());
			System.out.println(notPinListOfNote + "listOfAllLAbelNotes");
			return new Response(200, "displayLisOfNote", notPinListOfNote);
		}
		throw new RecordNotFoundException("invalid user");
	}

	public Response getAllPinNoteByLabelId(int labelId, String token) {
		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<LabelsModel> labelModel = labelRepository.findByLabelId(labelId);
		if ((registrationModel.isPresent()) && (labelModel.isPresent())) {
//			List<Note> getNote = noteRepository.findAll();
			List<Note> labelNote = labelModel.get().getNoteModel();
			List<Note> unTrashLabelNote = labelNote.stream().filter(i -> (i.isTrash() == false))
					.collect(Collectors.toList());
			List<Note> pinLabelNote = unTrashLabelNote.stream().filter(i -> (i.isPinUnpin() == true))
					.collect(Collectors.toList());
			System.out.println(pinLabelNote + "listOfAllLAbelNotes");
			return new Response(200, "displayLisOfNote", pinLabelNote);
		}
		throw new RecordNotFoundException("invalid user");
	}

	public Response labelRemoveFromNote(int noteId, int labelId, String token) {
		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		if (registrationModel.isPresent()) {
			Optional<Note> noteModel = noteRepository.findByNoteId(noteId);
			Optional<LabelsModel> labelModel = labelRepository.findByLabelId(labelId);
			if (noteModel.isPresent() && labelModel.isPresent()) {
				noteModel.get().getLabelModel().remove(labelModel.get());
				noteRepository.save(noteModel.get());
				return new Response(200, "label remove ", null);
			}
			throw new RecordNotFoundException("not available list ");
		}
		throw new RecordNotFoundException("invalid user");
	}
}
