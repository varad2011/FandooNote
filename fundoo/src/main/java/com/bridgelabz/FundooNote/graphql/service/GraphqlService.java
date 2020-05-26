package com.bridgelabz.FundooNote.graphql.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooNote.graphql.service.datafetcher.GetAllNoteDataFetcher;
import com.bridgelabz.FundooNote.graphql.service.datafetcher.GetNoteDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphqlService {

	@Value("classpath:note.graphql")
	Resource resource;
	
	@Autowired
	GetAllNoteDataFetcher getAllNoteDataFetcher;
	
//	@Autowired
//	GetNoteDataFetcher getNoteDataFetcher;
	
	private GraphQL graphQL;
	
	@PostConstruct
	private void loadSchema () throws IOException {
		  File schemaFile = resource.getFile();
		  TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		  RuntimeWiring wiring = buildRuntimeWiring();
		  GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
	        graphQL = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildRuntimeWiring() {
		 return RuntimeWiring.newRuntimeWiring()
	                .type("Query", typeWiring -> typeWiring
	                        .dataFetcher("getAllNote", getAllNoteDataFetcher))
	                .build();
	}
//	.dataFetcher("getNoteById", getNoteDataFetcher))

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
