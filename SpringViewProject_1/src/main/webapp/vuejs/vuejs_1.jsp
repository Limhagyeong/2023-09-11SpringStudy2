<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<!--<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function () {
	$('#msg').keyup(function(){
		$('#print').text($('#msg').val())
	})
})
</script> -->
<style type="text/css">
.container{
margin-top: 50px;
}
.row{
margin: 0px auto;
width: 800px;
}
</style>
</head>
<%--
		Vue : 1. "가상 돔"을 사용 => 속도를 빠르게 처리 ==> 가상 돔의 개념
					 ==> html로 읽음 (Diff=> 원본 돔에서 변경된 부분만 카피로 추가=> 속도 빠름 // Vuejs, React)		
					 ==> 단점					          
				 mount => 가상 메모리에 저장 ==> 파일 저장하면 브라우저에 바로 연동됨
				 					    ==> 태그 선택이 없다
				 => String / StringBuffer
			   2. 생명주기 => callback
			   3. 디렉티브 => 제어문
			   4. 서버 연동 => axios
			   5. 출력 형식 => {{}} : 텍스트 출력 / :src= : 속성 출력
			   6. 양방향 통신 => v-model
			   7. router => 화면 변경
			   ========================= 기본
			   8. 기타 => vue-bootstrap (다이얼로그 띄우기 ..)
			    
			   1) 생명주기 => vue3(react), vuex(mvc)
			   	  beforeCreate() =>vue 객체 생성 전
			   	  created() => vue 객체 생성됨
			   	  ---------------------- vue 객체 생성
			   	  beforeMount()
			   	  mounted()
			   	  ---------------------- 가상메모리에 HTML을 올린 경우
			   	      => $(function(){}) , window.onload
			   	      => 서버에서 데이터를 읽는다 : 멤버변수에 저장
			   	      => Jquery 연동 시점!
			   	
			   	  beforeUpdate()
			   	  updated
			   	  ---------------------- 데이터 갱신
			   	      => component : Main / Sub => sub에 값을 전송 => $emit (모든 사람에게 값을 전송)
			   	  beforeDestroy()
			   	  destroyed()    
			   	  ---------------------- 메모리 해제
 --%>
<body>
<div class="container">
<div class="row">
<input type="text" size="30" class="input-sm" v-model="msg">
</div>
<div class="row">
<div>{{msg}}</div>
</div>
</div>
<script>
// Vue JS 셋팅
// Vue 객체 생성
let app=Vue.createApp({
	data(){
		// 멤버 변수 설정
		return{
			msg:''		
		}
	},
	// callback 함수 => vue에 의해 자동으로 호출된다 ((생명주기 함수))
	beforeCreate(){
		console.log("Vue 객체 생성 전: beforeCreate() call")
	},
	created(){
		console.log("Vue 객체 생성 완료: created() call")
	},
	beforeMount(){
		console.log("HTML과 데이터 가상 메모리에 올라가기 전 : beforeMount() call")
	},
	mounted(){
		console.log("HTML과 데이터 가상 메모리에 올라간 상태 :  $(function(){}) , window.onload, mounted() call")
	},
	beforeUpdate(){
		console.log("변경 전 : beforeUpdate() call")
	},
	updated(){
		console.log("변경 완료 : updated() call")
	},
	beforeDestory(){
		console.log("Vue 객체 메모리 해제 전 : beforeDestory() call")
	},
	destroyed(){
		console.log("Vue 객체 메모리 해제 완료 : destroyed() call")
	},
	// 사용자 정의 메소드
	methods:{
		
	}
}).mount('.container')
</script>
</body>
</html>