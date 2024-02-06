package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainContoller {
	// => tiles에서 실행하면 안됨 ==> 아래 경로로 찾게 해주깅
	/* <bean id="viewResolver"
	 * class="org.springframework.web.servlet.view.InternalResourceViewResolver"
	 * p:prefix="/" p:suffix=".jsp" p:order="1" />
	 */
	@GetMapping("chat/chat.do")
	public String chat_chat()
	{
		return "site/chat/chat";
	}
}
