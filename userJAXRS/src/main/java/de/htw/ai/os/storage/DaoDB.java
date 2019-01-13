package de.htw.ai.os.storage;

import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.postgresql.util.PGobject;

import com.fasterxml.jackson.databind.util.JSONPObject;

import de.htw.ai.os.bean.LocationEntry;
import de.htw.ai.os.bean.User;


/*
 * Datenbanktabelle Spalten:
 * integer id
 * charvar name
 * charvar password
 * timestamp without time zon last_timestemp
 * geometry last_position
 * secondlast_timestemp
 * second_last_position
 * double precision last_direction
 * second_ast direction
 * 
 */

public class DaoDB implements Dao {
	private  DataSource ds;
	private static String SELECT_ALL_LOCATIONENTRIES = "select * from ws18_19.rumtreiber";
	
	public DaoDB () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/rumtreiberDB");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            e.printStackTrace();
	        } 
	}
	 
	@Override
	public boolean authenticate(long userId) {
		// TODO Automatisch generierter Methodenstub
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<LocationEntry> list = new ArrayList<LocationEntry>();
		LocationEntry entry = null;
		String searchQuery = SELECT_ALL_LOCATIONENTRIES + " where id='" + userId + "'";
		
		try {
		    con = ds.getConnection();
		    stmt = con.prepareStatement(searchQuery);
		    //stmt.setString(1, userId);
		    rs = stmt.executeQuery();
		    
		    boolean more = rs.next(); 
		    if (more) { 
		    	return true; 
	    	}  
		} catch (SQLException e) {
		    System.out.println("SQLException getting user");
		    e.printStackTrace();
		} finally {
		    try {
		        if (rs != null) rs.close();
		        if (stmt != null) stmt.close();
		        if (con != null) con.close();
		    } catch (Exception e) {
		        System.out.println("Exception in closing DB resources");
		    } 
		}
		return false;
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
		Collection<LocationEntry> list = new ArrayList<LocationEntry>();
		LocationEntry entry = null;
		
		try {
		    con = ds.getConnection();
		    stmt = con.prepareStatement(SELECT_ALL_LOCATIONENTRIES);
		    //stmt.setString(1, userId);
		    rs = stmt.executeQuery();
		    while (rs.next()) {
		    	entry = new LocationEntry();
		    	entry.setId(rs.getInt(1));
		    	entry.setUserId(rs.getString(2));
		    	entry.setLastTimestamp(rs.getTimestamp(3));
		    	entry.setLast_position(((Point) rs.getObject(4))); 
		    	list.add(entry);
		    }
		    
		    return list;
		} catch (SQLException e) {
		    System.out.println("SQLException getting user");
		    e.printStackTrace();
		    return list;
		} finally {
		    try {
		        if (rs != null) rs.close();
		        if (stmt != null) stmt.close();
		        if (con != null) con.close();
		    } catch (Exception e) {
		        System.out.println("Exception in closing DB resources");
		    } 
		}
	}
	
	public String updatePosition(LocationEntry locationTemplate, long auth) {
		Connection con = null;
        PreparedStatement stmt = null;
        int result;

        try {
            con = ds.getConnection();
            stmt = con.prepareStatement("UPDATE LocationEntry "
            		+ "SET last_timestemp = ?,  secondlast_timestemp = ?, last_position = ?, secondlast_position = ?, last_direction = ?, secondlast_direction = ?"
            		+ "WHERE userId = ?");
            stmt.setTimestamp(1, locationTemplate.getLastTimestamp());
            stmt.setTimestamp(2, locationTemplate.getSecondlastTimestamp());
            stmt.setString(3, locationTemplate.getLast_position().toString());
            stmt.setString(4, locationTemplate.getSecondlast_position().toString());
            stmt.setFloat(5, locationTemplate.getLast_direction());
            stmt.setFloat(6, locationTemplate.getSecondlast_direction());
            stmt.setString(7, locationTemplate.getUserId());
            result = stmt.executeUpdate();
            return "INSERTION SUCCEDED";
        } catch (SQLException e) {
            System.out.println("SQLException updating location");
            e.printStackTrace();
            return "INSERTION FAILED";
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Exception in closing DB resources");
            } 
        }
		
	}

	@Override
	public long addUser(String user) {
		// TODO Automatisch generierter Methodenstub
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        //insert new user
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement("INSERT INTO LocationEntry (id)" + 
            		"VALUES ('" + user + " '); ");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException updating location");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Exception in closing DB resources");
            } 
        }
        
        //return user id
        try {
            stmt = con.prepareStatement(SELECT_ALL_LOCATIONENTRIES + " where name='" + user + "'");
            rs = stmt.executeQuery();
            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("SQLException updating location");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Exception in closing DB resources");
            } 
        }
        
		return 0;
	}

}
