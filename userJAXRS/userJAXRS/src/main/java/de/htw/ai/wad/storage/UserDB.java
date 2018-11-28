package de.htw.ai.wad.storage;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import de.htw.ai.wad.bean.User;

public class UserDB {

	private static Map<Integer,User> storage;
	private static UserDB instance = null;
	
	private UserDB() {
		storage = new ConcurrentHashMap<Integer,User>();
		initUserDB();
	}
	
	public synchronized static UserDB getInstance() {
		if (instance == null) {
			instance = new UserDB();
		}
		return instance;
	}
	
	private static void initUserDB() {
		User robert = new User(1, "robert1", "secret", "Robert", "S", "admin");
		
		User bob = new User(2,"bob2", "password123", "Bobby", "S", "user");
		
		storage.put(robert.getId(), robert);
		
		storage.put(bob.getId(), bob);
	}
	
	public User getUser(Integer id) {
		return storage.get(id);
	}
	
	public Collection<User> getAllUsers() {
		return storage.values();
	}
	
	public Integer addUser(User user) {
		user.setId((int)storage.keySet().stream().count() + 1);
		storage.put(user.getId(), user);
		return user.getId();
	}
	
	public boolean updateContact(User contact) {
		throw new UnsupportedOperationException("updateContact: not yet implemented");
	}
	
	public User deleteContact(Integer id) {
		throw new UnsupportedOperationException("deleteContact: not yet implemented");
	}
}
