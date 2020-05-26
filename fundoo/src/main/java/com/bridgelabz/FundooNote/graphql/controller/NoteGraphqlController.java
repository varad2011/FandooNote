package com.bridgelabz.FundooNote.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bridgelabz.FundooNote.graphql.service.GraphqlService;

import graphql.ExecutionResult;

@RequestMapping("/noteGet")
@Controller
public class NoteGraphqlController {

	@Autowired
	GraphqlService graphqlService;
	
	 @PostMapping
	    public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
	        ExecutionResult execute = graphqlService.getGraphQL().execute(query);

	        return new ResponseEntity<>(execute, HttpStatus.OK);
	    }
}
