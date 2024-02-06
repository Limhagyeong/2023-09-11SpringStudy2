package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MemberMapper;
import com.sist.vo.MemberVO;

import java.util.*;

@Repository
public class MemberDAO {
@Autowired
private MemberMapper mapper;

public MemberVO isLogin(String id,String pwd) {
	MemberVO vo=new MemberVO();
	
	int count=mapper.idCount(id);
	if(count==0)
	{
		vo.setMsg("NOID");
	}
	else
	{
		MemberVO dbVO=mapper.isLogin(id);
		if(pwd.equals(dbVO.getPwd()))
		{
			vo.setId(dbVO.getId());
			vo.setName(dbVO.getName());
			vo.setMsg("OK");
		}
		else
		{
			vo.setMsg("NOPWD");
		}
	}
	return vo;
}
}
