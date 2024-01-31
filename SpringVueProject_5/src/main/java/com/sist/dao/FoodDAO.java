package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import java.util.*;
import com.sist.mapper.*;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	public List<FoodVO> foodListData(Map map){
		return mapper.foodListData(map);
	}
	
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
}
