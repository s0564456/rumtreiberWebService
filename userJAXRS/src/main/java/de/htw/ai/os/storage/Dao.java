package de.htw.ai.os.storage;

import java.util.Collection;

import de.htw.ai.os.bean.LocationEntry;
import de.htw.ai.os.bean.User;

public interface Dao {
	
    LocationEntry authenticate(String userId);
    
    boolean updatePositions(int userId, long longnitude, long lattitude);
    
    Collection<LocationEntry> getAllLocationEntries();
    
    Integer addUser(User user);

	String updatePosition(LocationEntry locationTemplate);
}
