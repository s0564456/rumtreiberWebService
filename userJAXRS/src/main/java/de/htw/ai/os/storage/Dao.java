package de.htw.ai.os.storage;

import java.util.Collection;

import de.htw.ai.os.bean.LocationEntry;
import de.htw.ai.os.bean.User;

public interface Dao {
	
    User authenticate(String userId, String password);
    
    boolean updatePositions(int userId, long longnitude, long lattitude);
    
    Collection<LocationEntry> getAllLocationEntries();
    
    Integer addUser(User user);
}
