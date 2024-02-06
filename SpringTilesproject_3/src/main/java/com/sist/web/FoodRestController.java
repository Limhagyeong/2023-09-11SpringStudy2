package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.FoodService;
import com.sist.vo.FoodVO;

@RestController
public class FoodRestController {
@Autowired
private FoodService service;
@GetMapping(value="food/list_vue.do",produces = "text/plain;charset=UTF-8")
public String food_list_vue(int page) throws Exception
{
	int rowsize=12;
	int start=(page*rowsize)-(rowsize-1);
	int end=(page*rowsize);
	List<FoodVO> list=service.foodList(start, end);
	
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(list);
	return json;
}
@GetMapping(value="food/page_vue.do",produces = "text/plain;charset=UTF-8")
public String food_page_vue(int page) throws Exception
{
	int totalpage=service.foodTotalpage();
	final int BLOCK=10;
	int startpage=((page-1)/BLOCK*BLOCK)+1;
	int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
	if (endpage>totalpage)
	endpage=totalpage;
	
	Map map=new HashMap();
	map.put("curpage", page);
	map.put("startpage", startpage);
	map.put("endpage", endpage);
	map.put("totalpage", totalpage);
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(map);
	return json;
}
}


