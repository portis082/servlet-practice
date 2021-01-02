package com.bit2021.guestbook.repository;

import java.util.List;

import com.bit2021.guestbook.vo.GuestbookVo;

public class GuestbookRepositoryTest {

	public static void main(String[] args) {
		//testInsert();
		testSelect();

	}

	private static void testInsert() {
		GuestbookVo vo = new GuestbookVo();
		vo.setName("홍길동");
		vo.setPassword("1234");
		vo.setMessage("안녕하세요");
		
		boolean result = new GuestbookRepository().insert(vo);
		if (result) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}
	
	private static void testSelect() {
		List<GuestbookVo> list = new GuestbookRepository().findAll();
		if(list.size() == 2) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}

}
