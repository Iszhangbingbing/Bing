package classes.com.cn.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.com.cn.bean.Person;
import classes.com.cn.domain.DatabaseDao;

public class DatabaseServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		Person p = new Person(name,pwd);
		DatabaseDao dao = new DatabaseDao();
		dao.insert(p);
		response.getWriter().write("register success");
//		login(response, p, dao);
	}

	private void login(HttpServletResponse response, Person p, DatabaseDao dao)
			throws IOException {
		if(dao.query(p)){
			response.getWriter().write("login success");
		}else{
			response.getWriter().write("please login again");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);	
	}

}
