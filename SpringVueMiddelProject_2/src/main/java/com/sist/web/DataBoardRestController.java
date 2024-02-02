package com.sist.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.DataBoardDAO;
import com.sist.vo.DataBoardVO;
// Vue와 데이터 통신
// Vue / React ==> Router => 데이터 송수신 , 화면변경 (controller) => Spring기반
@RestController
public class DataBoardRestController {
@Autowired
private DataBoardDAO dao;

@GetMapping(value="databoard/list_vue.do",produces = "text/plain;charset=UTF-8")
public String dataBoardList_vue(int page) throws Exception
{
	int rowsize=10;
	int start=(page*rowsize)-(rowsize-1);
	int end=(page*rowsize);
	List<DataBoardVO> list=dao.dataBoardList(start, end);

	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(list);
	
	return json;
}
@GetMapping(value="databoard/page_vue.do",produces = "text/plain;charset=UTF-8")
public String dataBoard_page_vue(int page) throws Exception
{
	int totalpage=dao.dataBoardTotalpage();
	Map map=new HashMap();
	map.put("curpage", page);
	map.put("totalpage", totalpage);
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(map); // {curpage:1,totalpage:10}
	return json;
}
@PostMapping(value="databoard/insert_vue.do",produces = "text/plain;charset=UTF-8")
public String databoard_insert_ok(DataBoardVO vo,HttpServletRequest request)
{
	String result="";
	try
	{
		String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
		path=path.replace("\\", File.separator); // File.separator => 운영체제에따라 경로 구분자 자동 변환됨 (호환성)
		// 호스팅(AWS) 운영체제 => 리눅스!
		File dir=new File(path);
		if(!dir.exists())
		{
			dir.mkdir(); // => 없다면 자동 업로드 폴더 생성
		}
		
		List<MultipartFile> list=vo.getFiles(); // => 임시기억
		if(list==null) // 업로드가 없는 상태
		{
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		}
		else // 업로드가 있는 상태
		{
			String filename="";
			String filesize="";
			for(MultipartFile mf:list)
			{
				String name=mf.getOriginalFilename();
				File file=new File(path+name);
				mf.transferTo(file); // => 파일을 가져와서 업로드함
				
				filename+=name+","; // a.jpg,b.jpg,...,y.jpg, => 마지막 , 제거
				filesize+=file.length()+",";	
			}
			filename=filename.substring(0,filename.lastIndexOf(","));
			filesize=filesize.substring(0,filesize.lastIndexOf(","));
			vo.setFilename(filename);
			vo.setFilesize(filesize);
			vo.setFilecount(list.size());
		}
		dao.dataBoardInsert(vo);
		result="yes"; // 업로드 성공
	}
	catch(Exception ex) 
	{
		result=ex.getMessage();
	}
	return result;
}
@GetMapping(value="databoard/detail_vue.do",produces = "text/plain;charset=UTF-8")
public String dataBoardDetail_vue(int no) throws Exception
{
	/*
	 *  res.data={"no":1,"name":'',...,filename:''}
	 */
	DataBoardVO vo=dao.dataBoardDetail(no);
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(vo);
	
	return json;
}
@GetMapping("databoard/download.do")
public void databoard_download(String fn,HttpServletRequest request, HttpServletResponse response)
{
	String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
	path.replace("\\", File.separator);
	try
	{
		File file=new File(path+fn);
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fn,"UTF-8"));
		//										  =========== 다운로드 창을 띄워줌
		response.setContentLength((int)file.length());
		// => 다운로드 창 보여준다
		
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
		// 서버에서 파일을 읽어온다
		BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
		// 다운로드 폴더에 복사뜨기 (클라이언트 복사 영역)
		int i=0;
		byte[] buffer=new byte[1024];
		while((i=bis.read(buffer,0,1024))!=-1)
		{
			// i=> 읽은 바이트 수
			// -1 ==> EOF (end of file)
			bos.write(buffer,0,i);
		}
		bis.close();
		bos.close();
	}
	catch(Exception ex)
	{
		
	}
}
}
