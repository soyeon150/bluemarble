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
 * Servlet implementation class noticeUpdateServlet
 */
@WebServlet("/noticeUpdate.do")
public class noticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public noticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/noticeUpdate.jsp";
		String t = request.getParameter("ntitle");
		
		Member_Dao mDao = Member_Dao.getInstance();
		ArrayList<Notice> notice = mDao.getNoticeOne(t);
		request.setAttribute("notice", notice);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String t = request.getParameter("t");
		String ntitle = request.getParameter("ntitle");
		String c = request.getParameter("ncontent");
		
		Notice no = new Notice();
		no.setNtitle(ntitle);
		no.setNcontent(c);
		
		Member_Dao mDao = Member_Dao.getInstance();
		mDao.noticeUpdate(no, t);
		
		response.sendRedirect("notice.do");
	}

}
