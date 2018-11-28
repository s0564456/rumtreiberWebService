package de.htw.ai.os.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import de.htw.ai.os.bean.User;

public class UserDaoDB implements UserDao {

    private static String SELECT_USER_WITH_USERID = "select * from user where user_id = ?";

    private  DataSource ds;
    
    public UserDaoDB () {      
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

        User user = new User();
        user.setUserId(userId);
        user.setAuthenticated(false);
        
        if (userId == null || userId.isEmpty() || password == null || password.isEmpty()) {
            return user;
        }

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(SELECT_USER_WITH_USERID);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setUserId(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setRole(rs.getString(6));
                user.setAuthenticated(false);
            }
            
            if (password.equals(user.getPassword())) {
                user.setAuthenticated(true);
                return user;
            }
            return user;
        } catch (SQLException e) {
            System.out.println("SQLException getting user");
            e.printStackTrace();
            return user;
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

    @Override
    public User getUser(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<User> getAllUsers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer addUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }
}
