/* 
 * Name: Yi-Wen Chu    991624614
 * Assignment: Assignment 1 
 * Program: Computer Systems Technology -
 * 	Software Development and Network Engineering
 * File: AcademicStandingCalculatorServlet.java
 * 
 * Date: Oct 2, 2021
 * 
 * Description: Servelet for handle reqest, allowing user to 
 * add course grade and calculating GPA.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.academicCal.Grade;

/**
 * Servlet implementation class AcademicStandingCalculatorServlet.
 *
 * @author Yi-Wen Chu
 * Computer Systems Technology
 * Software Development and Network Engineering
 */
@WebServlet(urlPatterns={"/AcademicStandingCalculatorServlet"})
public class AcademicStandingCalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * Check how user enter the url.
		 * Null imply that user enter from the index.
		 * */
		String actBtn = request.getParameter("actBtn");
		actBtn = (actBtn == null)? "index" : actBtn; 
				
		// Whether output the total GPA bases on the pushed button.
		String showPage = null;
		switch (actBtn) {
			case "next": next(request); 
				showPage = "input_grade.jsp";
				break;
			case "done": done(request); 
				showPage = "output_grade.jsp";
				
				break;	
			default:
				request.getSession().invalidate();
				showPage = "input_grade.jsp";
		}
		
		request.getRequestDispatcher(showPage)
			.forward(request, response);
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/*
	 * The main logic for the next button.
	 * 
	 * Add the input course grade in a list,
	 * and save the updated list back into the session.
	 * */
	private void next(HttpServletRequest request) {
		
		// extract course grade from the request.
		String inputGrade = request.getParameter("courseGrade");
		
		// extract data in session.
		HttpSession session = request.getSession();
		List<Grade> gradeList = extractGradeList(session); 
		
		// save new course grade into gradeList.
		if(inputGrade != null) {
			gradeList.add(Grade.valueOf(inputGrade));
		}
		
		// save data to session.
		session.setAttribute("gradeList", gradeList);
	}
	
	/*
	 * The main logic for the done button.
	 * 
	 * Calculate sum up the GPA for the grades in the grade list, 
	 * and generate the academic standing message.
	 * */
	private void done(HttpServletRequest request) {
		
		// extract data in session.
		HttpSession session = request.getSession();
		List<Grade> gradeList = extractGradeList(session); 
		
		// calculate GPA and set it into request scope.
		String stdMsg = generateStdMsg(calcGPA(gradeList));
		request.setAttribute("stdMsg", stdMsg);
	}
	
	// get the grade list stored in the session.
	private List<Grade> extractGradeList(HttpSession session) {
		
		Object grades = session.getAttribute("gradeList");
		List<Grade> gradeList = (grades == null)?new ArrayList<Grade>(): 
									(ArrayList<Grade>)grades; 
		
		return gradeList;
	}
	
	// Calculate GPA according to the input course list.
	private double calcGPA(List<Grade> gradeList) {
		
		double totalGPA = 0; 
		for(Grade grade: gradeList) {
			totalGPA += grade.getGpa();
		}
		
		return totalGPA/gradeList.size();
	}
	
	// Generate the academic standing message according to GPA
	private String generateStdMsg(double gpa) {
		
		String msg = null;
		
		if(gpa >= 3.8) {
			msg = "Sheridan Scholar";
		} else if(gpa >= 3.5) {
			msg = "Honours ";
		} else if(gpa >= 2.0) {
			msg = "Good";
		} else if(gpa >= 1.8) {
			msg = "Conditional Pass";
		} else if(gpa >= 1.2) {
			msg = "Academic Probation";
		} else {
			msg = "Cannot Continue";
		}
		
		return msg;
	}

}
