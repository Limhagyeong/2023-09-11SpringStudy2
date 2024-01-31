let app=Vue.createApp({
	data(){
		return{
			food_list:[],
			fd:'마포'
		}
	},
	// find.do?address=마포
	mounted(){
		this.send()	
	},
	methods:{
		send(){
			axios.get('http://localhost:8080/web/food/find.do',{
				params:{
					address:this.fd
				}
			}).then(response=>{
				console.log(response.data)
				this.food_list=response.data
			})
		},
		find(){
			let fds=this.$refs.fd.value;
			if(fds=="")
			{
				alert("검색어 입력")		
				return
			}
			this.send()	
		}
	}
}).mount('.container')