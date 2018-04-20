package classes.com.cn.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.com.cn.controller.LocationController;

public class LocationServlet2 extends HttpServlet {

	private LocationController c = new LocationController();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		List list = c.getLocation(Integer.parseInt(id));
		for (Object object : list) {
			System.out.println(object);
		}
		response.sendRedirect("/Bing/1.1.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
