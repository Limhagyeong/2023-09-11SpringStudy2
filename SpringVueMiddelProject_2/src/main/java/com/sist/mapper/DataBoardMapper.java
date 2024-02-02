package com.sist.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.DataBoardVO;

import java.util.*;
public interface DataBoardMapper {
 @Select("SELECT no,name,subject,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
 		+ "FROM(SELECT no,name,subject,regdate,hit,ROWNUM as num "
 		+ "FROM(SELECT /*+ INDEX_DESC(vueDataBoard vdb_no_pk)*/no,name,subject,regdate,hit "
 		+ "FROM vueDataBoard)) "
 		+ "WHERE num BETWEEN #{start} AND #{end}")
 public List<DataBoardVO> dataBoardList(@Param("start") int start, @Param("end") int end);
 
 @Select("SELECT CEIL(COUNT(*)/10.0) FROM vueDataBoard")
 public int dataBoardTotalpage();
 
 @Insert("INSERT INTO vueDataBoard VALUES("
 		+ "vdb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},SYSDATE,0,#{filename},#{filesize},#{filecount})")
 public void dataBoardInsert(DataBoardVO vo);
 /*
  *  SELECT => selectList(목록=>list) OR selectOne (상세보기 ==> vo,일반데이터형)
  *  =======
  *  INSERT
  *  UPDATE  => void
  *  DELETE
  *  ======
  */
 @Update("UPDATE vueDataBoard SET hit=hit+1 WHERE no=#{no}")
 public void hitIncrement(int no);
 @Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,filename,filesize,filecount "
 		+ "FROM vueDataBoard "
 		+ "WHERE no=#{no}")
 public DataBoardVO dataBoardDetail(int no);
}
