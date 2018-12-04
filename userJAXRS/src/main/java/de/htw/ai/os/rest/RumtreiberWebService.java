package de.htw.ai.os.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.htw.ai.os.bean.LocationEntry;
import de.htw.ai.os.bean.User;
import de.htw.ai.os.storage.Dao;
import de.htw.ai.os.storage.TestDB;

// URL fuer diesen Service ist: http://localhost:8080/${project.artifactId}/<url-pattern>
@Path("/db")
public class RumtreiberWebService {
	
	private static final Logger log = Logger.getLogger( RumtreiberWebService.class.getName() );
	private Dao dao = new TestDB();
//	private Dao dao = new DaoDB();

	/**
	 * GET http://localhost:8080/rumtreiber/data
	 * @return all location Entries without the the password
	 */
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<LocationEntry> getAll() {
		System.out.println("getAll-Request: Returning all contacts with positions!");
		log.info("getAll-Request: Returning all contacts with positions!");
		return dao.getAllLocationEntries();
	}

	/**
	 * 
	 * @param id
	 * @param longnitude
	 * @param lattitude
	 */
	//Returns: 200 and user with id 1
	//Returns: null on provided id not found
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	public void updatePosition(@PathParam("id") Integer id, @QueryParam("long") double longnitude, @QueryParam("lat") double lattitude) {
		log.info("updatePosition-Request: Trying to update database!");
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

	/** 
	 * POST http://localhost:8080/rumtreiber/data with user in payload
	 * @param user
	 * @return new generated id
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public int registrateUser(User user) {
		log.info("registrateUser-Request: Trying to supplement database!");
		return dao.addUser(user);
	}
}