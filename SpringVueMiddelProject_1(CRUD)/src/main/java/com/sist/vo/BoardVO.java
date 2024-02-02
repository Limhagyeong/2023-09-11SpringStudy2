package com.sist.vo;

import lombok.Data;
import java.util.*;
@Data
public class BoardVO {
	private int no,hit;
	private String dbday,name,subject,content,pwd;
	private Date regdate;
}
