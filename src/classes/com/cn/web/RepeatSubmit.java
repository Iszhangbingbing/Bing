package classes.com.cn.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.com.cn.tools.DatabaseTools;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class RepeatSubmit extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TokenKey token = TokenKey.getInstance();
		String key = token.getKey();
		System.out.println("key:"+key);
		request.getSession().setAttribute("key", key);
		response.getWriter().write(key);
	}

}
class TokenKey{
	private static TokenKey token = new TokenKey();
	private Random random = new Random();
	private TokenKey(){
		
	}
	public static TokenKey getInstance(){
		return token;
	}
	public String getKey(){
		String str = System.currentTimeMillis()+""+random.nextInt(1000);
		byte[] key = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			key = digest.digest(str.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		Base64 base = new Base64();
		return base.encode(key);
	}
}