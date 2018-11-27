package de.htw.ai.wad.rest;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.htw.ai.wad.bean.LocationEntry;
import de.htw.ai.wad.bean.User;
import de.htw.ai.wad.storage.UserDB;

// URL fuer diesen Service ist: http://localhost:8080/${project.artifactId}/<url-pattern>/users 
@Path("/users")
public class RumtreiberWebService {

	//GET http://localhost:8080/userJAXRS/rest/users
	//Returns all location Entries without the the password
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<LocationEntry> getAll() {
		System.out.println("getAll-Request: Returning all contacts!");
		//TODO: db: SELECT *
		
		return new ArrayList<LocationEntry>();
		//return UserDB.getInstance().getAllUsers();
	}

	//Returns: 200 and user with id 1
	//Returns: null on provided id not found
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	public void updatePosition(@PathParam("id") Integer id, @QueryParam("long") double longnitude, @QueryParam("lat") double lattitude) {
		/*User contact = UserDB.getInstance().getUser(id);
		if (contact != null) {
			System.out.println("getUser: Returning contact for id " + id);
			return contact;  
		} else {
			return null; 
		} */
		System.out.println("new Locationsentries");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        //TODO: alten Eintrag zwischenspeichern, danach überschreiben
	}

	// POST http://localhost:8080/userJAXRS/rest/users with user in payload
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public int registrateUser(User user) {
		// generate ID
		// db: CREATE
		return 22399;
//	     Integer newId = UserDB.getInstance().addUser(user);
//	     return newId.toString();
	}
}