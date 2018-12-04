package de.htw.ai.os.rest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import de.htw.ai.os.storage.Dao;
import de.htw.ai.os.storage.DaoDB;

@WebListener
public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // Perform action during application's startup
    	System.out.println("Deutschland Startup of Application ....");
    	Dao d = new DaoDB();
    	d.getAllLocationEntries();
    	System.out.println("Now my test should be done.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Perform action during application's shutdown
    }
}