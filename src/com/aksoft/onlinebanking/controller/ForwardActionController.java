package com.aksoft.onlinebanking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForwardActionController")
public class ForwardActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ForwardActionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nextPage = request.getParameter("nextPage");
		request.getRequestDispatcher(nextPage).forward(request, response);
		
	}

}
