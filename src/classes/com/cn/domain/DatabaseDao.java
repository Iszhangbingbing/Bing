package classes.com.cn.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.com.cn.bean.Person;
import classes.com.cn.tools.DatabaseTools;


public class DatabaseDao {
	public boolean query(Person p){
		String sql = "select * from person where name=? and pwd=?";
		Connection conn = DatabaseTools.getConnect();
		PreparedStatement state = null;
		ResultSet set = null;
		boolean flag = false;
		try {
		   state = conn.prepareStatement(sql);
		   state.setString(1, p.getName());
		   state.setString(2, p.getPwd());
		   set = state.executeQuery();
		   if(set.next()){
			   flag = true;
		   }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release(conn, state, set);
		}
		return flag;
	}
	public void insert(Person p){
		String sql = "insert into person values (?,?)";
		Connection conn = DatabaseTools.getConnect();
		PreparedStatement state = null;
		try {
		   state = conn.prepareStatement(sql);
		   state.setString(1, p.getName());
		   state.setString(2, p.getPwd());
		   state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			release(conn, state, null);
		}
	}
	private void release(Connection conn,PreparedStatement state,ResultSet result){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(result!=null){
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(state!=null){
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
