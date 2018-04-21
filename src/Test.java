import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import javax.sql.DataSource;

import sun.jdbc.odbc.JdbcOdbc;

import classes.com.cn.bean.BeanHandler;
import classes.com.cn.bean.Person;
import classes.com.cn.bean.ResultHandler;
import classes.com.cn.tools.JdbcUtils;

public class Test {
	public static void main(String[] args) throws Exception {
		String sql = "select * from Person";
		System.out.println();
		我回退了版本
		ResultHandler handler = new BeanHandler(Person.class);
		Object[] objs =  JdbcUtils.query(sql, new Object[0], handler);
		for (Object object : objs) {
			System.out.println(object);
		}
	/*	Connection conn = JdbcUtils.getConnection();
		DatabaseMetaData meta = null;
		try {
			meta = conn.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(meta.getDriverMajorVersion());*/
	}
}
