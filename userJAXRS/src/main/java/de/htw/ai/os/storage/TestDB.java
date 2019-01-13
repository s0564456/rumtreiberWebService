package de.htw.ai.os.storage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import de.htw.ai.os.bean.LocationEntry;
import de.htw.ai.os.bean.User;

public class TestDB implements Dao{
	
	private static Collection<LocationEntry> res = new ArrayList<LocationEntry>();
	public int index = 5;
	
	public TestDB() {
		res.add(new LocationEntry( 1, "Harry", new Timestamp(System.currentTimeMillis() )));
		res.add(new LocationEntry( 2, "Snape", new Timestamp(System.currentTimeMillis() )));
		res.add(new LocationEntry( 3, "Dumbledore", new Timestamp(System.currentTimeMillis() )));
		res.add(new LocationEntry( 4, "McGonagle", new Timestamp(System.currentTimeMillis() )));
		res.add(new LocationEntry( 5, "Finch", new Timestamp(System.currentTimeMillis() )));
	}

	@Override
	public boolean authenticate(long token) {
		for (LocationEntry locationEntry : res) {
			long id = locationEntry.getId();
			if (id == token) {
				return true;
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
		return res;
	}

	@Override
	public long addUser(String user) {
		++index;
		res.add(new LocationEntry( index, user, new Timestamp(System.currentTimeMillis() )));
		return index;
	}

	@Override
	public String updatePosition(LocationEntry locationTemplate, long auth) {
		for (LocationEntry locationEntry : res) {
			long id = locationEntry.getId();
			if (id == auth) {
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
