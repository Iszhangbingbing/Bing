package classes.com.cn.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionDemo extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		//����������˴洢sessionID��cookie�����Ͳ����ڴ���session
		HttpSession session = request.getSession();
		List list = (List) session.getAttribute("list");
		
		Cookie c =  new Cookie("JSESSIONID",session.getId());
		c.setMaxAge(10*60);
		c.setPath(request.getContextPath()+"/");
		//��һ����
		if(list==null){
			list = new ArrayList();
			list.add(id);
			session.setAttribute("list", list);
		}else{
			if(!list.contains(id)){
				list.add(id);
			}
		}
		response.addCookie(c);
		System.out.println("哈哈哈哈哈");
		response.getWriter().write("go to the buycar success");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List list = (List) session.getAttribute("list");
		if(list!=null){
			int count = list.size();
			System.out.println("size:"+count);
			for(int i=count-1;i>=0;i--){
				Object obj = list.remove(i);
				System.out.println(obj);
				
			}
		}
		response.getWriter().write("your buy car is empty now");
	}

}
