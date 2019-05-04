package mvcdemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDataUtil 
{
	private static DataSource datasource;
	public StudentDataUtil(DataSource theDataSource) {
		datasource=theDataSource;
	}
	public List<Student> getstudent() throws Exception 
	{
		List<Student> student=new ArrayList<>();
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
//			get a connection
			con=datasource.getConnection();
//			create sql statement
			String query="select * from student1";
			st=con.createStatement();
//			execute query
			rs=st.executeQuery(query);
//			process result set
			while(rs.next()) {
//				retrieve data from result set row
				int id=rs.getInt("id");
				String firstname=rs.getString("firstname");
				String lastname=rs.getString("lastname");
				String email=rs.getString("email");
//				create new student object
				Student s=new Student(id,firstname,lastname,email);
//				add it to the list of students
				student.add(s);
			}
			return student;
		}
		finally {
//			close connection
			con.close();
			st.close();
			rs.close();
		}
	}
	public void addstudent(Student s) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		try {
//			get database connection
			con=datasource.getConnection();
//			create sql for inserting
			String query="insert into student1" + "(firstname,lastname,email)" + "values(?,?,?)";
			ps=con.prepareStatement(query);
//			set the param values for the student
			ps.setString(1, s.getFirstname());
			ps.setString(2, s.getLastname());
			ps.setString(3, s.getEmail());
//			execute sql insert
			ps.execute();
		}
		finally {
			con.close();
			ps.close();
		}
		
	}
	public Student getstudent(String sid) throws Exception 
	{
		Student s=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int idofstudent;
		try {
//			convert student to int
			idofstudent=Integer.parseInt(sid);
//			get connection to db
			con=datasource.getConnection();
//			create query to get the values
			String query="select * from student1 where id=?";
//			create prepared statement
			ps=con.prepareStatement(query);
//			set params
			ps.setInt(1,idofstudent);
//			execute query
			rs=ps.executeQuery();
//			retrieve data from resultset row
			if(rs.next())
			{
				String firstname=rs.getString(2);
				String lastname=rs.getString(3);
				String email=rs.getString(4);
				s=new Student(idofstudent,firstname,lastname,email);
			}
			else
			{
				throw new Exception("id not in db" + idofstudent);
			}
			return s;
		}
		finally {
		con.close();
		ps.close();
		rs.close();
		}
	}
	public void updatestudent(Student s) throws Exception 
	{
		Connection con=null;
		PreparedStatement ps=null;
		try {
//		get connection to db
		con=datasource.getConnection();
//		create query to get the values
		String query="update student1 set firstname=?,lastname=?,email=? where id=?";
//		create prepared statement
		ps=con.prepareStatement(query);
//		set params
		ps.setString(1,s.getFirstname());
		ps.setString(2,s.getLastname());
		ps.setString(3,s.getEmail());
		ps.setInt(4,s.getId());
//		execute query
		ps.execute();
		}
		finally {
		con.close();
		ps.close();
		}
	}
	public void deletestudent(String sid) throws SQLException 
	{
		Connection con=null;
		PreparedStatement ps=null;
		int idofstudent;
//		convert id to int 
		idofstudent = Integer.parseInt(sid);
		try {
//		get connection to db
		con=datasource.getConnection();
//		create query to get the values
		String query="delete from student1 where id=?";
//		create prepared statement
		ps=con.prepareStatement(query);
//		set params
		ps.setInt(1,idofstudent);
//		execute query
		ps.execute();
		}
		finally {
			con.close();
			ps.close();
		}
	}
}
