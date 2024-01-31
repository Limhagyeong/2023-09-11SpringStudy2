package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.GoodsVO;

public interface GoodsMapper {
@Select("SELECT no,goods_name,goods_poster,num "
		+ "FROM(SELECT no,goods_name,goods_poster,ROWNUM as num "
		+ "FROM(SELECT no,goods_name,goods_poster "
		+ "FROM goods_all ORDER BY no ASC)) "
		+ "WHERE num BETWEEN #{start} AND #{end}")
public List<GoodsVO> goodsListData(Map map);

@Select("SELECT CEIL(COUNT(*)/20.0) FROM goods_all")
public int totalPage();
}
