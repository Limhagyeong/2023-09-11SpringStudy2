package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDAO dao;

	@Override
	public List<GoodsVO> goodsList(Map map) {
		// TODO Auto-generated method stub
		return dao.goodsList(map);
	}

	@Override
	public int goodsTotalpage(Map map) {
		// TODO Auto-generated method stub
		return dao.goodsTotalpage(map);
	}

	@Override
	public GoodsVO goodsDetailList(Map map) {
		// TODO Auto-generated method stub
		return dao.goodsDetailList(map);
	}

}
