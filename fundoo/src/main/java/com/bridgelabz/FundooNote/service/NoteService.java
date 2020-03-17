package com.bridgelabz.FundooNote.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.FundooNote.Util.TokenGeneratorDecoder;
import com.bridgelabz.FundooNote.dto.NoteDto;
import com.bridgelabz.FundooNote.model.CollaboratorOut;
import com.bridgelabz.FundooNote.model.Note;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.repository.CollaboratorRepository;
import com.bridgelabz.FundooNote.repository.NoteRepository;
import com.bridgelabz.FundooNote.repository.RegistrationPageRepository;
import com.bridgelabz.FundooNote.response.Response;
import com.cloudinary.Cloudinary;
//import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepsitory;

	@Autowired
	private TokenGeneratorDecoder tokenDecoder;

	@Autowired
	private RegistrationPageRepository registrationPagerepository;

	@Autowired
	private CollaboratorRepository collaboratorRepository;

	// user availability check
	private boolean checkUserExit(String token) {
		System.out.println(token);
		String userId = tokenDecoder.decodeToken(token);
		System.out.println(userId);
		long id = Long.parseLong(userId);
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		if (registrationModel.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	// new note create
	public Response createNewNote(Note model, String token) {
		System.out.println("token" + token);
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		try {
			Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
			if (registrationModel.isPresent()) {
				model.setModel(registrationModel.get());
				model.setAtCreated();
				noteRepsitory.save(model);
				return new Response(200, "saveNote", token);
			} else {
				return new Response(400, "envalid token", token);
			}
		} catch (Exception e) {
			return new Response(500, "e", null);
		}
	}

	// deleteNote
	public Response deleteNote(NoteDto model, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		long noteId = model.getNoteId();
		try {
			Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
			if (registrationModel.isPresent()) {
				noteRepsitory.deleteById((int) noteId);
				return new Response(200, "deleteNote", token);
			} else {

				return new Response(400, "envalid token", token);
			}
		} catch (Exception e) {
			return new Response(500, "e", token);
		}
	}

	// update create notes
	public Response updateNote(NoteDto model, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		long noteId = model.getNoteId();
		try {
			Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
			Optional<Note> noteModel = noteRepsitory.findById((int) noteId);

			if (registrationModel.isPresent()) {
				noteModel.get().setTitle(model.getTitle());
				noteModel.get().setContent(model.getContent());
				noteModel.get().setAtModified();
				noteRepsitory.save(noteModel.get());
				return new Response(200, "update successfully", token);
			} else {
				return new Response(400, "envalid token", token);
			}
		} catch (Exception e) {
			return new Response(500, "e", token);
		}
	}

	// display all user create Notes
	public Response displayUserNote(String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		if (registrationModel.isPresent()) {
			List<Note> noteList = noteRepsitory.findAll();
			List<Note> noteModel1 = noteList.stream().filter(t -> (t.getModel().getId()) == (id))
					.collect(Collectors.toList());
			System.out.println(noteModel1);
			return new Response(200, "display Note successfully", noteModel1);
		} else {
			return new Response(200, "Empty List", null);
		}
	}

	// Sort note by note name
	public Response displayUserNotesName(String name, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		if (registrationModel.isPresent()) {
			List<Note> noteModel = noteRepsitory.findAll();
			List<Note> userNoteList = noteModel.stream().filter(t -> (t.getModel().getId() == (id)))
					.collect(Collectors.toList());
			List<Note> noteModel1 = userNoteList.stream().filter(t -> t.getTitle().equals(name))
					.collect(Collectors.toList());
			return new Response(200, "display Note successfully", noteModel1);
		} else {
			return new Response(200, "Empty List", null);
		}
	}

	// store note to trash
	public Response storeToTrash(long noteId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<Note> noteModel = noteRepsitory.findByNoteId((int) noteId);
		if (registrationModel.isPresent()) {
			if (noteModel.get().isPinUnpin()) {

				noteModel.get().setPinUnpin(false);
			}
			noteModel.get().setTrash(true);
			noteModel.get().setAtModified();
			noteRepsitory.save(noteModel.get());
			return new Response(200, "trash successfully", null);
		} else {
			return new Response(400, "not trush", null);
		}
	}

	// untrash note
	public Response trashToList(long noteId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<Note> noteModel = noteRepsitory.findByNoteId((int) noteId);
		if (registrationModel.isPresent()) {
			noteModel.get().setTrash(false);
			noteModel.get().setAtModified();
			noteRepsitory.save(noteModel.get());
			return new Response(200, "trash to list store successfully", null);
		} else {
			return new Response(400, "not trush to list", null);
		}

	}

	// delete notes from trash permanent deleted data
	public Response deleteFromTrash(int noteId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<Note> noteModel = noteRepsitory.findByNoteId(noteId);

		if ((registrationModel.isPresent()) && (noteModel.get().isTrash())) {
			noteRepsitory.deleteById(noteId);

			return new Response(200, "delete successfully from trash", null);
		} else {
			return new Response(400, "not delete from trush", null);
		}
	}

	// pin unpin notes
	// if note is pin then unpin it or if note is unpin then pin it
	public Response pinUnpin(int noteId, String token) {
		System.out.println("pin unpin" +noteId);
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<Note> noteModel = noteRepsitory.findByNoteId(noteId);
		if (registrationModel.isPresent()) {
			if (!noteModel.get().isPinUnpin()) {
				noteModel.get().setPinUnpin(true);
			} else {
				noteModel.get().setPinUnpin(false);
			}
			noteRepsitory.save(noteModel.get());

			return new Response(200, "note pin Status change", null);
		} else {
			return new Response(400, " not status change", null);
		}
	}

	// note added to archieve
	public Response addToArchieve(int noteId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<Note> noteModel = noteRepsitory.findByNoteId(noteId);

		if ((registrationModel.isPresent()) && (!noteModel.get().isTrash())) {

			if (noteModel.get().isPinUnpin()) {
				noteModel.get().setPinUnpin(false);
				noteModel.get().setArchievePin(true);
			}
			noteModel.get().setArchieve(true);
			noteModel.get().setAtModified();
			noteRepsitory.save(noteModel.get());
			return new Response(200, "note added to archieve", null);
		} else {
			return new Response(400, "not added to archieve", null);
		}
	}

	// note unArchieve
	public Response unArchieveNote(int noteId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<Note> noteModel = noteRepsitory.findByNoteId(noteId);
		if (registrationModel.isPresent()) {
			if (noteModel.get().isArchievePin()) {
				noteModel.get().setPinUnpin(true);
			}
			noteModel.get().setArchievePin(false);
			noteModel.get().setArchieve(false);
			noteRepsitory.save(noteModel.get());
			return new Response(200, " unArchieve successfully", null);
		} else {
			return new Response(200, "not  unArchieve", null);
		}
	}

	// check boolean condition --emailId is already collaborate or not
	public boolean checkList(Optional<Note> noteModel, String emailId) {
		boolean condition = false;
		for (int i = 0; i < noteModel.get().getRegistrationModel().size(); i++) {
			if ((noteModel.get().getRegistrationModel().get(i).getEmailId()).equals(emailId)) {
				System.out.println("123 " + noteModel.get().getRegistrationModel().get(i).getEmailId());
				return condition = true;
			} else {
				condition = false;
			}
		}
		return condition;
	}

	// note collaborate
	public Response collaborateWithEmailId(int noteId, String emailId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> owner = registrationPagerepository.findById(id);
		Optional<Note> noteModel = noteRepsitory.findByNoteId(noteId);
		Optional<RegistrationModel> user1 = registrationPagerepository.findByEmailId(emailId);
		NoteService noteService = new NoteService();
		boolean check = noteService.checkList(noteModel, emailId);
		if (owner.isPresent()) {
			if (check) {
				return new Response(400, "user already collaborate", null);
			} else {
				if (user1.isPresent()) {
					noteModel.get().getRegistrationModel().add(user1.get());
					noteRepsitory.save(noteModel.get());
					return new Response(200, "collabearator ", null);
				} else {
					CollaboratorOut collaboratorOut = new CollaboratorOut();
					collaboratorOut.setColEmaiId(emailId);
					collaboratorRepository.save(collaboratorOut);
					return new Response(200, "added to collaborator", null);
				}
			}
		} else {
			return new Response(400, "user is not available", null);
		}
	}

	// added reminder to note
	public Response addReminder(int noteId, String datetime, String token) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		LocalDateTime dateTime = LocalDateTime.parse(datetime, formatter);
		Optional<RegistrationModel> UserExist = registrationPagerepository.findById(id);
		Optional<Note> noteCheck = noteRepsitory.findByNoteId(noteId);
		System.out.println(noteCheck.get());
		if (UserExist.isPresent()) {
			noteCheck.get().setNoteReminder(true);
			noteCheck.get().setReminderDatTime(dateTime);
			noteRepsitory.save(noteCheck.get());
			return new Response(200, "reminder added successfully", null);
		} else {
			return new Response(400, "user is not present in dataBase", null);
		}
	}

	// remove reminder from note
	public Response removeReminder(int noteId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> UserExist = registrationPagerepository.findById(id);
		Optional<Note> noteCheck = noteRepsitory.findByNoteId(noteId);
		if (UserExist.isPresent()) {
			noteCheck.get().setNoteReminder(false);
			noteCheck.get().setReminderDatTime(null);
			noteRepsitory.save(noteCheck.get());
			return new Response(200, "note reminder remove successfully", null);
		} else {
			return new Response(400, "use is not present in dataBase", null);
		}
	}

	// reminder set notes display for login user
	/*
	 * public Response getAllReminderList(String token) { long id =
	 * Long.parseLong(tokenDecoder.decodeToken(token)); Optional<RegistrationModel>
	 * UserExist = registrationPagerepository.findById(id); if
	 * (checkUserExit(token)) { List<Note> allNotes = noteRepsitory.findAll();
	 * List<Note> userAllNotes = allNotes.stream().filter(i ->
	 * (i.getModel().getId()) == (id)) .collect(Collectors.toList()); List<Note>
	 * reminderSetNotes = userAllNotes.stream().filter(i -> (i.isNoteReminder()))
	 * .collect(Collectors.toList()); }
	 */
	public Response getAllReminderList(String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> UserExist = registrationPagerepository.findById(id);
		if (checkUserExit(token)) {
			List<Note> allNotes = noteRepsitory.findAll();
			List<Note> userAllNotes = allNotes.stream().filter(i -> (i.getModel().getId()) == (id))
					.collect(Collectors.toList());
			List<Note> reminderSetNotes = userAllNotes.stream().filter(i -> (i.isNoteReminder()))
					.collect(Collectors.toList());
			return new Response(400, "use is not present in dataBase", reminderSetNotes);
		} else {
			return new Response(400, "list is empty", null);
		}
	}

	// remind notes to user when settime == reminder time;

	public List<Note> remindNotesToUser(String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		List<Note> allNotes = noteRepsitory.findAll();
		List<Note> userAllNotes = allNotes.stream().filter(i -> (i.getModel().getId()) == (id))
				.collect(Collectors.toList());
		List<Note> reminderAllNotes = userAllNotes.stream()
				.filter(i -> (i.getReminderDatTime()).equals(LocalDateTime.now())).collect(Collectors.toList());
		if (checkUserExit(token)) {
			if (!reminderAllNotes.isEmpty()) {
				return reminderAllNotes;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public boolean checkCollaboratorEmailList(Optional<CollaboratorOut> collaboratorListEmail, String emailId,
			int noteId) {
		boolean condition = false;
		for (int i = 0; i < collaboratorListEmail.get().getNoteList().size(); i++) {
			if ((collaboratorListEmail.get().getNoteList().get(i).getNoteId()) == noteId) {
				System.out.println((collaboratorListEmail.get().getNoteList().get(i).getNoteId()) == noteId);
				System.out.println("123 " + (collaboratorListEmail.get().getNoteList().get(i).getNoteId()));
				return condition = true;
			} else {
				condition = false;
			}
		}
		return condition;
	}

	public Response collaboratorUsingJoinTable(int noteId, String emailId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> owner = registrationPagerepository.findById(id);
		Optional<RegistrationModel> emailIdUser = registrationPagerepository.findByEmailId(emailId);
		Optional<CollaboratorOut> checkEmailId = collaboratorRepository.findByColEmaiId(emailId);
		Optional<Note> noteModel = noteRepsitory.findByNoteId(noteId);
		if (!checkEmailId.isPresent()) {
			CollaboratorOut collaboratorOut = new CollaboratorOut();
			collaboratorOut.setColEmaiId(emailId);
			collaboratorRepository.save(collaboratorOut);
			Optional<CollaboratorOut> findCollaboratorId = collaboratorRepository.findByColEmaiId(emailId);
			noteModel.get().getCollaboratorOutsList().add(findCollaboratorId.get());
			noteRepsitory.save(noteModel.get());
			return new Response(200, "collaborated with new user", null);
		} else {
			boolean emailExitCheckWithNoteId = checkCollaboratorEmailList(checkEmailId, emailId, noteId);
			if (emailExitCheckWithNoteId) {
				return new Response(200, "already collaboratoed ", null);
			} else {
				noteModel.get().getCollaboratorOutsList().add(checkEmailId.get());
				noteRepsitory.save(noteModel.get());
				return new Response(200, "collaborated with already available ", null);
			}
		}
	}

	public Response uploadProPic(MultipartFile file, String token) throws IOException {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> loginUser = registrationPagerepository.findById(id);
		if (checkUserExit(token)) {
			File uploadFile = new File(file.getOriginalFilename());
			BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			outStream.write(file.getBytes());
			outStream.close();
			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "patilvarad2011", "api_key",
					"814469859839278", "api_secret", "xyd8OV451Efmc9DvwFKL0xxNfi4"));
			Map<?, ?> uploadProfile;
			uploadProfile = cloudinary.uploader().upload(uploadFile, ObjectUtils.emptyMap());
			loginUser.get().setProfilePic(uploadProfile.get("secure_url").toString());
			registrationPagerepository.save(loginUser.get());
			return new Response(200, "imageUploaded successfully", null);
		} else {
			return new Response(200, "some issues are here", null);
		}
	}

	@Scheduled(cron = "0 0 1 * * *", zone = "Asia/Calcutta")
	// @Scheduled(cron = "0 * 7 * * ?")
	// @Scheduled(fixedRate = 5000)
	public void deleteTrashShduled() {
		List<Note> allNote = noteRepsitory.findAll();
		List<Note> trashNote = allNote.stream().filter(i -> i.isTrash()).collect(Collectors.toList());
		for (int i = 0; i <= trashNote.size(); i++) {
			if (trashNote.get(i).getTrashDate().isAfter(LocalDate.now())) {
				noteRepsitory.delete(trashNote.get(i));
			}
		}
	}

	public Response getArchivedList(String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		List<Note> allNotes = noteRepsitory.findAll();
		List<Note> userAllNotes = allNotes.stream().filter(i -> (i.getModel().getId()) == (id))
				.collect(Collectors.toList());
		System.out.println(userAllNotes);
		List<Note> reminderAllNotes = userAllNotes.stream().filter(i -> i.isArchieve()).collect(Collectors.toList());
		System.out.println(reminderAllNotes);
		if (!reminderAllNotes.isEmpty()) {
			return new Response(200, "Display Archive List", reminderAllNotes);
		} else {
			return new Response(200, "empty List", null);
		}
	}

	public Response getTrashList(String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		List<Note> allNotes = noteRepsitory.findAll();
		List<Note> userAllNotes = allNotes.stream().filter(i -> (i.getModel().getId()) == (id))
				.collect(Collectors.toList());
		List<Note> reminderAllNotes = userAllNotes.stream().filter(i -> i.isTrash()).collect(Collectors.toList());
		if (!reminderAllNotes.isEmpty()) {
			return new Response(200, "dispaly list", reminderAllNotes);
		} else {
			return new Response(200, "empty", null);
		}
	}
}