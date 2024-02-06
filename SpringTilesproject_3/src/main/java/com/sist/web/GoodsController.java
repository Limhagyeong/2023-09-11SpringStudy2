package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.service.GoodsService;
import com.sist.vo.GoodsVO;

@Controller
public class GoodsController {
	@Autowired
	private GoodsService service;
	
	private String[] tables= {"","goods_all","goods_best","goods_special","goods_new"};
 
	@GetMapping("goods/goods_main.do")
   public String goods_main(String page,String type,Model model)
   {
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   
	   if(type==null)
		   type="1";
	   
	   int rowsize=12;
		int start=(curpage*rowsize)-(rowsize-1);
		int end=(curpage*rowsize);
		
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("table_name", tables[Integer.parseInt(type)]);
		List<GoodsVO> list=service.goodsList(map);
		int totalpage=service.goodsTotalpage(map);
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if (endpage>totalpage)
		endpage=totalpage;
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("type",type);
		
		return "goods";
   }
	@GetMapping("goods/goods_detail.do")
	public String goods_detail(int no,int type,Model model)
	{
		Map map=new HashMap();
		map.put("no", no);
		map.put("table_name", tables[type]);
		GoodsVO vo=service.goodsDetailList(map);
		model.addAttribute("vo",vo);
		return "goods/goods_detail";
	}
}