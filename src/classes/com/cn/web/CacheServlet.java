package classes.com.cn.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CacheServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		expries(response);
	}

	private void expries(HttpServletResponse response) throws IOException {
		Long time = System.currentTimeMillis()+1*24*3600*1000;
		response.setDateHeader("expires", time);
		response.getWriter().write("expries time:"+new Date(time));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
