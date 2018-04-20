package classes.com.cn.web;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseRequest extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		System.out.println(name);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		res(response);
//		redirect(response);
//		sendAndForward(request, response);
//		getParameter(request);
		String name = request.getParameter("name");
		String temp = new String(name.getBytes("iso8859-1"), "utf-8");
		request.setAttribute("name", "������hi������hi");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	private void getParameter(HttpServletRequest request) {
		Map<String,String[]> map = request.getParameterMap();
		for(Map.Entry<String, String[]> m :map.entrySet()){
			System.out.print(m.getKey()+":");
			for (int i = 0; i < m.getValue().length; i++) {
				System.out.print(m.getValue()[i]+" ");
			}
			System.out.println();
		}
	}
	
	//ת��   �������
	private void sendAndForward(HttpServletRequest request,HttpServletResponse response){
		try {
			response.getOutputStream().write("12234".getBytes());
			request.getRequestDispatcher("/Redirect").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	
	//�����ض���
	private void redirect(HttpServletResponse response){
		try {
			response.sendRedirect(this.getServletContext().getServletContextName()+"/pic/IMG_20170728_202116.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void res(HttpServletResponse response) throws IOException {
		//�����������utf-8����
		response.setHeader("content-Type", "text/html;charset=utf-8");
		String msg = "�й�";
//		response.getOutputStream().write(msg.getBytes("utf-8"));
		response.getWriter().write(msg);
		//���·��������        �����������ַ�ʱ�����߷���������response�е������utf-8����
		response.setCharacterEncoding("utf-8");
	}


}
