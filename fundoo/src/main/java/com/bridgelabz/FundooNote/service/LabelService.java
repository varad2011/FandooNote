package com.bridgelabz.FundooNote.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooNote.Util.TokenGeneratorDecoder;
import com.bridgelabz.FundooNote.model.LabelsModel;
import com.bridgelabz.FundooNote.model.Note;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.repository.LabelRepository;
import com.bridgelabz.FundooNote.repository.NoteRepository;
import com.bridgelabz.FundooNote.repository.RegistrationPageRepository;
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
		System.out.println("www");
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);

		if (registrationModel.isPresent()) {
			// labelModel.setLabelName(labelDto.getLabelName());
			labelModel.setCreateDate();
			labelModel.setRegistrationModel(registrationModel.get());
			System.out.println(labelModel);
			labelRepository.save(labelModel);

			return new Response(200, "label Create successfully", null);
		} else {

			return new Response(401, "not create label", null);
		}
	}

	// label delete base on label id
	public Response labelDelete(LabelsModel labelsModel, String token) {

		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		long labelId = labelsModel.getLabelId();
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);

		if (registrationModel.isPresent()) {
			labelRepository.deleteById((int) labelId);
			return new Response(400, "label Delete Successfully", null);
		} else {

			return new Response(200, "label not delete", null);
		}
	}

	// display list of label
	public List<LabelsModel> displayLabel(String token) {

		Long id = Long.parseLong(tokenGeneratorDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);

		if (registrationModel.isPresent()) {
			List<LabelsModel> labelModel = labelRepository.findAll();
			//bellow commented code is for arrays.sort() method implementaion to sort list		
			/*
			 * Object[] objects = labelModel.toArray(); Arrays.sort(objects);
			 * 
			 */
			return labelModel;
		} else {

			return null;
		}
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
		} else {

			return new Response(401, "not update ", null);
		}
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
			return new Response(400, "list or label not avialble", null);
		} else {

			return new Response(400, "fail to add note ", null);
		}
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
			return new Response(400, "list or label not avialble", null);

		} else {

			return new Response(400, "fail to add note ", null);
		}
	}
}
