package com.sist.chat;
import java.util.*;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
@ServerEndpoint("/site/chat/chat-ws") // => 접속 지점
/*
 * 	function connection() {
	websocket=new WebSocket("ws://localhost:8080/web/site/chat/chat-ws")
												    ==================== 일치돼야됨!
}
 */
public class ChatServer {
	// 저장 => 접속자 정보 저장
	//private static List<Session> users=new ArrayList<Session>();
	private static List<Session> users=Collections.synchronizedList(new ArrayList<Session>());
	// websocket에 존재하는 session (접속자의 정보 저장되어있음)
	// 클라이언트가 접속을 했을 때 호출되는 메소드
	@OnOpen
	public void onOpen(Session session)
	{
		users.add(session);
		System.out.println("클라이언트 접속:"+session.getId()); // => 숫자 표현
	}
	// 클라이언트가 퇴장을 했을 때 호출되는 메소드
	@OnClose
	public void onClose(Session session)
	{
		users.remove(session);
		System.out.println("클라이언트 퇴장:"+session.getId());
	}
	// 채팅 => 문자열 전송
	@OnMessage
	public void onMessage(String message,Session session) throws Exception
	{
		System.out.println(session.getId()+"님의 메세지:"+message);
		
		//방법 1
		/*Iterator<Session> iter=users.iterator(); // => 접속한 모든사람
		while(iter.hasNext()) 
		{
			iter.next().getBasicRemote().sendText(message);
		}*/
		
		//방법 2
		for(Session s:users)
		{
			s.getBasicRemote().sendText(message);
		}
	}
}
