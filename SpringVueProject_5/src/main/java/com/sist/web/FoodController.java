package com.sist.web;

import java.util.*;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

@Controller
public class FoodController {
	@Autowired
	public FoodDAO dao;
	
	@GetMapping("food/list.do")
	public String food_list()
	{
		return "food/list";
	}
	
	@GetMapping(value="food/list_vue.do",produces="text/plain;charset=UTF-8")
	@ResponseBody 
	// @ResponseBody(메소드) => @RestController(클래스) ==> 데이터만 보냄 (페이지 이동 X)
	public String food_list_vue(int page)
	{
		// vuejs => 연결 전에 초기값 설정이 가능하다
		/*
		 * data(){
		 * 	return{
		 * 		page:1
		 * 	}
		 * }
		 */
		int rowsize=20;
		int start=(rowsize*page)-(rowsize-1);
		int end=(rowsize*page);
		Map map=new HashedMap();
		map.put("start", start);
		map.put("end", end);
		List<FoodVO> list=dao.foodListData(map);
		int totalPage=dao.foodTotalPage();
		
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalPage)
			endPage=totalPage;
		int i=0;
		JSONArray arr=new JSONArray(); // => [] : list / JSONObject => {} : vo
		for(FoodVO vo:list)
		{
			JSONObject obj=new JSONObject(); // vo를 담는 객체
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("poster", vo.getPoster()); // {"fno":1,"name":'',"poster":''}
			if(i==0)
			{
				obj.put("curpage", page);
				obj.put("totalPage", totalPage);
				obj.put("startPage", startPage);
				obj.put("endPage", endPage);
			}
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}
	@GetMapping("food/page_vue.do")
	@ResponseBody 
	public String food_page_vue(int page)
	{
		
		return "";
	}
}
