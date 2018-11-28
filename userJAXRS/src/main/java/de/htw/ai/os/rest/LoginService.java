package de.htw.ai.os.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.htw.ai.os.bean.User;
import de.htw.ai.os.storage.UserDao;
import de.htw.ai.os.storage.UserDaoDB;
import de.htw.ai.os.storage.UserDaoInMemory;

/* 
 * URL fuer diesen Service ist: 
 * http://localhost:8080/${project.artifactId}/<url-pattern>/login?userId=paule&passwd=geheim
 */
@Path("/login")
public class LoginService {
    
    //private UserDao userDao = new UserDaoDB();
    private UserDao userDao = UserDaoInMemory.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User login(@QueryParam("userId") String userId, @QueryParam("passwd") String password) {

        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setAuthenticated(false);

        if (userId == null || userId.trim().isEmpty()) {
            System.out.println("No userid provided");
            return user;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("No password provided");
            return user;
        }

        System.out.println("Authenticating user");
        user = userDao.authenticate(userId, password);
        return user;
    }
}
