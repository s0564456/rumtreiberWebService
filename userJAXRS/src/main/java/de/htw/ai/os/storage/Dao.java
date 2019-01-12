package de.htw.ai.os.storage;

import java.util.Collection;

import de.htw.ai.os.bean.LocationEntry;
import de.htw.ai.os.bean.User;

public interface Dao {
	
    boolean authenticate(long userId);
    
    boolean updatePositions(int userId, long longnitude, long lattitude);
    
    Collection<LocationEntry> getAllLocationEntries();
    
    long addUser(String user);

	String updatePosition(LocationEntry locationTemplate, long auth);
}
