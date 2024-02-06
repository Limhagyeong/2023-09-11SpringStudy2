package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.GoodsMapper;
import com.sist.vo.GoodsVO;

@Repository
public class GoodsDAO {
@Autowired
private GoodsMapper mapper;

public List<GoodsVO> goodsList(Map map){
	return mapper.goodsList(map);
}
public int goodsTotalpage(Map map) {
	return mapper.goodsTotalpage(map);
}
public GoodsVO goodsDetailList(Map map) {
	return mapper.goodsDetailList(map);
}
}
