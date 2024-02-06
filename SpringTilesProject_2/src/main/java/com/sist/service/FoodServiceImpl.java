package com.sist.service;

import java.util.List;

import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.FoodDAO;
import com.sist.dao.MemberDAO;
import com.sist.dao.ReplyDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.MemberVO;
import com.sist.vo.ReplyVO;

@Service
public class FoodServiceImpl implements FoodService{
@Autowired
private FoodDAO dao;

@Autowired
private ReplyDAO rDao;

@Autowired
private MemberDAO mDao;

@Override
public List<FoodVO> foodList(int start, int end) {
	// TODO Auto-generated method stub
	return dao.foodList(start, end);
}

@Override
public int foodTotalpage() {
	// TODO Auto-generated method stub
	return dao.foodTotalpage();
}

@Override
public FoodVO foodDetail(int fno) {
	// TODO Auto-generated method stub
	return dao.foodDetail(fno);
}

@Override
public MemberVO isLogin(String id, String pwd) {
	// TODO Auto-generated method stub
	return mDao.isLogin(id, pwd);
}

@Override
public List<ReplyVO> replyListData(int fno) {
	// TODO Auto-generated method stub
	return rDao.replyListData(fno);
}

@Override
public void replyInsert(ReplyVO vo) {
	// TODO Auto-generated method stub
	rDao.replyInsert(vo);
}

@Override
public void replyDelete(int rno) {
	// TODO Auto-generated method stub
	rDao.replyDelete(rno);
}

@Override
public void replyUpdate(ReplyVO vo) {
	// TODO Auto-generated method stub
	rDao.replyUpdate(vo);
}

}
