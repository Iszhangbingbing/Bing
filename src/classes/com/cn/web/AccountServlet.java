package classes.com.cn.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.com.cn.controller.AccountController;

public class AccountServlet extends HttpServlet {

	private AccountController c = new AccountController();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		c.transfer(1, 2, 100+"");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
