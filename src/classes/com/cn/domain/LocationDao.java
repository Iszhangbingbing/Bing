package classes.com.cn.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.com.cn.bean.Location;
import classes.com.cn.tools.DatabaseTools;

public class LocationDao {
	private Connection conn;
	private PreparedStatement state;
	private ResultSet result;
	public List queryLocation(int id){
		List list = new ArrayList(0);
		Location l = null;
		String sql = "select c.* from location p left join location c on p.lft<=c.lft and p.rgt>=c.rgt where p.id=?";
		conn = DatabaseTools.getConnect();
		try {
			state = conn.prepareStatement(sql);
			state.setInt(1, id);
			result = state.executeQuery();
			while(result.next()){
				l = new Location();
				l.setId(result.getInt("id"));
				l.setName(result.getString("name"));
				l.setLft(result.getInt("lft"));
				l.setRgt(result.getInt("rgt"));
				list.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DatabaseTools.release(conn, state, result);
		}
		return list;
	}
	public void addLocation(int id,String name) throws SQLException{
		conn = DatabaseTools.getConnect();
		int rgt=-1;
		String sql1 = "select rgt from location where id=?";
		String sql2 = "update location set rgt=rgt+2 where rgt>=?";
		String sql3 = "update location set lft=lft+2 where lft>?";
		String sql4 = "insert into location (name,lft,rgt) values (?,?,?)";
		state = conn.prepareStatement(sql1);
		state.setInt(1,id);
		result = state.executeQuery();
		while(result.next()){
			rgt = result.getInt("rgt");
		}
		state = conn.prepareStatement(sql2);
		state.setInt(1, rgt);
		state.executeUpdate();
		state = conn.prepareStatement(sql3);
		state.setInt(1, rgt);
		state.execute();
		state = conn.prepareStatement(sql4);
		state.setString(1, name);
		state.setInt(2, rgt);
		state.setInt(3, rgt+1);
		state.execute();
		DatabaseTools.release(conn, state, result);
	}
	public void deleteLocation(){
		
	}
}
