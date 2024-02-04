package com.sist.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

import java.util.*;
// => 구현된 매퍼를 사용하는 클래스 => 유지보수를 할 경우 => 다른 클래스에 영향
@Repository
public class FoodDAO {
@Autowired
private FoodMapper mapper;

public List<FoodVO> foodListData(int start,int end){
	return mapper.foodListData(start, end);
}
public int foodTotalpage() {
	return mapper.foodTotalpage();
}
public FoodVO foodDetailData(int fno) {
	return mapper.foodDetailData(fno);
}
}
