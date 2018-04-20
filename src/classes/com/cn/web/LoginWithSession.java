package classes.com.cn.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginWithSession extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		//��һ�ε���  ��֤����
		if(login==null){
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			if(name.equals("zbb")&&pwd.equals("123456")){
				login = "true";
				session.setAttribute("login", login);
				request.getRequestDispatcher("/list.html").forward(request, response);
			}else{
				response.getWriter().write("login fail");
			}
		}else{
			request.getRequestDispatcher("/list.html").forward(request, response);
		}
	}

}
