package classes.com.cn.bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeanHandler implements ResultHandler {
	private Class cla;
	public BeanHandler(Class cla){
		this.cla = cla;
	}
	public Object[] handler(ResultSet result) {
		List list = null;
		try {
			if(!result.next()){
				return null;
			}
			list = new ArrayList();
			Field[] fileds = cla.getDeclaredFields();
			Constructor constructor = cla.getConstructor();
	 		while(result.next()){
	 			Object obj = constructor.newInstance();
	 			String name = null;   //字段名称
	 			Object value = null;	//字段值
	 			for (Field field : fileds) {
					name = field.getName();
					value = result.getObject(name);
					field.setAccessible(true);
					field.set(obj, value);
				}
	 			list.add(obj);
	 		}
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		Object[] objs = new Object[list.size()];
		for (int i = 0; i < objs.length; i++) {
			objs[i] = list.get(i);
		}
		return objs;
	}

}
