
package student_management_system.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import student_management_system.dao.Studentdao;
import student_management_system.dto.Student;

@WebServlet("/login")
public class LoginController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		Studentdao studentdao=new Studentdao();
		Student student=studentdao.loginStudent(email);
		
		if(student.getEmail().equals(email)&&student.getPassword().equals(password)) {
			List<Student> list=studentdao.getAllStudents();
			req.setAttribute("list", list);
//			Cookie cookie=new Cookie("username", student.getName());
//			resp.addCookie(cookie);
			HttpSession httpSession=req.getSession();
			httpSession.setAttribute("username", student.getName());
			RequestDispatcher dispatcher=req.getRequestDispatcher("display");
			dispatcher.forward(req, resp);
//			RequestDispatcher dispatcher=req.getRequestDispatcher("display.jsp");
//			dispatcher.forward(req, resp);
					
		}else {
			req.setAttribute("message", "please login with proper credentials");
			RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
			dispatcher.include(req, resp);
		}
				
				
	}
}

