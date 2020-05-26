/*
 * package com.bridgelabz.FundooNote.elasticSearch;
 * 
 * import java.util.Collection; import java.util.List; import
 * java.util.Optional; import java.util.stream.Collectors;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.bridgelabz.FundooNote.Util.TokenGeneratorDecoder; import
 * com.bridgelabz.FundooNote.model.Note; import
 * com.bridgelabz.FundooNote.model.RegistrationModel; import
 * com.bridgelabz.FundooNote.repository.CollaboratorRepository; import
 * com.bridgelabz.FundooNote.repository.RegistrationPageRepository; import
 * com.bridgelabz.FundooNote.response.RecordNotFoundException; import
 * com.bridgelabz.FundooNote.response.Response;
 * 
 * @Service public class NoteElasticSearchService {
 * 
 * @Autowired NoteElasticSearchRepository noteElasticSearchRepository;
 * 
 * @Autowired private TokenGeneratorDecoder tokenDecoder;
 * 
 * @Autowired private RegistrationPageRepository registrationPagerepository;
 * 
 * public Response getListOfNoteBySearchKey(String token, String typeText) {
 * long id = Long.parseLong(tokenDecoder.decodeToken(token));
 * Optional<RegistrationModel> user = registrationPagerepository.findById(id);
 * if (user.isPresent()) { Iterable<NoteElastic> allNoteList =
 * noteElasticSearchRepository.findAll(); return new Response(200,
 * "display Serach Note successfully", allNoteList); } throw new
 * RecordNotFoundException("User not present "); } }
 */