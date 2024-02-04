package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.FoodDAO;
// 타시스템과 연결 => 자바스크립트와 연결 (Ajax , Vue, React) => JSON / 일반 문자열 전송
//  @RestController => 클래스형
// simple-json => 직접 JSON을 만드는 과정 => 자동 완성 (jackson)
// jackson => JSON (자바스크립트 객체 표현법) {"키":값,..} => 키를 멤버변수로 인식 (중복 불가)
/*
 * <<같은개념>>
 * 	오라클 : Row , 자바 : 객체 , 자바스크립트 : {}
 */
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.service.*;
import com.sist.vo.FoodVO;
@RestController
public class FoodRestController {
@Autowired
private FoodService service;

@GetMapping(value="food/list_vue.do",produces="text/plain;charset=UTF-8")
public String food_list_vue(int page) throws Exception
{
	int rowsize=12;
	int start=(page*rowsize)-(rowsize-1);
	int end=(page*rowsize);
	List<FoodVO> list=service.foodListData(start, end);
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(list);
	return json;
}
@GetMapping(value="food/page_vue.do",produces="text/plain;charset=UTF-8")
public String page_vue(int page) throws Exception
{
	int totalpage=service.foodTotalpage();
	final int BLOCK=10;
	int startpage=((page-1)/BLOCK*BLOCK)+1;
	int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
	
	if(endpage>totalpage)
		endpage=totalpage;
	
	Map map=new HashedMap();
	map.put("startpage", startpage);
	map.put("endpage", endpage);
	map.put("totalpage", totalpage);
	map.put("curpage", page);
	
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(map);
	return json;
}
@GetMapping(value="food/detail_vue.do",produces="text/plain;charset=UTF-8")
public String food_detail_vue(int fno,HttpSession session) throws Exception
{
	String id=(String)session.getAttribute("id");
	FoodVO vo=service.foodDetailData(fno);
	String sId="";
	if(id==null)
		sId="";
	
	else {
		sId=id;
	}
	vo.setSessionId(sId); // 임시 저장 => 댓글 사용 => 수정/삭제
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(vo);
	return json;
}
}
