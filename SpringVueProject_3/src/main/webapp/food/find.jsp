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
	<table class="table">
	<tr>
	<td>
	<input type="text" class="input-sm" size="25" ref="fd" v-model="fd"
	@keyup.enter="find()"
	>
	<input type="button" class="btn-sm btn-danger" value="검색" @click="find()">
	</td>
	</tr>
	</table>
</div>
<div class="row">
	 <div class="col-md-3" v-for="vo in food_list">
    <div class="thumbnail">>
        <img :src="'https://www.menupan.com'+vo.poster" alt="Lights" style="width:100%" class="images">
        <div class="caption">
          <p>{{vo.name}}</p>
        </div>
    </div>
  </div>
</div>
</div>
<script src="../js/food_find.js"></script>
</body>
</html>