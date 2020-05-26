/*
 * package com.bridgelabz.FundooNote.elasticSearch;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.bridgelabz.FundooNote.response.Response;
 * 
 * @RestController
 * 
 * @CrossOrigin
 * 
 * @RequestMapping("/elasticSearch") public class ElasticSearchController {
 * 
 * @Autowired NoteElasticSearchService noteElasticSearchService;
 * 
 * @RequestMapping(method = RequestMethod.GET, value = "/getListOfnoteBySearch")
 * public ResponseEntity<Response> displayNoteListBySearchKey(@RequestParam
 * String token,
 * 
 * @RequestParam String typeText) { Response response =
 * noteElasticSearchService.getListOfNoteBySearchKey(token, typeText); return
 * new ResponseEntity<Response>(response, HttpStatus.OK); } }
 */