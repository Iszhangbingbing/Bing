package classes.com.cn.bean;

import java.sql.ResultSet;

public interface ResultHandler {
	public  Object[] handler(ResultSet result);
}
