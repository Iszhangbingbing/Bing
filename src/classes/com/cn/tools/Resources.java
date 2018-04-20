package classes.com.cn.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

//通过类装载器  读取大文件容易占用内存
public class Resources {
	//这种方式读取不能做到实时更新
	public static void getResource(){
		InputStream in =Resources.class.getClassLoader().getResourceAsStream("/classes/Database.properties");
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("读取配置文件失败");
		}
		System.out.println("普通java程序读取资源文件  url:"+pro.getProperty("url")+"\nuser:"+pro.getProperty("user")+"\npwd:"+pro.getProperty("password"));
	}
	
	
	//这种方式读取能做到实时更新
	public static void getResourceWithUpdate(){
		URL url = Resources.class.getClassLoader().getResource("/classes/Database.properties");
		String path = null;
		InputStream in = null;
		Properties pro = new Properties();
		try {
			path = url.toURI().getPath();
			in = new FileInputStream(new File(path));
			pro.load(in);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("普通java程序读取资源文件  url:"+pro.getProperty("url")+"\nuser:"+pro.getProperty("user")+"\npwd:"+pro.getProperty("password"));
	}
}
