package com.bit2021.aoptest.service;

import org.springframework.stereotype.Service;

import com.bit2021.aoptest.vo.ProductVo;

@Service
public class ProductService {
	public ProductVo find(String name) {
		System.out.println("[finding]");
//		if(1-1 == 0) {
//			throw new RuntimeException("Find Exception");
//		}
		return new ProductVo(name);
	}
}
