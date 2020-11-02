package com.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.model.Student;
public class StudentDao {


   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/studentdb";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "M@dhu143";
   
   
   //CURD queries
    private static final String Insert_Student = "INSERT INTO students (name,age,branch,section) VALUES" + "(?,?,?,?)";
	private static final String SELECT_STUDENT_BY_ID = "select id,name,age,branch,section from students where id =?";
	private static final String SELECT_ALL_STUDENTS = "select * from students";
	private static final String DELETE_STUDENT_SQL = "delete from students where id = ?;";
	private static final String UPDATE_STUDENT_SQL = "update students set name = ?,age= ?, branch =?,section=? where id = ?;";

		
	
	//function to get connection
	private Connection getConnection() {
		Connection connection=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(DB_URL,USER,PASS);
				
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		return connection;
	}//end of getConnection
	
	
	
	//inserting data into table -(Name,Age,Branch,Section)  id will be auto_incremented
	public boolean insertStudentdb(Student newStudent) throws SQLException{
		Connection connection=getConnection();
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(Insert_Student);
			preparedStatement.setString(1,newStudent.getName());
			preparedStatement.setInt(2, newStudent.getAge());
			preparedStatement.setString(3, newStudent.getBranch());
			preparedStatement.setString(4, newStudent.getSection());
			
			
			preparedStatement.executeUpdate();
		} catch( SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			connection.close();
		}
		return true;
	}//end of insertStudent
		
	
	
	//to update data in db by student id
	public boolean updateStudent(Student student) throws SQLException {
		boolean rowUpdated = false;
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);
			statement.setString(1, student.getName());
			statement.setInt(2, student.getAge());
			statement.setString(3, student.getBranch());
			statement.setString(4, student.getSection());
			statement.setInt(5, student.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return rowUpdated;
	}//end of updateStudent

	
	//to delete data in student db by their id
	public boolean deleteStudent(int id) throws SQLException {
		boolean rowDeleted = false;
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return rowDeleted;
	}//end of deleteStudent
	
	
	
	//during edit option to print respective student data
	public Student selectStudent(int id) throws SQLException {
		Student student = null;
		Connection connection = getConnection();
		try{
			
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				String name = rs.getString("name");
				int age = Integer.parseInt(rs.getString("age"));
				String branch = rs.getString("branch");
				String section = rs.getString("section");
				student = new Student(id,name, age, branch, section);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return student;
	}//end of selectStudent
	
	
	
	//to list all students data from db
	public List<Student> selectAllStudents() throws SQLException {
		
		List<Student> students = new ArrayList<>();
		Connection connection = getConnection();
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = Integer.parseInt(rs.getString("age"));
				String branch = rs.getString("branch");
				String section = rs.getString("section");
				students.add(new Student(id, name, age, branch, section));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			connection.close();
		}
		return students;
	}//end of selectAllStudents




   }//end of class

