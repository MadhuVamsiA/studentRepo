package com.control;
//imports
import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Student;
import com.dao.StudentDao;

/**hh
 * Servlet implementation class AddNewStudent
 * 
 * import javax.servlet.annotation.WebServlet;
 * @WebServlet("/")
 * 
 */

public class AddNewStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	StudentDao studentDao=new StudentDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertStudent(request, response);
				break;
			case "/delete":
				deleteStudent(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateStudent(request, response);
				break;
			case "/list":
				listStudents(request, response);
				break;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			throw new ServletException(ex);
		}
		
		
		
	}

	
	//insert students details into database --- will be called from studentForm
	private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException
	{
		//get parameters
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		String branch=request.getParameter("branch");
		String section=request.getParameter("section");
		
		//create student model with the data given
		Student newStudent = new Student(name,age,branch,section);
		
		//call insert students from DAO
		studentDao.insertStudentdb(newStudent);
		listStudents(request, response);

	}

	
	//list all students in student database
	private void listStudents(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
	
		//get all student detils from DAO
		List<Student> studs=studentDao.selectAllStudents();
		request.setAttribute("listStudents", studs);
		RequestDispatcher dispatcher = request.getRequestDispatcher("listStudents.jsp");
		dispatcher.forward(request, response);
	}
	
	
	//New form to add student details
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentForm.jsp");
		dispatcher.forward(request, response);
	}

	//show selected student details in  edit form(select with student id)
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Student existingStudent= studentDao.selectStudent(id);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentForm.jsp");
		request.setAttribute("student", existingStudent);
		dispatcher.forward(request, response);

	}
	
	//update student details with student ID
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		String branch=request.getParameter("branch");
		String section=request.getParameter("section");

		
		Student studentUpdate= new Student(id, name, age, branch,section);
		studentDao.updateStudent(studentUpdate);
		response.sendRedirect("list");
	}
	
	
	//delete student by student id
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		studentDao.deleteStudent(id);
		response.sendRedirect("list");

	}


}
