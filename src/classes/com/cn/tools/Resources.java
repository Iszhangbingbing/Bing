package classes.com.cn.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

//ͨ����װ����  ��ȡ���ļ�����ռ���ڴ�
public class Resources {
	//���ַ�ʽ��ȡ��������ʵʱ����
	public static void getResource(){
		InputStream in =Resources.class.getClassLoader().getResourceAsStream("/classes/Database.properties");
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("��ȡ�����ļ�ʧ��");
		}
		System.out.println("��ͨjava�����ȡ��Դ�ļ�  url:"+pro.getProperty("url")+"\nuser:"+pro.getProperty("user")+"\npwd:"+pro.getProperty("password"));
	}
	
	
	//���ַ�ʽ��ȡ������ʵʱ����
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
		System.out.println("��ͨjava�����ȡ��Դ�ļ�  url:"+pro.getProperty("url")+"\nuser:"+pro.getProperty("user")+"\npwd:"+pro.getProperty("password"));
	}
}
