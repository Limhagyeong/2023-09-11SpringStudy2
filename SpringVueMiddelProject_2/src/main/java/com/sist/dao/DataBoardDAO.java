package com.sist.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.DataBoardMapper;
/*
 * 	선택적 메모리 할당
 *  ---------------------------- 기능별로 분리
 *  => 
 *  	@Component : AOP, MainClass, OpenAPI => 일반 클래스에 올라감
 *  	@Repository : DAO => 저장소 (데이터베이스 연결)
 *  	@Service : BI => DAO 여러개를 통합 (기능통합) => 
 *  			   BoardDAO / ReplyDAO => 기능별로 클래스 만든다 (재사용 목적)
 *  	@Controller : model (요청 처리 => 응답) => 페이지 설정
 *  	@RestController : Model (요청 처리 => 응답) => 페이지 설정 X ==> 처리된 데이터만 전송
 *  	 ==== 다른 프로그램과 연동 (자바스크립트) => JSON
 *  	 Front / Back
 *  	@ControllerAdvice : Controller에서 오류 발생 시 예외처리 (공통)
 *  	@RestControllerAdvice : RestController에서 오류 발생 시 예외처리 (공통)
 *  	@Configuration : XML 대신 클래스 설정을 자바로 변경
 */
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
	 public List<DataBoardVO> dataBoardList(int start,int end){
		 return mapper.dataBoardList(start, end);
	 }
	 
	 public int dataBoardTotalpage() {
		 return mapper.dataBoardTotalpage();
	 }
	 
	 public void dataBoardInsert(DataBoardVO vo) {
		 mapper.dataBoardInsert(vo);
	 }
	 public DataBoardVO dataBoardDetail(int no) {
		 mapper.hitIncrement(no);
		 return mapper.dataBoardDetail(no);
	 }
}
