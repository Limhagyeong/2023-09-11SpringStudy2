package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

@Service
public class FoodServiceImpl implements FoodService{

	@Autowired
	private FoodDAO dao;
	
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

}
