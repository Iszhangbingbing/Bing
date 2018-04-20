package classes.com.cn.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.com.cn.tools.DatabaseTools;

public class AddBuyCar extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Cookie[] cookies = request.getCookies();
		//������û��cookie��˵���ǵ�һ�η���
		if(cookies==null){
			Cookie temp = new Cookie("buyCar",id);
			temp.setPath(request.getServletPath()+"/AddBuyCar");
			response.addCookie(temp);
			return;
		}
		//�����е�cookie����buycar,����value
		for (Cookie cookie : cookies) {
			if(cookie.getName().equalsIgnoreCase("buyCar")){
				//�������
				String value = cookie.getValue();
				System.out.println("cookie value"+value);
				value = value+","+id;
				cookie.setValue(value);
				System.out.println("���ﳵ��:"+cookie.getValue());
				response.addCookie(cookie);
				return;
			}
		}
		//�����е�cookie��û��buycar
		Cookie temp = new Cookie("buyCar",id);
		temp.setPath(request.getContextPath()+"/AddBuyCar");
		response.addCookie(temp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
