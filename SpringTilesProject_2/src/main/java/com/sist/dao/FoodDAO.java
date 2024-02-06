package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

import java.util.*;
// 스프링에 관리 요청 => interface / VO(데이터형)
@Repository // id => foodDAO
public class FoodDAO {
@Autowired
private FoodMapper mapper;

public List<FoodVO> foodList(int start, int end){
	return mapper.foodList(start, end);
}
public int foodTotalpage() {
	return mapper.foodTotalpage();
}
public FoodVO foodDetail(int fno) {
	return mapper.foodDetail(fno);
}
}
