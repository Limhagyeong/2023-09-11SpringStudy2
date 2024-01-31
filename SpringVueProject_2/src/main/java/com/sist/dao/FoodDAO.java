package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodListData(){
		return mapper.foodListData();
	}
	public FoodVO foodDetailList(int fno) {
		return mapper.foodDetailList(fno);
	}
}
