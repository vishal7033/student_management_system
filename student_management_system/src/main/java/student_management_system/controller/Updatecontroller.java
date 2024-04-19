
package student_management_system.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student_management_system.dao.Studentdao;
import student_management_system.dto.Student;

@WebServlet("/update")
public class Updatecontroller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		
		Studentdao studentdao=new Studentdao();
		Student student=studentdao.getStudentById(id);
		
		if (student!=null) {
			req.setAttribute("student", student);
			RequestDispatcher dispatcher=req.getRequestDispatcher("edit.jsp");
			dispatcher.forward(req, resp);
		}
		
		
	}
}