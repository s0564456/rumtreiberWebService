package de.htw.ai.os.storage;

import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import de.htw.ai.os.bean.LocationEntry;
import de.htw.ai.os.bean.User;

public class DaoDB implements Dao {
	private  DataSource ds;
	private static String SELECT_USER_WITH_USERID = "select * from user where user_id = ?";
	
	public DaoDB () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/adviDB");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            e.printStackTrace();
	        } 
	}
	 
	@Override
	public User authenticate(String userId, String password) {
		// TODO Automatisch generierter Methodenstub
		return null;
	}

	@Override
	public boolean updatePositions(int userId, long longnitude, long lattitude) {
		// TODO Automatisch generierter Methodenstub
		return false;
	}

	@Override
	public Collection<LocationEntry> getAllLocationEntries() {
		return null;
	}

	@Override
	public Integer addUser(User user) {
		// TODO Automatisch generierter Methodenstub
		return null;
	}

}
