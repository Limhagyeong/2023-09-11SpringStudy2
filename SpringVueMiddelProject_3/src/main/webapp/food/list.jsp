<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="http://unpkg.com/axios/dist/axios.min.js"></script>
<!-- 
	model = viewmodel = view(html) ==> MVVM
 -->
<style type="text/css">
.container{
margin-top: 50px;
}
.row{
margin: 0px auto;
width: 960px;
}
a.link:hover{
cursor: pointer;
}
</style>
</head>
<body>
	<jsp:include page="${login_jsp}"></jsp:include>
	<div class="container" id="app1">
		<div class="row">
			<div class="col-md-3" v-for="vo in food_list">
			<a :href="'../food/detail.do?fno='+vo.fno">
				<div class="thumbnail">
					<img :src="'https://www.menupan.com'+vo.poster" style="width: 100%">
					<div class="caption">
						<p style="font-size: 8px">{{vo.name}}</p>
					</div>
				</div>
				</a>
			</div>
		</div>
		<div  style="height: 20px"></div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<li v-if="startpage>1"><a class="link"  @Click="prev()">&laquo;</a></li>
					<li v-for="i in range(startpage,endpage)" :class="curpage===i?'active':''">
					<a class="link" @Click="pagechange(i)">{{i}}</a>
					</li>
					<li v-if="endpage<totalpage"><a class="link" @Click="next()">&raquo;</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			// 멤버변수 설정 => Model
			// viewmodel => 기능처리 ==> model에 있는 데이터값 (상태) 변경
			data(){
				return{
					food_list:[],
					curpage:1,
					startpage:0,
					endpage:0,
					totalpage:0
				}
			},
			// 시작과 동시에 데이터처리
			mounted(){
				this.dataRecv()
			},
			// 사용자 정의
			methods:{
				dataRecv(){
					axios.get("../food/list_vue.do",{
						params:{
							page:this.curpage
						}
					}).then(res=>{
						console.log(res.data)
						this.food_list=res.data
						
					})
					
					axios.get('../food/page_vue.do',{
						params:{
							page:this.curpage
						}
					}).then(res=>{
						console.log(res.data)
						this.curpage=res.data.curpage
						this.totalpage=res.data.totalpage
						this.startpage=res.data.startpage
						this.endpage=res.data.endpage
					})
				},
				range(start,end){
					let arr=[];
					let leng=end-start
					
					for(let i=0;i<=leng;i++)
						{
						arr[i]=start;
						start++
						}
					return arr
				},
				prev(){
					this.curpage=this.startpage-1
					this.dataRecv()
				},
				next(){
					this.curpage=this.endpage+1
					this.dataRecv()
				},
				pagechange(page){
					this.curpage=page
					this.dataRecv()
				}
			
			},
			// 상ㅌ애가 변경되었을 경우 => 데이터 (data()에 있는 데이터값이 변경)
			updated(){
				
			}
		}).mount('#app1')
	</script>
</body>
</html>