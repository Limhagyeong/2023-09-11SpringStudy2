package com.sist.mapper;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.FoodVO;

import java.util.*;
public interface FoodMapper {
@Select("SELECT fno,name,poster FROM food_menu_house WHERE fno BETWEEN 108 AND 127 ORDER BY fno ASC")
public List<FoodVO> foodListData();
@Select("SELECT fno,name,poster,score,type,address,theme,price,time,seat FROM food_menu_house WHERE fno=#{fno}")
public FoodVO foodDetailList(int fno);
}
