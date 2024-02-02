package com.sist.vo;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
/*
 * 	기본
 *  ===
 *   src
 *    = com.sist.config ==> XML 대신 스프링에 클래스 관계를 설정해준다
 *    						=> 5버전의 핵심 (권장) => 보안! ==> xml은 노출됨 / java파일은 노출 X
 *    					    => 스프링 부트 (포함 => SpringFrameWork) => XML(pom.xml)
 *    						=> 순수 자바로만 설정 (어노테이션**)
 *    						   <context:component-scan>
 *    							=> @ComponentScan()
 *    							<mybatis-spring>
 *    							=> @MapperScan()
 *    							<bean>
 *    							=> @Bean
 *    = com.sist.dao ==> 데이터베이스 연결
 *    = com.sist.service ==> DAO 통합 => interface(결합성을 낮게)
 *    = com.sist.vo ==> 사용자정의 데이터형 만듦
 *    = com.sist.web ==> Model (요청 => 응답) / default폴더
 *    = com.sist.intercepter ==> Model연결 전 / JSP 연결 전
 *    							 preHandle()  postHandle()
 *    							 => 자동 로그인  => 권한확인
 *    = com.sist.aop ==> 공통 모듈을 모아서 관리
 *    = com.sist.manager ==> 오픈 API 이용 : 실시간 외부 뉴스 읽기 .. 
 *    = com.sist.commons ==> 공통 예외 처리 (모든 컨트롤러에 예외처리)
 *    = com.sist.security ==> 보안 : 암호화 , 복호화 , 권한 , 메소드 보안
 *    = com.sist.chat ==> 실시간 상담
 *    
 *    => 모든 패키지의 메모리 할당 요청 ==> 스프링 (클래스 관리) => 생성 / 소멸
 *    												  --------
 *    												  1. 스프링에 클래스 메모리 주소를 요청
 *    													 =>@Autowired
 *    => 서블릿 (자바로 만들어진 웹파일)
 *    	 server + let
 *    	 => 서버에서 실행하는 가벼운 프로그램
 *    	 => 서블릿을 실행하는 대상 => 톰캣!! (request / response)
 *    	   =============================================> 웹서버의 역할 
 *    		=> 서블릿은 무조건 톰캣에 등록
 *    					  ========
 *    					  web.xml OR @WebServlet()
 *    					  =======    ==============> DispatcherSevlet (X)
 *    					   => DispatcherSevlet (O)
 *    		=> DispatcherSevlet 등록함과 동시에 아래 자동 생성됨 <<XML로 환경 설정 시!>>
 *    			=> WebApplicationContext (1)
 *    			=> HandlerMapping (2) ==> 컨트롤러를 찾아줌
 *    			=> but, annotation은 직접 생성해줘야됨 <자바로 환경 설정 시>>
 *    
 *    			=> URL에 따라서 => 톰캣이 관리	
 *    
 *    		list.do =======================>DispatcherServlet
 *    				톰캣(request,response)
 *    						public void service(request,response)
 *    						{
 *    							=> model을 찾아줌 ==> HandlerMapping
 *    								=> 기능 수행 (메소드) => 구분자 @GetMapping / @PostMapping
 *    							=> return을 읽어온다
 *    							=> JSP 찾기 ==> ViewResolver (경로명)
 *    							=> request전송
 *    						}
 */
@Data
public class DataBoardVO {
private int no,hit,filecount;
private String name,subject,content,filename,filesize,dbday,pwd;
private Date regdDate;
private List<MultipartFile> files; // => 여러개의 데이터 보내기 위함
}
