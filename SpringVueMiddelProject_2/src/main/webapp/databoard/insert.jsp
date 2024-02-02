<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="http://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
margin-top: 50px;
}
.row{
margin: 0px auto;
width: 960px;
}
</style>
</head>
<body>
	<div class="container">
		<h3 class="text-center">글쓰기</h3>
		<div class="row">
		<%--
			form => 기본 기능 (서버로 데이터 전송)
				 => 화면 reset
				 => 기능을 동작하지 못하게 만든다 (@submit.prevent)
		--%>
		<form @submit.prevent="submitForm">
		<table class="table">
		<tr>
		<th width="15%" class="text-center">이름</th>
		<td width="85%">
		<input type="text" size="15" class="input-sm" v-model="name" ref="name">
		</td>
		</tr>
		<tr>
		<th width="15%" class="text-center">제목</th>
		<td width="85%">
		<input type="text" size="15" class="input-sm" v-model="subject" ref="subject">
		</td>
		</tr>
		<tr>
		<th width="15%" class="text-center">내용</th>
		<td width="85%">
		<textarea rows="10" cols="52" v-model="content" ref="content"></textarea>
		</td>
		</tr>
		<tr>
		<th width="15%" class="text-center">첨부파일</th>
		<td width="85%">
			<input type="file" ref="upfiles" class="input-sm" multiple accept="upload/*"
			v-model="upfiles">
		</td>
		</tr>
		<tr>
		<th width="15%" class="text-center">비밀번호</th>
		<td width="85%">
		<input type="password" size="15" class="input-sm" v-model="pwd" ref="pwd">
		</td>
		</tr>
		<tr>
		
		<td colspan="2" class="text-center">
			<input type="submit" value="글쓰기" class="btn-sm btn-success">
			<input type="button" value="취소" onclick="javascript:history.back()"
			class="btn-sm btn-danger">
		</td>
		</tr>
		</table>
		</form>
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			// 멤버변수 설정
			data(){
				return{
					name:'',
					subject:'',
					content:'',
					pwd:'',
					upfiles:''
				}
			},
			// 멤버함수 설정
			methods:{
				// <form=@submit.prevent="submitForm"/>
				// submit버튼 호출 시 => 데이터 전송 없이 아래 메소드를 호출
				// submit / a 의 기능을 없앨 때 => defaultPrevent => 이벤트 동작 중지
				submitForm(){
					// NOT NULL 유효성 검사
					// this.$refs.ref명.focus() ==> ref: 태그 제어 시 사용 
					if(this.name==='')
					{
						this.$refs.name.focus()
						return
					}
					if(this.subject==='')
					{
						this.$refs.subject.focus()
						return
					}
					if(this.content==='')
					{
						this.$refs.content.focus()
						return
					}
					if(this.pwd==='')
					{
						this.$refs.pwd.focus()
						return
					}
					
					// FormData() => form에있는 모든 데이터 가져옴
					let form=new FormData()
					form.append("name",this.name)
					form.append("subject",this.subject)
					form.append("content",this.content)
					form.append("pwd",this.pwd)
					
					let leng=this.$refs.upfiles.files.length
					//                          ======
					// DataBoardVo => private List<MultipartFile> files;
					//alert(leng)
					if(leng>0)
					{
						for(let i=0;i<leng;i++)
						{
							form.append("files["+i+"]",this.$refs.upfiles.files[i])
						}
					}
					axios.post("../databoard/insert_vue.do",form,{
						header:{
							'Content-Type':'multipart/form-data'
						}
					}).then(res=>{
						if(res.data==="yes")
						{
							location.href="../databoard/list.do"
						}
						else
						{
							alert(res.data)
						}
					}).catch(error=>{
						console.log(error.res)
					})
				}
				// 서버로 전송
			},
			// callback => vue에 의해 호출되는 함수
			mounted(){
				// 시작과 동시에 처리
			},
			updated(){
				// 데이터 갱신 시 호출 => component를 만든 경우 처리 ***
			}
		}).mount(".container")
	</script>
</body>
</html>