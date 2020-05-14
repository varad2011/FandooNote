package com.bridgelabz.FundooNote.vertX;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.FundooNote.model.Note;
import com.bridgelabz.FundooNote.repository.NoteRepository;
import com.bridgelabz.FundooNote.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

@Component
public class ServerVertical  extends AbstractVerticle {

	@Autowired
	NoteService noteService;
	@Autowired
	NoteRepository  noteRepsitory;
	
	@Override
	public void start(Future<Void> fut) throws Exception {
	Vertx vertx = Vertx.factory.vertx();
	Router router = Router.router(vertx);
	router.post("/getUser").handler(this::createUser);
	router.get("/createUser").handler(this::getUser);
	vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port", 8081),
	result -> {
	if (result.succeeded()) {
	fut.complete();
	} else {
	fut.fail("it failed");
	}
	});
	}
	private void getUser(RoutingContext routingContext) {
	
		List<Note> noteModel = noteRepsitory.findAll();
//	User user = new User();
//	user.setGender("Male");
//	user.setName("shashi raj");
	HttpServerResponse res = routingContext.response();
	res.setStatusCode(200).putHeader("content-type", "application/json").end(noteModel.toString());
	}
	
	private void createUser(RoutingContext routingContext) {
		
//	 ObjectMapper mapper = new ObjectMapper();
	// User user = mapper.convertValue(routingContext.getBodyAsJson(),
	// User.class);
	// dao.createUser(user);
	HttpServerResponse res = routingContext.response();
	res.setStatusCode(200).putHeader("content-type", "application/json").end();
	}
}
