/*
 * package com.bridgelabz.FundooNote.elasticSearch;
 * 
 * import java.time.LocalDate;
 * 
 * 
 * import java.time.LocalDateTime; import java.util.List;
 * 
 * import javax.persistence.CascadeType; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.JoinColumn; import
 * javax.persistence.JoinTable; import javax.persistence.ManyToMany; import
 * javax.persistence.ManyToOne; import javax.validation.constraints.NotBlank;
 * 
 * import org.springframework.data.elasticsearch.annotations.Document; import
 * org.springframework.stereotype.Component;
 * 
 * import com.bridgelabz.FundooNote.model.CollaboratorOut; import
 * com.bridgelabz.FundooNote.model.LabelsModel; import
 * com.bridgelabz.FundooNote.model.RegistrationModel; import
 * com.fasterxml.jackson.annotation.JsonIgnore; import
 * com.fasterxml.jackson.annotation.JsonIgnoreProperties; //@Entity //@Component
 * 
 * @Document(indexName = "note", type = "note", shards = 1) public class
 * NoteElastic {
 * 
 * @Id private int noteId; private String title; private String content; private
 * LocalDateTime atCreated; private LocalDateTime atModified; private boolean
 * trash; private LocalDate trashDate; private boolean pinUnpin; private boolean
 * archieve; public NoteElastic() { super(); // TODO Auto-generated constructor
 * stub }
 * 
 * public NoteElastic(int noteId, String title, String content, LocalDateTime
 * atCreated, LocalDateTime atModified, boolean trash, LocalDate trashDate,
 * boolean pinUnpin, boolean archieve, boolean archievePin, boolean
 * noteReminder, LocalDateTime reminderDatTime, String backgroundColor,
 * RegistrationModel model, List<RegistrationModel> registrationModel,
 * List<LabelsModel> labelModel, List<CollaboratorOut> collaboratorOutsList) {
 * super(); this.noteId = noteId; this.title = title; this.content = content;
 * this.atCreated = atCreated; this.atModified = atModified; this.trash = trash;
 * this.trashDate = trashDate; this.pinUnpin = pinUnpin; this.archieve =
 * archieve; this.archievePin = archievePin; this.noteReminder = noteReminder;
 * this.reminderDatTime = reminderDatTime; this.backgroundColor =
 * backgroundColor; this.model = model; this.registrationModel =
 * registrationModel; this.labelModel = labelModel; this.collaboratorOutsList =
 * collaboratorOutsList; }
 * 
 * private boolean archievePin; private boolean noteReminder; private
 * LocalDateTime reminderDatTime; private String backgroundColor;
 * 
 * public String getBackgroundColor() { return backgroundColor; }
 * 
 * public void setBackgroundColor(String backgroundColor) { this.backgroundColor
 * = backgroundColor; }
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "id")
 * 
 * @JsonIgnore private RegistrationModel model;
 * 
 * @ManyToMany(cascade = CascadeType.ALL)
 * 
 * @JoinTable(name = "emailJoin", joinColumns = @JoinColumn(name = "noteId"),
 * inverseJoinColumns = @JoinColumn(name = "id"))
 * 
 * @JsonIgnoreProperties(value = "noteModel")
 * 
 * @JsonIgnore private List<RegistrationModel> registrationModel;
 * 
 * @ManyToMany
 * 
 * @JoinTable(name = "LableJoin", joinColumns = @JoinColumn(name = "noteId"),
 * inverseJoinColumns = @JoinColumn(name = "labelId"))
 * 
 * @JsonIgnoreProperties(value = "noteModel")
 * 
 * @JsonIgnore private List<LabelsModel> labelModel;
 * 
 * @ManyToMany
 * 
 * @JoinTable(name = "colJoin", joinColumns = @JoinColumn(name = "noteId"),
 * inverseJoinColumns = @JoinColumn(name = "colId"))
 * 
 * @JsonIgnoreProperties(value = "noteList")
 * 
 * @JsonIgnore private List<CollaboratorOut> collaboratorOutsList;
 * 
 * public List<CollaboratorOut> getCollaboratorOutsList() { return
 * collaboratorOutsList; }
 * 
 * public void setCollaboratorOutsList(List<CollaboratorOut>
 * collaboratorOutsList) { this.collaboratorOutsList = collaboratorOutsList; }
 * 
 * public LocalDate getTrashDate() { return trashDate; }
 * 
 * public void setTrashDate() { this.trashDate = LocalDate.now().plusDays(30); }
 * 
 * public boolean isNoteReminder() { return noteReminder; }
 * 
 * public void setNoteReminder(boolean noteReminder) { this.noteReminder =
 * noteReminder; }
 * 
 * public LocalDateTime getReminderDatTime() { return reminderDatTime; }
 * 
 * public void setReminderDatTime(LocalDateTime reminderDatTime) {
 * this.reminderDatTime = reminderDatTime; }
 * 
 * public boolean isArchieve() { return archieve; }
 * 
 * public void setArchieve(boolean archieve) { this.archieve = archieve; }
 * 
 * public boolean isArchievePin() { return archievePin; }
 * 
 * public void setArchievePin(boolean archievePin) { this.archievePin =
 * archievePin; }
 * 
 * public boolean isTrash() { return trash; }
 * 
 * public void setTrash(boolean trash) { this.trash = trash; }
 * 
 * public boolean isPinUnpin() { return pinUnpin; }
 * 
 * public void setPinUnpin(boolean pinUnpin) { this.pinUnpin = pinUnpin; }
 * 
 * public List<RegistrationModel> getRegistrationModel() { return
 * registrationModel; }
 * 
 * public void setRegistrationModel(List<RegistrationModel> registrationModel) {
 * this.registrationModel = registrationModel; }
 * 
 * public int getNoteId() { return noteId; }
 * 
 * public void setNoteId(int noteId) { this.noteId = noteId; }
 * 
 * public String getTitle() { return title; }
 * 
 * public void setTitle(String title) { this.title = title; }
 * 
 * public String getContent() { return content; }
 * 
 * public void setContent(String content) { this.content = content; }
 * 
 * public RegistrationModel getModel() { return model; }
 * 
 * public void setModel(RegistrationModel model) { this.model = model; }
 * 
 * public List<LabelsModel> getLabelModel() { return labelModel; }
 * 
 * public void setLabelModel(List<LabelsModel> labelModel) { this.labelModel =
 * labelModel; }
 * 
 * public LocalDateTime getAtCreated() { return atCreated; }
 * 
 * public void setAtCreated() { this.atCreated = LocalDateTime.now(); }
 * 
 * public LocalDateTime getAtModified() { return atModified; }
 * 
 * public void setAtModified() { this.atModified = LocalDateTime.now(); }
 * 
 * @Override public String toString() { return "NoteModel [noteId=" + noteId +
 * ", title=" + title + ", content=" + content + ", atCreated=" + atCreated +
 * ", atModified=" + atModified; } }
 */