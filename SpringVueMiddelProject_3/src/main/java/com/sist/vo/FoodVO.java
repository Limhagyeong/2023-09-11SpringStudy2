package com.sist.vo;
// 사용자정의 데이이터형 => 메모리할당 불필요
import lombok.Data;

@Data
public class FoodVO {
	private int fno;
	private double score; 
	private String poster,name,address,phone,price,time,type,theme,seat,sessionId;
}
