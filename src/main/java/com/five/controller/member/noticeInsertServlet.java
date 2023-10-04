package com.five.controller.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.five.dao.Member_Dao;
import com.five.dto.Notice;

/**
 * Servlet implementation class noticeInsertServlet
 */
@WebServlet("/noticeInsert.do")
public class noticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public noticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("member/noticeInsert.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String t = request.getParameter("ntitle");
		String c = request.getParameter("ncontent");
		Notice no = new Notice();
		no.setNtitle(t);
		no.setNcontent(c);
		
		Member_Dao mDao = Member_Dao.getInstance();
		mDao.noticeInsert(no);
		
		response.sendRedirect("notice.do");
	}

}
