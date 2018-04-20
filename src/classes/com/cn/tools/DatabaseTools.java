/**
 * @author Administrator
 * @version v1.0
 * 自己定义的数据库连接池
 */
package classes.com.cn.tools;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import javax.sql.DataSource;

import classes.com.cn.bean.MyConnection;

public class DatabaseTools implements DataSource{
	private static Map<MyConnection,Boolean> map = new HashMap<MyConnection,Boolean>();
	static{
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			String path = DatabaseTools.class.getClassLoader().getResource("Database.properties").toURI().getPath();
			InputStream in = new FileInputStream(path);
			Properties prop = new Properties();
			prop.load(in);
			Connection conn = null;
			MyConnection my = null;
			//创建10个连接
			for(int i=0;i<10;i++){
				conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pwd"));
				my = new MyConnection(conn);
				map.put(my,false);
			}
			System.out.println("创建数据库连接池：");
			showConnec();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static MyConnection getConnect(){
		Set<Map.Entry<MyConnection, Boolean>> entry =  map.entrySet();
		for (Map.Entry<MyConnection, Boolean> object : entry) {
			if(object.getValue()==false){
				object.setValue(true);
				getConnectionlog(object.getKey());
				return object.getKey();
			}
		}
		return null;
	}
	public static void close(Connection conn){
		Set<Map.Entry<MyConnection, Boolean>> entry =  map.entrySet();
		for (Map.Entry<MyConnection, Boolean> object : entry) {
			if(object.getKey()==conn){
				object.setValue(false);
				releaseConnectionlog(object.getKey());
				break;
			}
		}
	}
	public static void showConnec(){
		Set<Map.Entry<MyConnection,Boolean>> set = map.entrySet();
		for(Map.Entry<MyConnection, Boolean> obj:set){
			System.out.println(obj.getKey()+":"+obj.getValue());
		}
	}
	public static void release(Connection conn,PreparedStatement state,ResultSet result){
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
	private static void getConnectionlog(Connection conn){
		System.out.println("获取:"+conn);
	}
	private static void releaseConnectionlog(Connection conn){
		System.out.println("释放:"+conn);
	}
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
