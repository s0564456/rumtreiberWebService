package de.htw.ai.os.rest;




import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.htw.ai.os.bean.User;
import de.htw.ai.os.storage.UserDao;
import de.htw.ai.os.storage.UserDaoDB;
import de.htw.ai.os.storage.UserDaoInMemory;

// URL fuer diesen Service ist: http://localhost:8080/${project.artifactId}/<url-pattern>/users 
@Path("/users")
public class UserWebService {

    //GET http://localhost:8080/userJAXRS/rest/users
    //Returns all users
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> getAllUsers() {
        System.out.println("getAllUsers: Returning all users!");
        return UserDaoInMemory.getInstance().getAllUsers();
    }

    //GET http://localhost:8080/userJAXRS/rest/users/bob2
    //Returns: 200 and user with userid userId
    //Returns: null on provided id not found
    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userId") String userId) {
        User contact = UserDaoInMemory.getInstance().getUser(userId);
        if (contact != null) {
            System.out.println("getUser: Returning user for id " + userId);
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
         Integer newId = UserDaoInMemory.getInstance().addUser(user);
         return newId.toString();
    }
}