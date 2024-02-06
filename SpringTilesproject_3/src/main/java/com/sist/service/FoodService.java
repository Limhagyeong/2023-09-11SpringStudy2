package com.sist.service;

import java.util.List;

import com.sist.vo.FoodVO;

public interface FoodService {
	 public List<FoodVO> foodList(int start,int end);
	 public int foodTotalpage();
}
