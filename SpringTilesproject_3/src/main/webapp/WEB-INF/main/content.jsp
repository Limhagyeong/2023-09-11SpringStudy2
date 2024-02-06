<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
   <div class="container" id="foodListApp">
     <div class="row">
      <div class="col-md-3" v-for="vo in food_list">
          <div class="thumbnail">
            <a href="#">
             <img :src="'https://www.menupan.com'+vo.poster" style="width:100%">
               <div class="caption">
                <p style="font-size: 8px;">{{vo.name}}</p>
              </div>
              </a>
          </div>
        </div>
     </div>
     <div style="height: 20px"></div>
			<div  class="row">
				<div class="text-center">
					<ul class="pagination">
						<li v-if="startpage>1"><a class="alink" @click="prev()">&laquo;</a></li>
						<li v-for="i in range(startpage,endpage)" :class="curpage===i?'active':''"><a class="alink" @click=pagechange(i)>{{i}}</a></li>
						<li v-if="endpage<totalpage"><a class="alink" @click="next()">&raquo;</a></li>
					</ul>
				</div>
			</div>
   </div>
   <script>
   let foodListApp=Vue.createApp({
         data(){
            return{
               food_list:[],  
               curpage:1,
               totalpage:0,
               startpage:0,
               endpage:0
            }
         },
         mounted(){
            // 첫페이지 로딩 => 자동 호출 함수 사용
            this.dataRecv()
         },
         // 이벤트 처리 => 버튼 클릭 , 이미지 클릭
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
            	
            	axios.get("../food/page_vue.do",{
            		params:{
            			page:this.curpage
            		}
            	}).then(res=>{
            		console.log(res.data)
            		this.curpage=res.data.page
            		this.startpage=res.data.startpage
            		this.endpage=res.data.endpage
            		this.totalpage=res.data.totalpage
            	})
            },
            // 블록별 페이지 출력 
            range(start,end){
            	let arr=[]
            	let leng=end-start
            	
            	for(let i=0;i<=leng;i++)
            	{
            		arr[i]=start
            		start++
            	}
            	return arr;
            }
            
         }
   }).mount('#foodListApp')
   </script>
</body>
</html>