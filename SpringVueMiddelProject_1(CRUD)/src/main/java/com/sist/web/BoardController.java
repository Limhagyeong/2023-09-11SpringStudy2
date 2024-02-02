package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.BoardDAO;
// 화면 변경 용도 => forward(request를 전송) / sendRedirect(재호출)
// 				 경로명/파일명				 redirect: ~.do => request 초기화
@Controller 
public class BoardController {
@GetMapping("board/list.do")
public String board_list()
{
	return "board/list";
}
@GetMapping("board/insert.do")
public String board_insert()
{
	return "board/insert";
}
@GetMapping("board/detail.do")
public String board_detail(int no,Model model)
{
	model.addAttribute("no",no); // no:${no} => jsp에서 el로 받아준다!!
	return "board/detail";
}
@GetMapping("board/update.do")
public String board_update(int no,Model model)
{
	model.addAttribute("no",no);
	return "board/update";
}


}
