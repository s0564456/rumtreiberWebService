package de.htw.ai.os.storage;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import de.htw.ai.os.bean.User;

public class UserDaoInMemory implements UserDao{

	private static Map<String,User> storage;
	private static UserDaoInMemory instance = null;
	
	private UserDaoInMemory() {
		storage = new ConcurrentHashMap<String,User>();
		initUserDB();
	}
	
	public synchronized static UserDaoInMemory getInstance() {
		if (instance == null) {
			instance = new UserDaoInMemory();
		}
		return instance;
	}
	
	private static void initUserDB() {
		User robert = new User(1, "robert1", "password123", "Robert", "S", "admin");
		
		User bob = new User(2,"bob2", "password123", "Bobby", "S", "normalo");
		
		storage.put(robert.getUserId(), robert);
		
		storage.put(bob.getUserId(), bob);
	}
	
	@Override
	public User authenticate(String userId, String password) {
	    User user = storage.get(userId);
	    
	    if (user == null) {
	        User userNotAuthenticated = new User();
	        userNotAuthenticated.setAuthenticated(false);
	        return userNotAuthenticated;
	    }
	    
	    if (user.getPassword().equals(password)) {
	        user.setAuthenticated(true);
	        return user;
	    } else {
	        user.setAuthenticated(false);
	        return user;
	    }
	}
	
	@Override
	public User getUser(String userId) {
        return storage.get(userId);
    }
    
	@Override
    public Collection<User> getAllUsers() {
        return storage.values();
    }
    
    @Override
    public Integer addUser(User user) {
        user.setId((int)storage.keySet().stream().count() + 1);
        storage.put(user.getUserId(), user);
        return user.getId();
    }
}
