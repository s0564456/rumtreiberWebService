package de.htw.ai.wad.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import de.htw.ai.os.bean.User;
import de.htw.ai.os.rest.UserWebService;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserWebServiceTest extends JerseyTest {
			
    @Override
    protected Application configure() {
        return new ResourceConfig(UserWebService.class);
    }  
}
