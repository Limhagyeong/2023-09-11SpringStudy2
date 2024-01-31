package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.FoodDAO;
// 파일명만 전송이 가능
@Controller
public class FoodController {
	
	@GetMapping("food/list.do")
	public String food_list()
	{	
		return "food/list"; // forward / sendRedirect
	}
}
