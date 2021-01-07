<%@page import="com.bit2021.guestbook.repository.GuestbookRepository"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bit2021.guestbook.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");	
	GuestbookVo vo = new GuestbookVo();
	
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String message = request.getParameter("message");
	
	vo.setName(name);
	vo.setPassword(password);
	vo.setMessage(message);
	
	boolean result = new GuestbookRepository().insert(vo);
	response.sendRedirect("index.jsp");
%>