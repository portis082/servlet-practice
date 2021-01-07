package com.bit2021.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2021.guestbook.repository.GuestbookRepository;
import com.bit2021.guestbook.vo.GuestbookVo;


public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("a");
		
		if ("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("message");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);
			
			new GuestbookRepository().insert(vo);
			response.sendRedirect(request.getContextPath() + "/gb");
			
		} else if("deleteform".equals(action)){
			RequestDispatcher rd = request.getRequestDispatcher("/views/deleteform.jsp");
			rd.forward(request, response);
			
		} else if("delete".equals(action)){
			String no = request.getParameter("no");
			String password = request.getParameter("password");

			new GuestbookRepository().delete(Long.parseLong(no), password);
			response.sendRedirect(request.getContextPath() + "/gb");
			
		} else {
			//list
			List<GuestbookVo> list = new GuestbookRepository().findAll();
			
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/views/index.jsp");
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
