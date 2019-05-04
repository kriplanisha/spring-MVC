package mvcdemo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDataUtil studentdatautil;
	@Resource(name="jdbc/mvcdemo")
	private DataSource datasource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			studentdatautil=new StudentDataUtil(datasource);
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
//Listing of students MV
		try {
			
//			reading command parameter
			String commanding=request.getParameter("command");
			if(commanding==null)
			{
				commanding="List";
			}
			switch(commanding)
			{
			case "List":
				liststudents(request,response);
				break;
			case "ADD":
				addstudents(request,response);
				break;
			case "Load":
				loadstudent(request,response);
				break;
			case "update":
				updatestudent(request,response);
				break;
			case "delete":
				deletestudent(request,response);
				break;
				default:
				liststudents(request,response);	
			}
	}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
	}

	
	private void deletestudent(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
//		read student id from data
		String sid=request.getParameter("studentid");
//		delete from db
		studentdatautil.deletestudent(sid);
//		display back to list page
		liststudents(request,response);
	}
	
	
	
	
	private void updatestudent(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
//		read student info from db
		int id=Integer.parseInt(request.getParameter("studentid"));
		String fname=request.getParameter("firstname");
		String lname=request.getParameter("lastname");
		String mail=request.getParameter("email");
//		create a new student object
		Student s=new Student(id,fname,lname,mail);
//		perform update on db
		studentdatautil.updatestudent(s);
//		send them to list page
		liststudents(request,response);
	}
	
	
	
	
	private void loadstudent(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
//		read student id from data
		String sid=request.getParameter("studentid"); 
//		get from db
		Student s=studentdatautil.getstudent(sid);
//		place in request
		request.setAttribute("updating", s);
//		send to jsp
		RequestDispatcher rd=request.getRequestDispatcher("updatestudent.jsp");
		rd.forward(request, response);
	}
	
	
	
	private void addstudents(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
//		read student info from data
		String fname=request.getParameter("firstname");
		String lname=request.getParameter("lastname");
		String mail=request.getParameter("email");
		
//		create a new student object
		Student s =new Student(fname,lname,mail);
		
//		add student to database
		studentdatautil.addstudent(s);
		
//		send to main page
		liststudents(request,response);
	}
	
	
	
	private void liststudents(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
//		get students list from DB
		List<Student> student=studentdatautil.getstudent();
		
		
//		add students to request
		request.setAttribute("studentlist", student);
		
		
//		send to JSP page (view)
		RequestDispatcher rd=request.getRequestDispatcher("liststudents.jsp");
		rd.forward(request, response);
	}
	
	
	
	
}
