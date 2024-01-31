package com.sist.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.dao.GoodsDAO;
import com.sist.dao.GoodsVO;

@Controller
public class GoodsController {
@Autowired
private GoodsDAO dao;

@GetMapping("goods/list.do")
public String goods_list() {
	return "goods/list";
}
@GetMapping(value="goods/list_vue.do",produces="text/plain;charset=UTF-8")
@ResponseBody
public String goods_list_vue(int page)
{
	int rowsize=20;
	int start=(page*rowsize)-(rowsize-1);
	int end=(page*rowsize);
	Map map=new HashedMap();
	map.put("start", start);
	map.put("end", end);
	List<GoodsVO> list=dao.goodsListData(map);
	
	int totalPage=dao.totalPage();
	final int BLOCK=10;
	int startPage=((page-1)/BLOCK*BLOCK)+1;
	int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
	if(endPage>totalPage)
		endPage=totalPage;
	int i=0;
	JSONArray arr=new JSONArray();
	for(GoodsVO vo:list)
	{
		JSONObject obj=new JSONObject();
		obj.put("no", vo.getNo());
		obj.put("goods_name", vo.getGoods_name());
		obj.put("goods_poster", vo.getGoods_poster());
		if(i==0)
		{
			obj.put("totalPage", totalPage);
			obj.put("endPage", endPage);
			obj.put("startPage",startPage);
			obj.put("curpage", page);
		}
		arr.add(obj);
		i++;
	}
	return arr.toJSONString();
}
}
