package de.htw.ai.wad.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import de.htw.ai.wad.bean.User;
import de.htw.ai.wad.rest.UserWebService;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserWebServiceTest extends JerseyTest {
			
    @Override
    protected Application configure() {
        return new ResourceConfig(UserWebService.class);
    }
    
    @Test
    public void getUserWithValidIdShouldReturnUser() {
        User contact = target("/users/1")
        		.request(MediaType.APPLICATION_JSON)
        		.get(User.class);
        System.out.println(contact);
        Assert.assertEquals(1, contact.getId().intValue());
    }
    
    @Test
    public void getUserWithNonExistingIdShouldReturn204() {
        Response response = target("/users/22").request().get();
        Assert.assertEquals(204, response.getStatus());
    }
    
    @Test
    public void getUserWithStringIdShouldReturn404() {
        Response response = target("/users/ksksksk").request().get();
        Assert.assertEquals(404, response.getStatus());
    }
    
    @Test
    public void createUserShouldReturn201AndID() {
    		User bob = new User();
    		bob.setUserId("bobby3");
    		bob.setFirstName("Bobby");
    		bob.setLastName("MUELLER");
    		bob.setRole("user");			
        Response response = target("/users").request().post(Entity.json(bob));
        Assert.assertEquals("3", response.readEntity(String.class));
    }
}
