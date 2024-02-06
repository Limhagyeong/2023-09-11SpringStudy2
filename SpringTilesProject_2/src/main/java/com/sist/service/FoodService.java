package com.sist.service;

import java.util.List;

import com.sist.vo.FoodVO;
import com.sist.vo.MemberVO;
import com.sist.vo.ReplyVO;

public interface FoodService {
	// Food 관련
	public List<FoodVO> foodList(int start, int end);
	public int foodTotalpage();
	public FoodVO foodDetail(int fno);
	// Reply 관련
	public List<ReplyVO> replyListData(int fno);
	public void replyInsert(ReplyVO vo);
	public void replyDelete(int rno);
	public void replyUpdate(ReplyVO vo);
	//Member 관련
	public MemberVO isLogin(String id,String pwd);
	
}
