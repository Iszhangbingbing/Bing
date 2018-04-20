package classes.com.cn.web;
//����cookieʵ�ֵ��û��������ʷ
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BrowseServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		for(int i=0;cookies!=null&&i<cookies.length;i++){
			if(cookies[i].getName().equals("browseHistory")){
				cookies[i].setPath("/Bing/BrowseServlet");
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
				response.getWriter().write("delete sucess");
				return;
			}
		}
		response.getWriter().write("there is no cookie in your browse");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=utf-8");
		
		//ȡ���������Ʒid
		String id = request.getParameter("id");
		//�õ����е�cookies
		Cookie[] cookies = request.getCookies();
		Cookie c = null;
		boolean flag = false;
		for (int i = 0;cookies!=null&&i < cookies.length; i++) {
			if(cookies[i].getName().equals("browseHistory")){
				String value = cookies[i].getValue();
				if(!value.contains(id)){
					value = value+","+id;
					cookies[i].setValue(value);
					setCookie(cookies[i]);
					c = cookies[i];
					response.getWriter().write("�������:"+value);
				}else{
					
					setCookie(cookies[i]);
					cookies[i].setValue(replace(value, id));
					c = cookies[i];
					response.getWriter().write("���������:"+id);
				}
				flag = true;
				break;
			}
		}
		//flag==false ˵��û���ҵ��ˣ���һ�η��� 
		if(!flag){
			c = new Cookie("browseHistory",id);
			setCookie(c);
			response.getWriter().write("������ˣ�"+id);
		}
		response.addCookie(c);
	}
	private void setCookie(Cookie c){
		c.setPath("/Bing/BrowseServlet");
		c.setMaxAge(1*24*60*60);
	}
	private  String replace(String str,String replace) {
		String str2 = "";
		String[] temp = str.split(",");
		int index = -1;
		for (int i=0;i<temp.length;i++) {
			if(temp[i].equals(replace)){
				index = i;
				break;
			}
		}
		for(int i=index;i<temp.length-1;i++){
			temp[i] = temp[i+1];
			if(i==temp.length-2){
				temp[i+1] = replace;
			}
		}
		for(int i=0;i<temp.length;i++){
			if(i==0){
				str2 = temp[i];
			}else{
				str2 = str2+","+temp[i];
			}
		}
		return str2;
	}

}
