let app=Vue.createApp({
	data(){
		return{
			food_list:[], 
			food_detail:{}, 
			fno:0,
			isShow:false
		}
	},
	mounted(){
		axios.get('http://localhost:8080/web/food/list_vue.do').then(json=>{
			console.log(json.data)
			this.food_list=json.data
		})
		 .catch(error => {
        
        });
		
	},
	methods:{
		detail(fno){
			this.isShow=true
			this.fno=fno;
			let _this=this
			axios.get('../food/detail_vue.do',{
				params:{
					fno:_this.fno
				}
			}).then(response=>{
				console.log(response.data)
				_this.food_detail=response.data;
			})
		}
		
	}
}).mount('#app')