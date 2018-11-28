package de.htw.ai.os.storage;

import java.util.Collection;

import de.htw.ai.os.bean.User;

public interface UserDao {

    User authenticate(String userId, String password);
    
    User getUser(String userId);
    
    Collection<User> getAllUsers();
    
    Integer addUser(User user);
}
