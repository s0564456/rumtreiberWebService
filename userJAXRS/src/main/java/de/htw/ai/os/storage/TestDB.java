package de.htw.ai.os.storage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import de.htw.ai.os.bean.LocationEntry;
import de.htw.ai.os.bean.User;

public class TestDB implements Dao{
	
	private Collection<LocationEntry> res = new ArrayList<LocationEntry>();
	
	public TestDB() {
		res.add(new LocationEntry("Harry", new Timestamp(System.currentTimeMillis() )));
		res.add(new LocationEntry("Snape", new Timestamp(22299393 )));
	}

	@Override
	public LocationEntry authenticate(String userId) {
		for (LocationEntry locationEntry : res) {
			String name = locationEntry.getUserId();
			if (name.equals(userId)) {
				return locationEntry;
			}
		}
		return null;
	}

	@Override
	public boolean updatePositions(int userId, long longnitude, long lattitude) {
		// TODO Automatisch generierter Methodenstub
		return false;
	}

	@Override
	public Collection<LocationEntry> getAllLocationEntries() {

		return res;
	}

	@Override
	public Integer addUser(User user) {
		// TODO Automatisch generierter Methodenstub
		return null;
	}

	@Override
	public String updatePosition(LocationEntry locationTemplate) {
		for (LocationEntry locationEntry : res) {
			if (locationEntry.getUserId().equals(locationTemplate.getUserId())) {
				locationEntry.setLast_direction(locationTemplate.getLast_direction());
				locationEntry.setSecondlast_direction(locationTemplate.getSecondlast_direction());
				
				locationEntry.setLast_position(locationTemplate.getLast_position());
				locationEntry.setSecondlast_position(locationTemplate.getSecondlast_position());
				
				locationEntry.setLastTimestamp(locationTemplate.getLastTimestamp());
				locationEntry.setSecondlastTimestamp(locationTemplate.getSecondlastTimestamp());
				return "POSITION UPDATED";
			}
		}
		return "POSITION UPDATE FAILED";
	}

}
