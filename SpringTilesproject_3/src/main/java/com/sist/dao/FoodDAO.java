package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class FoodDAO {
   @Autowired
   private FoodMapper mapper;
   
   public List<FoodVO> foodList(int start,int end)
   {
      return mapper.foodList(start, end);
   }
   public int foodTotalpage()
   {
      return mapper.foodTotalpage();
   }
}