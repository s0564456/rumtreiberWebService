package de.htw.ai.os.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import de.htw.ai.os.bean.LocationEntry;
import de.htw.ai.os.bean.User;

public class DaoDB implements Dao {
	private  DataSource ds;
	private static String SELECT_ALL = "select * from rumtreiber";
	private final Logger logger = Logger.getLogger( DaoDB.class.getName() );
	
	public DaoDB () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/rumtreiberDB");
	            System.out.println("Data Source found. ");
	            logger.log(Level.SEVERE, "DaoDB gesucht und gefunden.");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            logger.log(Level.SEVERE, "DaoDB gesucht und NICHT gefunden.");
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
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<LocationEntry> result = new ArrayList<>();
		LocationEntry e1 =  new LocationEntry();
		LocationEntry e2 =  new LocationEntry();
		
		e1.setName("dummy");
		result.add(e1);

		try {
			con = ds.getConnection();
			logger.log(Level.SEVERE, "DaoDB Connection erfolgt.");
			
			stmt = con.prepareStatement(SELECT_ALL);
			//stmt.setString(1, userId);
			rs = stmt.executeQuery();
			logger.log(Level.SEVERE, "Query ausgeführt.");
			System.out.println("vor while Schleife");
			while (rs.next()) {
				e2.setName(rs.getString(1));
				logger.log(Level.SEVERE, "String gefunden.");
				System.out.println("Erhalten: \t"+rs.getString(1));
				logger.log(Level.INFO, "Bin in while!");
				/*
				user = new User();
				user.setId(rs.getInt(1));
				user.setUserId(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setRole(rs.getString(6));
				user.setAuthenticated(false); */
			}
			result.add(e2);
			
			/*
			if (password.equals(user.getPassword())) {
				user.setAuthenticated(true);
				return user;
			} */

		} catch (SQLException e) {
			System.out.println("SQLException getting user");
			e.printStackTrace();	
			logger.log(Level.SEVERE, "SQL Exception geworfen!" + e.getErrorCode() + " " + e.getSQLState() + "\n" + e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Exception in closing DB resources");
				
			} 
		}
		
		
		return result;
	}

	@Override
	public Integer addUser(User user) {
		// TODO Automatisch generierter Methodenstub
		return null;
	}

}
