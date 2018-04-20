package classes.com.cn.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionDemo2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=utf-8");
		HttpSession  session = request.getSession();
		List list = (List) session.getAttribute("list");
		if(list==null){
			response.getWriter().write("your buy car is empty");
		}else{
			String str = "�㹺���ˣ�\n";
			for (Object object : list) {
				str = str+object;
			}
			response.getWriter().write(str);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
