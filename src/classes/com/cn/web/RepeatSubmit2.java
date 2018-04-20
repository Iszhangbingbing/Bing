package classes.com.cn.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RepeatSubmit2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Serverkey = (String) request.getSession().getAttribute("key");
		String ClientKey = request.getParameter("key");
		if(!isToken(Serverkey, ClientKey)){
			response.getWriter().write("please do not repeate submit");
			return;
		}
		request.getSession().removeAttribute("key");
		response.getWriter().write("welcome");
	}
	private boolean isToken(String Serverkey,String ClientKey){
		if(Serverkey==null){
			return false;
		}
		if(ClientKey==null){
			return false;
		}
		if(!ClientKey.equals(Serverkey)){
			return false;
		}
		return true;
	}
}
