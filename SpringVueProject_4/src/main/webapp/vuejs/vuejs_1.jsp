<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--vue: Evan you(구글 = > angularjs= > 복잡하다) 
       IBM = > OS2,MS도스창
       | 단순한 프레임워크 ( VUEJS=>1.단수놔다 ,가벼운 프레임워크
                          2.데이터를 효율적으로처리
                          3.속도가 빠르다
                          4.코드의 재사용이 가능 
                          5.컴포넌트 기반
                     => 전자상 거래, 대시보드, 블로그, 뉴스 사이트 
       | 사용 : MVVM
       		   M : Model => 데이터 저장 => data()
       		   V : View => 화면 출력
       		   				{{}} , v-for , v-model , v-if , v-show , v-if v-else
       		   VM : ViewModel => 상태(데이터 관리, 연산처리) 
       		   					 생명주기
       		   					 1. mounted : onLoad
       		   					 2. updated : 수정
       		   					 3. 사용자 정의 
       		   					 	methods:{
       		   					 		=> 이벤트 처리
       		   					 	}
       		   					 4. components:{
       		   					 		=> 기능 : 이미지 카드 만들기, 애니메이션 ==> 재사용 가능
       		   					 	}
       		   					 5. filter : 10,000
       		   					    => computed : 계산된 경우
       		   -------------------------------------------------------------
       		   class A
       		   {
       		   		List<B> list= // 멤버변수 영역 ==> 선언,초기화만 가능함!!!
       		   }
       		   1. vue 객체 생성 => 여러개 생성 가능
       		     --------
       		     | 범위 지정 ==> mount('태그명,클래스명,ID명')
       		     
       		     let app=Vue.createApp({
       		     	------------------
       		     		 Model => 데이터 관리
       		     		 data(){
       		     		 	return{
       		     		 		변수설정 <<멤버변수 => this.~>> 선언,초기화만 가능함!!!
								fno:0, NUMBER
								fd:'', String
								list:[], Array
								obj:{}, Object ==> 자바스크립트 객체
								isShow:true, boolean
       		     		 	} ==> 선언, 초기화만 가능 : 서버 (Spring / NodeJS) 읽기 불가능
       		     		 }	
       		     	------------------
       		     		ViewModel => 데이터 처리
       		     		1) 변수의 초기화
       		     			=> 서버나 파일
       		     			=> callback함수 => vue동작 시 자동 호출
       		     			=> componentDidMount()
       		     			mounted(){
       		     				서버나 파일 읽기 => data에 저장된 변수에 초기화
       		     				===
       		     				axios.get("서버URL",{
       		     								<서버로 요청하는 데이터 설정> ==> ?params
       		     								params:{
       		     									fno:1,
       		     									id:'admin'
       		     								}
       		     				}).then(res<결과값을받음>=>{
       		     					멤버변수에 대입
       		     				})
       		     				axios.post("서버URL",{
       		     								<서버로 요청하는 데이터 설정>
       		     									fno:1,
       		     									id:'admin'
       		     				}).then(res<결과값을받음>=>{
       		     					멤버변수에 대입
       		     				})
       		     			}
       		     	------------------
       		     		사용자정의 메소드 => 이벤트 (버튼 클릭, 마우스 오버, 키 이벤트 ..)
       		     		methods:{
       		     			btnClick(){
       		     			
       		     			},
       		     			mouseClick(){
       		     			
       		     			}
       		     		}
       		     		재사용 목적
       		     		components{
       		     			template:'<div></div>'
       		     		}
       		     })
       		     
       		     ====================================
       		     화면 출력
       		     
       		     출력 형식
       		     <div>{{data()에 설정된 변수명}}</div> => text() $().text("")
       		     <div :data-no="fd"> => :속성명="변수명"
       		     디렉티브
       		     => v-for="vo in 배열"     ==> v-for"(vo,index) in 배열" <for-each>
       		     	v-if="true/false"
       		     	v-show="true/false" => display:none , display:''
       		     	v-if v-else 
       		     	v-if v-elseif ~ v-else
       		     	입력값 => 멤버변수에 전송 => v-model="멤버변수설정"
       		     => 이벤트
       		     	v-on:click => @click
       		     	v-on:change => @change
       		     	v-on:keyup => @keyup.enter , space ..
       		     	v-on:keydown => @keydown.enter , space ..
       		     => 프로그램
       		     	=> 반복수행을 할 때 : 메소드를 제작 (사용자 정의 메소드)
       		     	
       		     	=> 시작과 동시에 데이터 읽기
       		     	   ================== mounted()
       		     	=> 이전 this.curpage-1
       		     	=> 다음 this.curpage+1
       		     	=> 블록별 번호 this.curpage=값
       		   	
       		   	<<react>>
       		   	class A{
       		   		state={
       		   			<data()와 동일>
       		   		}
       		   		componentDidMount(){<mountd()와 동일>} 
       		   		btnClick(){
       		   		
       		   		}
       		   	}
       		   	
       		   	=> react : 단독처리
       		   	
       		   	=> JSP / Spring => PR 
    --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<style type="text/css">
.container{
margin-top: 50px;
}
.row{
margin: 0px auto;
width: 960px;
}
.images:hover{
cursor: pointer;
}
</style>
</head>
<body>
<%-- view --%>
<div class="container">
<div class="row">
<h3 class="text-center">{{msg}}</h3>
<input type="button" value="클릭" @click="change()"><br>
<input type="text" size="20" v-model="msg">
</div>
</div>
<%--  --%>
<script>
let app=Vue.createApp({
	data(){
		return{
			msg:'Hello Vue'
		}
	},
	methods:{
		change(){
			this.msg="변경됨"
		}
	}
}).mount(".container")
</script>
</body>
</html>