package classes.com.cn.web;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServletDemo() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		System.out.println("ServletDemo destroy.............");

	}
	private void  redirect(HttpServletResponse response) {
		response.setStatus(302);
		response.setHeader("Location", "/Bing/login.html");
	}
	
	//����content-typeͷ���������������ļ���ʽ    ѹ��
	private void test(HttpServletResponse response) throws IOException{
		InputStream in = this.getServletContext().getResourceAsStream("/pic/IMG_20170728_201655.jpg");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream Gout = new GZIPOutputStream(out);
		byte[] temp = new byte[100];
		int a = -1;
		int count = 0;//��¼û��ѹ��ǰ���ֽ���
		while((a=in.read(temp))>0){
			Gout.write(temp,0,a);
			count = count+a;
		}
		Gout.close();
		in.close();
		byte[] temp2 = out.toByteArray();
		out.close();
		System.out.println("ѹ��ǰ��"+count+"  ѹ����:"+temp2.length);
		response.setHeader("content-Encoding", "gzip");
		response.setHeader("content-Length", temp2.length+"");
		response.setHeader("content-type","image/jpeg");
		response.getOutputStream().write(temp2);
	}
	
	//��������������ط�ʽ���ļ�
	private void test2(HttpServletResponse response) throws IOException{
		String path = this.getServletContext().getRealPath("/pic/��־�Ե�����.jpg");
		String filename = path.substring(path.lastIndexOf("\\")+1);
		System.out.println(filename);
		response.setCharacterEncoding("utf-8");
		response.setHeader("context-type", "text/html;charset=utf-8");
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));
		InputStream in = new FileInputStream(path);
		OutputStream out = response.getOutputStream();
		byte[] car = new byte[100];
		int a = -1;
		while((a=in.read(car))>0){
			out.write(car, 0, a);
		}
		out.close();
		in.close();
	}
	//����servletȡ����
	public void test3(){
		ServletConfig config = this.getServletConfig();
		if(config==null){
			System.out.println("eeee");
			return;
		}
		Enumeration temp =  config.getInitParameterNames();
		while (temp.hasMoreElements()) {
			String str = (String) temp.nextElement();
			System.out.println(str+":"+config.getInitParameter(str));
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		test(response);
//		test2(response);
		test3();
		response.getWriter().write("addd");
//		System.out.println(request.getCharacterEncoding());
		System.out.println("doGet  running..................."+request.getMethod());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	//servlet������init����
	/*public void init(ServletConfig config) {
	}*/
	public void init() throws ServletException {
		System.out.println("ServletDemo init.............");
	}

}
