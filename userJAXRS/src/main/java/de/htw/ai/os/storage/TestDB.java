package de.htw.ai.os.storage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import de.htw.ai.os.bean.LocationEntry;
import de.htw.ai.os.bean.User;

public class TestDB implements Dao{

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
		Collection<LocationEntry> res = new ArrayList<LocationEntry>();
		res.add(new LocationEntry("Stephan", new Timestamp(System.currentTimeMillis() )));
		res.add(new LocationEntry("Helene", new Timestamp(22299393 )));
		return res;
	}

	@Override
	public Integer addUser(User user) {
		// TODO Automatisch generierter Methodenstub
		return null;
	}

}
