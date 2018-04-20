package classes.com.cn.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.com.cn.controller.LocationController;

public class LocationServlet extends HttpServlet {
	private LocationController c = new LocationController();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(name+":"+id);
		c.addLocation(id, name);
		response.sendRedirect("/Bing/1.html");
	}
}
