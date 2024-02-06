package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.GoodsVO;

public interface GoodsService {
	public List<GoodsVO> goodsList(Map map);
	public int goodsTotalpage(Map map);
	public GoodsVO goodsDetailList(Map map);
}
