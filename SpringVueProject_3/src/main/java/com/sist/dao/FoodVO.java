package com.sist.dao;

import lombok.Data;

@Data
public class FoodVO {
	private int fno;
	private double score; 
	private String poster,name,address,phone,price,time,type,theme,seat;
}
