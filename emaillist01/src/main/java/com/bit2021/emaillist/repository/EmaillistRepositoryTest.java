package com.bit2021.emaillist.repository;

import java.util.List;

import com.bit2021.emaillist.vo.EmaillistVo;

public class EmaillistRepositoryTest {
	public static void main(String[] args) {
		//testInsert();
		testSelect();
		
	}

	private static void testSelect() {
		List<EmaillistVo> list = new EmaillistRepository().findAll();
		if(list.size() == 1) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}

	private static void testInsert() {
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName("둘");
		vo.setLastName("리");
		vo.setEmail("dooly@gmail.com");
		
		boolean result = new EmaillistRepository().insert(vo);
		if(result) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}
}
