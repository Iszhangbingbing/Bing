package classes.com.cn.tools;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import classes.com.cn.bean.ResultHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	private static DataSource source;
	private static Connection conn;
	private static PreparedStatement state;
	private static ResultSet result;
	static{
		//DBCP连接池
		/*try {
			String path = JdbcUtils.class.getClassLoader().getResource("dbcpconfig.properties").toURI().getPath();
			InputStream in = new FileInputStream(path);
			Properties prop = new Properties();
			prop.load(in);
			BasicDataSourceFactory factory = new BasicDataSourceFactory();
			source = factory.createDataSource(prop);
			System.out.println("创了数据库连接池");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//c3p0连接池
		 source = new ComboPooledDataSource("mysql");
		 System.out.println("使用c3p0");
		
	}
	public static Object[] query(String sql,Object[] obj,ResultHandler handler){
		Object[] objs = null;
		try {
			conn = getConnection();
			state = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				state.setObject(i+1, obj[i]);
			}
			result = state.executeQuery();
			objs = handler.handler(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return objs;
	}
	
	
	public static Connection getConnection(){
		Connection conn =null;
		try {
			conn = source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
