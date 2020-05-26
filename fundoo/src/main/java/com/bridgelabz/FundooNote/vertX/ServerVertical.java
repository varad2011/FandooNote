package com.bridgelabz.FundooNote.vertX;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.bridgelabz.FundooNote.model.Note;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.repository.NoteRepository;
import com.bridgelabz.FundooNote.repository.RegistrationPageRepository;
import com.bridgelabz.FundooNote.response.Response;
import com.bridgelabz.FundooNote.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

@Component
public class ServerVertical extends AbstractVerticle {

	@Autowired
	NoteService noteService;

	@Autowired
	NoteRepository noteRepsitory;

	@Autowired
	vertxNoteRepository repo;

	@Autowired
	private RegistrationPageRepository registrationPagerepository;

	@Override
	public void start(Future<Void> fut) throws Exception {
		Vertx vertx = Vertx.factory.vertx();
		Router router = Router.router(vertx);
		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setChunked(true);
			response.putHeader("content-type", "text/html").end("<h1>Hello from my first Vert.x 3 application</h1>");
		});

		router.post("/getUser").handler(this::createUser);
		router.get("/getNotes").handler(this::getNotes);
		router.get("/getAll").handler(this::getAll);
		vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port", 8081),
				result -> {
					if (result.succeeded()) {
						fut.complete();
					} else {
						fut.fail("it failed");
					}
				});
	}

	private void getNotes(RoutingContext routingContext) {
		JsonObject json = new JsonObject();
		Optional<RegistrationModel> user = registrationPagerepository.findById((long) 1);
		RegistrationModel model = new RegistrationModel();
		model.setEmailId("1233");
		List<Note> noteAll = new ArrayList<Note>();
		Note notemodel = new Note();
		Note notemodel1 = new Note();
		notemodel.setContent("www");
		notemodel1.setContent("123");
		noteAll.add(notemodel);
		noteAll.add(notemodel1);
		List<Note> noteModel = noteRepsitory.findAll();
		List<Note> listOfIsTrash = noteModel.stream().filter(i -> i.isArchieve()).collect(Collectors.toList());
		System.out.println(listOfIsTrash);
		System.out.println(noteAll);
		json.put("user", user.get().getMobileNumber());

		List<Note> noteList = noteRepsitory.findAll();
		List<Note> noteModel1 = noteList.stream().filter(t -> (t.getModel().getId()) == ((long) 1))
				.collect(Collectors.toList());
		List<Note> noteListWithoutPin = noteModel1.stream().filter(i -> i.isPinUnpin() == false)
				.collect(Collectors.toList());
		List<Note> noteListWithoutTrashNote = noteListWithoutPin.stream().filter(i -> i.isTrash() == false)
				.collect(Collectors.toList());
		List<Note> noteListWithoutArchiveNotes = noteListWithoutTrashNote.stream().filter(i -> i.isArchieve() == false)
				.collect(Collectors.toList());

		HttpServerResponse res = routingContext.response();
		res.setStatusCode(201).putHeader("content-type", "application/jsons").end(Json.encodePrettily(noteModel));
//	res.setStatusCode(200).putHeader("content-type", "application/json; charset=utf-8").end(json.encodePrettily());
	}

	private void getAll(RoutingContext routingContext) {
		List<Note> noteModel = (List<Note>) repo.findAll();
		routingContext.response().putHeader("content-type", "application/json; charset=utf-8")
				.end(Json.encodePrettily(noteModel));
	}

	private void createUser(RoutingContext routingContext) {
		RequestParam token = routingContext.get("token");
		String tokenString = token.toString();
		Response response = noteService.AllReminderPinNoteList(tokenString);
		Optional<RegistrationModel> user = registrationPagerepository.findById((long) 1);
		// dao.createUser(user);
		HttpServerResponse res = routingContext.response();
		res.setChunked(true);
		res.setStatusCode(200).putHeader("content-type", "text/html").end(Json.encodePrettily(user.get()));
	}

//	void addUser(RoutingContext routingContext) {
//
//		  // Get the parsed parameters
//		  RequestParameters params = routingContext.get("parsedParameters");
//
//		  // We get an user JSON object validated by Vert.x Open API validator
//		  JsonObject user = params.body().getJsonObject();
//
//		  // Generate a user id
//		  String userId = "" + users.size();
//
//		  // Add the user to the users list
//		  users.add(user);
//
//		  // Send the user id as JSON response
//		  routingContext
//		     .response()
//		     .setStatusCode(200)
//		     .end();
//	}
}
