package classes.com.cn.web;
//����cookie��¼�û��ϴη���ʱ��
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieDemo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] c = request.getCookies();
		if(c!=null){
			for (Cookie cookie : c) {
				System.out.println(cookie.getName()+":"+cookie.getValue());
				if(!cookie.getName().equalsIgnoreCase("lastTime")){
					Cookie temp = new Cookie("lastTime",System.currentTimeMillis()+"");
					temp.setMaxAge(1*24*60*60);//��Ϊ��λ
					temp.setPath("/Bing/CookieDemo");
					response.addCookie(temp);
				}
				if(cookie.getName().equalsIgnoreCase("lastTime")){
					String time = cookie.getValue()+"";
					Date d = new Date(Long.parseLong(time));
					System.out.println("���ϴεķ���ʱ����:"+d.toString());
					System.out.println("lastTime����ʱ����:"+cookie.getMaxAge()+"����");
					//����cookieֵ
					cookie.setValue(System.currentTimeMillis()+"");
					System.out.println("������cookieʱ��");
				}
			}
		}else{
			Cookie temp = new Cookie("lastTime",System.currentTimeMillis()+"");
			temp.setMaxAge(1*24*60*60);
			temp.setPath("/Bing/CookieDemo");
			response.addCookie(temp);
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
