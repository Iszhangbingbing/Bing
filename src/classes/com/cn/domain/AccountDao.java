package classes.com.cn.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import classes.com.cn.tools.DatabaseTools;

public class AccountDao {
	private Connection conn;
	private PreparedStatement state;
	private ResultSet result;
	public AccountDao(){
		
	}
	public void transfer(int fId,int tId,String money){
		String sql1 = "update account set money=money+? where id=?";
		String sql2 = "update account set money=money-? where id=?";
	    conn = DatabaseTools.getConnect();
	    Savepoint key = null;
	    try {
	    	conn.setAutoCommit(false);//开启事务
//	    	conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE)  //设置事务隔离级别
			state =  conn.prepareStatement(sql1);
			//加钱
			state.setString(1, money);
			state.setInt(2, tId);
			state.executeUpdate();
			state = conn.prepareStatement(sql2);
		    key = conn.setSavepoint();
			//捡钱
			int i =1/0;
			state.setString(1, money);
			state.setInt(2, fId);
			state.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback(key);//手动回滚后要再次commit
				conn.commit();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
