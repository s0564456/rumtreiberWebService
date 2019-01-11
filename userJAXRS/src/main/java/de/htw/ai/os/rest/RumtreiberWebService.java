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

import de.htw.ai.os.bean.LocationEntry;
import de.htw.ai.os.storage.Dao;
import de.htw.ai.os.storage.DaoDB;


@Path("/tunichtgut")
//@ApplicationPath("")
public class RumtreiberWebService {
	
	static private final String QUERY_USER = "select u.id from LocationEntry u where " 
			+ "((:userId is null) or (u.userId = :userId))";
	private Dao dao = new DaoDB();
	
	// zum Testen , aus der fertigen Version rausnehmen
	@GET
	@Path("hi")
	// wie folgt addressiert:  http://localhost bzw rumtreiber.f4...:8080/rumtreiber/data/tunichtgut/hi
	@Produces(APPLICATION_JSON)
	public Collection<LocationEntry> getirgendwas() {
		Collection<LocationEntry> res = dao.getAllLocationEntries();
		System.out.println("getUser: Returning user harry " );
		return res;
	}
	
	@GET
	@Path("users/{id}")
	@Produces(APPLICATION_JSON)
	public LocationEntry queryUserId (
			@PathParam("id") final long personIdentity
	) {
		/*
		final EntityManager radioManager = RestJpaLifecycleProvider.entityManager("rumtreiber");
		final long identity = personIdentity == 0 ? requesterIdentity : personIdentity;
		final LocationEntry user = radioManager.find(LocationEntry.class, identity);
		if (user == null)
			throw new ClientErrorException(Status.NOT_FOUND);*/
		System.out.println("getUser: Returning user for id " + personIdentity);
		return new LocationEntry( "Harry", new Timestamp(System.currentTimeMillis()));	
	}
	
	@DELETE
	@Path("users/{id}")
	public void deleteUser (
			@PathParam("id")  final long entityIdentity
	) {
		/*
		final EntityManager radioManager = RestJpaLifecycleProvider.entityManager("rumtreiber");
		final LocationEntry requester = radioManager.find(LocationEntry.class, requesterIdentity);
		
		final LocationEntry entity = radioManager.find(LocationEntry.class, entityIdentity);
		if (entity == null)
			throw new ClientErrorException(NOT_FOUND);
		if (requester == null || !requester.equals(entity))
			throw new ClientErrorException(FORBIDDEN);
		
		radioManager.remove(entity);

		try {
			radioManager.getTransaction().commit();
		} catch (final RollbackException exception) {
			throw new ClientErrorException(CONFLICT);
		} finally {
			radioManager.getTransaction().begin();
		}

		radioManager.getEntityManagerFactory().getCache().evict(LocationEntry.class, entityIdentity);
		*/
		System.out.println("delete entry, entered id: " + entityIdentity);
	}
	
	@GET
	@Path("users")
	@Produces(APPLICATION_JSON)
	public List<LocationEntry> returnPeople (
			@Context HttpHeaders headers,
			@QueryParam("userId") String userId
	) {
		/*
		final EntityManager radioManager = RestJpaLifecycleProvider.entityManager("rumtreiber");
		headers.getRequestHeaders();
		final TypedQuery<Long> query = radioManager.createQuery(QUERY_USER, Long.class);		
		query
			.setParameter("userId", userId);

		final List<Long> personReferences = query.getResultList();
		final List<LocationEntry> user = new ArrayList<>();
		for (final long id : personReferences) {
			final LocationEntry person = radioManager.find(LocationEntry.class, id);
			if (person != null)
				user.add(person);
		}
		user.sort(Comparator.comparing(LocationEntry::getUserId));*/
		System.out.println("return all user");
		final List<LocationEntry> user = new ArrayList<LocationEntry>();
		user.add(new LocationEntry( "Harry", new Timestamp(System.currentTimeMillis())));
		user.add(new LocationEntry( "Schniefelus", new Timestamp(System.currentTimeMillis())));
		return user;	
	}
	
	@POST
	@Path("users")
	@Consumes(APPLICATION_JSON)
	@Produces(TEXT_PLAIN)
	public long createOrModifyUser (
			@NotNull @Valid LocationEntry personTemplate,
			@Context HttpHeaders headers,
			@HeaderParam("Set-Password") final String password
	) {
		/*
		final EntityManager radioManager = RestJpaLifecycleProvider.entityManager("rumtreiber");
		
		Would need authentication method to update or create user in the future
		if (requesterIdentity != personTemplate.getId()) 
			throw new ClientErrorException(FORBIDDEN);
		 
		
		headers.toString();
		final boolean insert = personTemplate.getId() == 0;

		final LocationEntry person;

		if (insert) { // neue Person erstellen
			person = new LocationEntry();
		} else { // Person, die bereits in DB existiert updaten
			person = radioManager.find(LocationEntry.class, personTemplate.getId()); 
			if (person == null)
				throw new ClientErrorException(Status.NOT_FOUND);
		}

		person.setUserId(personTemplate.getUserId());

		if (password != null)
			person.setPasswordHash(HashTools.sha256HashCode(password));

		if (insert) {
			radioManager.persist(person);
		} else {
			radioManager.flush();
		}

		try {
			radioManager.getTransaction().commit();
		} catch (PersistenceException error) {
			throw new ClientErrorException(Status.CONFLICT);
		} finally {
			radioManager.getTransaction().begin();
		}*/
		
		System.out.println("create or modify user");
		return 1;
	}
	
	@POST
	@Path("location")
	@Consumes(APPLICATION_JSON)
	@Produces(TEXT_PLAIN)
	public long updateLocation (
			@NotNull @Valid LocationEntry personTemplate,
			@Context HttpHeaders headers
	) {
		/*
		final EntityManager radioManager = RestJpaLifecycleProvider.entityManager("rumtreiber");

		if (requesterIdentity != personTemplate.getId()) 
			throw new ClientErrorException(FORBIDDEN);

		headers.toString();

		final LocationEntry person = radioManager.find(LocationEntry.class, personTemplate.getId()); 
		
		if (person == null)
			throw new ClientErrorException(Status.NOT_FOUND);
		
		//current last data will now be set to second last data
		person.setSecondlast_position(person.getLast_position());
		person.setSecondlastTimestamp(person.getLastTimestamp());
		person.setSecondlast_direction(person.getLast_direction());
		
		//new data will be inserted
		person.setLast_position(personTemplate.getLast_position());
		person.setLastTimestamp(personTemplate.getLastTimestamp());
		person.setLast_direction(personTemplate.getLast_direction());

		radioManager.flush();

		try {
			radioManager.getTransaction().commit();
		} catch (PersistenceException error) {
			throw new ClientErrorException(Status.CONFLICT);
		} finally {
			radioManager.getTransaction().begin();
		}*/
		
		System.out.println("update location");
		return 1;
	}
}