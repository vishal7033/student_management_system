package student_management_system.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/display")
public class Displaycontroller extends HttpServlet{

	String name=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter printWriter=resp.getWriter();
		
		Cookie[] cookies=req.getCookies();
		
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("username")) {
				name=cookie.getValue();
			}
		}
		printWriter.print("<h1>thank you</h1>");
	}
}