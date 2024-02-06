package com.sist.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.MemberDAO;
import com.sist.service.FoodService;
import com.sist.vo.FoodVO;
import com.sist.vo.MemberVO;
import com.sist.vo.ReplyVO;

@RestController
public class MainRestController {
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
public String page_vue(int page) throws Exception
{
	int totalpage=service.foodTotalpage();
	final int BLOCK=10;
	int startpage=((page-1)/BLOCK*BLOCK)+1;
	int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
	if(endpage>totalpage)
		endpage=totalpage;
	
	Map map=new HashedMap();
	map.put("totalpage",totalpage);
	map.put("startpage", startpage);
	map.put("endpage", endpage);
	map.put("curpage", page);
	
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(map);
	return json;
}
@GetMapping(value="food/detail_vue.do",produces = "text/plain;charset=UTF-8")
public String food_detail_vue(int fno,HttpSession session) throws Exception
{
	String id=(String)session.getAttribute("id");
	FoodVO vo=service.foodDetail(fno);
	vo.setSessionId(id);
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(vo);
	return json;
}
@GetMapping(value="member/login_vue.do",produces = "text/plain;charset=UTF-8")
public String member_login_vue(String id, String pwd,HttpSession session)
{
	MemberVO vo=service.isLogin(id, pwd);
	if(vo.getMsg().equals("OK"))
	{
		session.setAttribute("id", vo.getId());
		session.setAttribute("name", vo.getName());
	}
	return vo.getMsg();
}
@GetMapping(value="member/logout_vue.do",produces = "text/plain;charset=UTF-8")
public void member_logout_vue(HttpSession session)
{
	session.invalidate();
}

// => 수정, 삭제된 데이터 정보를 가지고옴
public String commonsData(int fno) throws Exception
{
	List<ReplyVO> list=service.replyListData(fno);
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(list);
	return json;
}

@GetMapping(value="reply/list_vue.do",produces = "text/plain;charset=UTF-8")
public String reply_list_vue(int fno) throws Exception
{
	return commonsData(fno);
}

@GetMapping(value="reply/insert_vue.do",produces = "text/plain;charset=UTF-8")
public String reply_insert(int fno,String msg,HttpSession session) throws Exception
{
	String id=(String)session.getAttribute("id");
	String name=(String)session.getAttribute("name");
	ReplyVO vo=new ReplyVO();
	vo.setFno(fno);
	vo.setId(id);
	vo.setName(name);
	vo.setMsg(msg);
	service.replyInsert(vo);
	return commonsData(fno); // => detail.do 새로 실행되는게 아님! 댓글 정보만 새롭게 업데이트됨
}
@GetMapping(value="reply/delete_vue.do",produces = "text/plain;charset=UTF-8")
public String reply_delete_vue(int rno,int fno) throws Exception
{
	service.replyDelete(rno);
	return commonsData(fno);
}
@GetMapping(value="reply/update_vue.do",produces = "text/plain;charset=UTF-8")
public String reply_update_vue(ReplyVO vo) throws Exception
{
	service.replyUpdate(vo);
	return commonsData(vo.getFno());
}
}
