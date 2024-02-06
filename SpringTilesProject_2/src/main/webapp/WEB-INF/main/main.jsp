<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style type="text/css">
  	.container{
  		margin-top: 20px;
  	}
  	.row{
  		margin: 0px auto;
  		width: 960px;
  	}
  </style>
</head>
<body>
	<tiles:insertAttribute name="header"/>
		<div class="container" id="logApp">
			<div class="row">
			<c:if test="${sessionScope.id==null }">
			 	<div class="text-right">
			 		ID:<input type="text" class="input-sm" v-model="id" ref="id">&nbsp;
			 		PWD:<input type="password" class="input-sm" v-model="pwd" ref="pwd">&nbsp;
			 		<input type="button" value="로그인" class="btn-sm btn-primary" @click="login()">
			 	</div>
			 	</c:if>
			 	<c:if test="${sessionScope.id!=null }">
			 	<div class="text-right">
			 		${sessionScope.name }님 로그인중입니다&nbsp;
			 		<input type="button" value="로그아웃" class="btn-sm btn-danger" @click="logout()">
			 	</div>
			 	</c:if>
			</div>
		</div>
	<tiles:insertAttribute name="home"/>
	<script>
	let logApp=Vue.createApp({
		data(){
			return{
				id:'',
				pwd:''
			}
		},
		methods:{
			login(){
				if(this.id==="")
				{
					this.$refs.id.focus()
					return
				}
				if(this.pwd==="")
				{
					this.$refs.pwd.focus()
					return
				}
				
				axios.get("../member/login_vue.do",{
					params:{
						id:this.id,
						pwd:this.pwd
					}
				}).then(res=>{
					if(res.data==="NOID")
					{
						alert("아이디가 존재하지 않습니다")
						this.id=""
						this.pwd=""
						this.$refs.id.focus()
					}
					else if(res.data==="NOPWD")
					{
						alert("비밀번호가 일치하지 않습니다")
						this.pwd=""
						this.$refs.pwd.focus()
					}
					else
					{
						location.href="../main/main.do"
					}	
				})
			},
			logout(){
				axios.get("../member/logout_vue.do").then(res=>{
					location.href="../main/main.do"
				})
			}
		}
	}).mount("#logApp")
	</script>
</body>
</html>