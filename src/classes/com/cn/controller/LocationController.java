package classes.com.cn.controller;

import java.sql.SQLException;
import java.util.List;

import classes.com.cn.domain.LocationDao;

public class LocationController {
	private LocationDao dao = new LocationDao();
	public List getLocation(int id){
		return dao.queryLocation(id);
	}
	public void addLocation(int id,String name){
		try {
			dao.addLocation(id, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
