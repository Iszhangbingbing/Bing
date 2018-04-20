package classes.com.cn.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.com.cn.tools.Resources;


public class ContextTest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = request.getSession().getServletContext();
//		getContext();
//		getParameter(context);
//		dispach(request, response);
//		contextGetSource(context);
//		contextGetRealPath(context);
		Resources.getResourceWithUpdate();
	}
	
	
	//ͨ��ؾ�����ļ�·����������Եõ��ļ���
	private void contextGetRealPath(ServletContext context){
		String path = context.getRealPath("/WEB-INF/classes/classes/Database.properties");
		String filename = path.substring(path.lastIndexOf("\\")+1);
		InputStream in =null;
		Properties pro = new Properties();
		try {
			in = new FileInputStream(new File(path));
			pro.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(filename+"  url:"+pro.getProperty("url")+"\nuser:"+pro.getProperty("user")+"\npwd:"+pro.getProperty("password"));
	}
	
	
	//ͨ����ļ�������ȡ
	private void contextGetSource(ServletContext context){
		InputStream in = context.getResourceAsStream("/WEB-INF/classes/classes/Database.properties");
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("��ȡ�����ļ�ʧ��");
		}
		System.out.println("url:"+pro.getProperty("url")+"\nuser:"+pro.getProperty("user")+"\npwd:"+pro.getProperty("password"));
	}
	
	
	//ȡ��ServletConetxt
	private void getContext() {
		ServletContext context = this.getServletConfig().getServletContext();
//		ServletContext context = this.getServletContext();
		
	}

	
	//�õ�Servletcontext��ʼ������
	private void getParameter(ServletContext context) {
		System.out.println(context.getInitParameter("admin"));
		System.out.println(context.getServletContextName());
		System.out.println(context.getRealPath("/pic"));
	}

	
	
	//dispatcherת������
	private void dispach(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().getServletContext().getRequestDispatcher("/login.html").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
