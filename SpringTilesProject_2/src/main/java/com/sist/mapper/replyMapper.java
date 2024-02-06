package com.sist.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.ReplyVO;

import java.util.*;
public interface replyMapper {
	@Select("SELECT rno,fno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD') as dbday FROM vueReply "
			+ "WHERE fno=#{fno} ORDER BY rno DESC")
	public List<ReplyVO> replyListData(int fno);
	@Insert("INSERT INTO vueReply VALUES("
			+ "vr_rno_seq.nextval,#{fno},#{id},#{name},#{msg},SYSDATE)")
	public void replyInsert(ReplyVO vo);
	
	// 수정
	@Update("UPDATE vueReply SET msg=#{msg} WHERE rno=#{rno}")
	public void replyUpdate(ReplyVO vo);
	// 삭제
	@Delete("DELETE FROM vueReply WHERE rno=#{rno}")
	public void replyDelete(int rno);
}
