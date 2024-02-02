package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

import oracle.jdbc.proxy.annotation.Post;

//자바 스크립트 연동 => 데이터를 받아서 처리후에 결과값 전송 => Rest (타 시스템과 연결됨) => Get/Post=> <<웹에서 사용되는 rest>>
//																		Put/Delete <<사용X>>
//@Responsebody가 변경됨
import java.util.*;
@RestController
public class BoardRestController {
	@Autowired
	public BoardDAO dao;
	@GetMapping(value="board/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String board_list_vue(int page) throws Exception
	{
		int rowsize=10;
		int start=(page*rowsize)-(rowsize-1);
		int end=(page*rowsize);
		List<BoardVO> list=dao.boardListData(start, end);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list); // jackson
		return json;
	}
	@GetMapping(value="board/page_vue.do",produces = "text/plain;charset=UTF-8")
	public String board_page_vue(int page) throws Exception
	{
		Map map=new HashedMap();
		int totalpage=dao.boardTotalPage();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	@PostMapping("board/insert_ok.do")
	public void board_insert_ok(BoardVO vo)
	{
		dao.boardInsert(vo);
//		String url="<script>"
//				+ "location.href=\"list.do\""
//				+ "</script>";
//		return url;
		// return "redirect: ../board/list.do"; ==> 일반 문자열로 넘어감 => 화면변경용도로 사용 불가!!!
	}
	/*
	 *  @RestController
	 *  class A
	 *  {
	 *  	@GetMapping()
	 *  	public List<FoodVO> listdata()
	 *  	{
	 *  		List<FoodVO> list=dao.getList();
	 *  		return list;	
	 *  	}
	 *  }
	 */
	@GetMapping(value="board/detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String board_detail_vue(int no) throws Exception
	{
		 // no:${no} => jsp에서 el로 받아준다!!
		BoardVO vo =dao.boardDetailData(no); 
//		System.out.println(no);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo); // jackson
//		System.out.println(json);
		return json;
	}
	@GetMapping(value="board/update_vue.do",produces = "text/plain;charset=UTF-8")
	public String board_update_vue(int no) throws Exception
	{
		BoardVO vo=dao.boardUpdateData(no);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	 @PostMapping(value="board/update_ok.do",produces = "text/plain;charset=UTF-8")
	  public String board_update_ok(BoardVO vo)
	  {
		  String result=dao.boardUpdate(vo);
		  return result;
	  }
	 @GetMapping(value="board/delete_vue.do",produces = "text/plain;charset=UTF-8")
	 public String board_delete_ok(int no,String pwd)
	 {
		 String result=dao.boardDelete(no, pwd);
		 return result;
	 }
}
