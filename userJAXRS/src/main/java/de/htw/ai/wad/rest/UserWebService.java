package de.htw.ai.wad.rest;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.htw.ai.wad.bean.User;
import de.htw.ai.wad.storage.UserDB;

// URL fuer diesen Service ist: http://localhost:8080/${project.artifactId}/<url-pattern>/users 
@Path("/users")
public class UserWebService {

	//GET http://localhost:8080/userJAXRS/rest/users
	//Returns all users
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getAllUsers() {
		System.out.println("getAllContacts: Returning all contacts!");
		return UserDB.getInstance().getAllUsers();
	}

	//GET http://localhost:8080/userJAXRS/rest/users/1
	//Returns: 200 and user with id 1
	//Returns: null on provided id not found
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") Integer id) {
		User contact = UserDB.getInstance().getUser(id);
		if (contact != null) {
			System.out.println("getUser: Returning contact for id " + id);
			return contact;  
		} else {
			return null; 
		}
	}

	// POST http://localhost:8080/userJAXRS/rest/users with user in payload
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String createUser(User user) {
	     Integer newId = UserDB.getInstance().addUser(user);
	     return newId.toString();
	}
}