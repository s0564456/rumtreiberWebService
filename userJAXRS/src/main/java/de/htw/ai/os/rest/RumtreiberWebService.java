package de.htw.ai.os.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.FORBIDDEN;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.util.JSONPObject;

import de.htw.ai.os.bean.LocationEntry;
import de.htw.ai.os.storage.Dao;
import de.htw.ai.os.storage.DaoDB;
import de.htw.ai.os.storage.TestDB;


@Path("/tunichtgut")
public class RumtreiberWebService {
	
	static private final String QUERY_USER = "select u.id from LocationEntry u where " 
			+ "((:userId is null) or (u.userId = :userId))";
	private Dao dao = new DaoDB();
	private Dao testDao = new TestDB();
	
	// zum Testen , aus der fertigen Version rausnehmen
	@GET
	@Path("hi")
	// wie folgt addressiert:  http://localhost bzw rumtreiber.f4.htw-berlin.de:8080/rumtreiber/data/tunichtgut/hi
	@Produces(APPLICATION_JSON)
	public Collection<LocationEntry> getirgendwas() {
		System.out.println("Method entered " );
		Collection<LocationEntry> res = dao.getAllLocationEntries();
		System.out.println("getUser: Returning user harry " );
		return res;
	}
	
	@GET
	@Path("users/{id}")
	@Produces(APPLICATION_JSON)
	public long queryUserId (
			@QueryParam("id") String userId
	) {
		LocationEntry user = testDao.authenticate(userId);
		if(user == null) {
			System.out.println("User has NOT been authenticated");
			return 0;
		}else {
			System.out.println("User has been authenticated");
			return 1;
		}
	}
	
	/*For later iteration
	@DELETE
	@Path("users/{id}")
	public void deleteUser (
			@PathParam("id")  final long entityIdentity
	) {

		System.out.println("delete entry, entered id: " + entityIdentity);
	}*/
	
	@GET
	@Path("users")
	@Produces(APPLICATION_JSON)
	public Collection<LocationEntry> returnUser () {
		Collection<LocationEntry> res = testDao.getAllLocationEntries();
		return res;	
	}
	
	@POST
	@Path("location")
	@Consumes(APPLICATION_JSON)
	@Produces(TEXT_PLAIN)
	public long updateLocation (
			@NotNull @Valid LocationEntry locationTemplate
	) {
		String result = testDao.updatePosition(locationTemplate);
		System.out.println("update location: " + result);
		return 1;
	}
	
	/*For later iteration
	@POST
	@Path("users")
	@Consumes(APPLICATION_JSON)
	@Produces(TEXT_PLAIN)
	public long createOrModifyUser (
			@NotNull @Valid LocationEntry personTemplate,
			@Context HttpHeaders headers,
			@HeaderParam("Set-Password") final String password
	) {
		
		
		System.out.println("create or modify user");
		return 1;
	}*/
}