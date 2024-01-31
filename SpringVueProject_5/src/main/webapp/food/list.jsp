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
.images:hover{
cursor: pointer;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3" v-for="vo in food_list">
				<div class="thumbnail">
					<a href="#">
					 <img :src="'https://www.menupan.com'+vo.poster" 
					 style="width: 100%" class="images">
						<div class="caption">
							<p>{{vo.name}}</p>
						</div>
					</a>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
				<li v-if="startPage>1"><a href="#" @click="prev()">&laquo;</a></li>
				<li v-for="i in range(startPage,endPage)" :class="i===curpage?'active':''">
				<a href="#" @click="pageChange(i)">{{i}}</a>
				</li>
				<li v-if="endPage<totalPage"><a href="#" @click="next()">&raquo;</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script>
let app=Vue.createApp({
	data(){
		return{
				food_list:[],
				curpage:1,
				totalPage:0,
				startPage:0,
				endPage:0
		}
	},
	mounted(){
		this.dataSend()
	},
	methods:{
		dataSend(){
			axios.get("http://localhost:8080/web/food/list_vue.do",{
				params:{
					page:this.curpage
				}
			}).then(res=>{
				console.log(res.data)
				this.food_list=res.data
				this.curpage=res.data[0].curpage
				this.totalPage=res.data[0].totalPage
				this.startPage=res.data[0].startPage
				this.endPage=res.data[0].endPage
			})
		},
		// 숫자 => 배열로 만들어놓기
		range(start,end){
			let arr=[];
			let len=end-start
			for(let i=0;i<=len;i++)
			{
				arr[i]=start
				start++
			}
			return arr
		},
		pageChange(page){
			this.curpage=page
			this.dataSend()
		},
		prev(){
			this.curpage=this.startPage-1
			this.dataSend()
		},
		next(){
			this.curpage=this.endPage+1
			this.dataSend()
		}
	}
	
}).mount(".container")
</script>
</body>
</html>