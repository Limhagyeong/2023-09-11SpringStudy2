package com.sist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

@RestController
public class FoodRestController {
	@Autowired
	public FoodDAO dao;
	
	@GetMapping(value="food/find.do",produces="text/plain;charset=UTF-8")
	public String food_find(String address) throws Exception
	{
		List<FoodVO> list=dao.foodFindData(address);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
}
