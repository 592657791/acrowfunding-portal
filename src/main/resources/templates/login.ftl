<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/login.css">

<script src="/jquery/jquery-2.1.1.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.jsp" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container">

      <form class="form-signin" id="loginForm" action="loginUser" method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="loginacct" name="loginacct" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" id="userpswd" name="userpswd" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select class="form-control" name="usertype" id="usertype">
                <option value="member">会员</option>
                <option value="user">管理</option>
            </select>
		  </div>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
          <br>
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="reg.jsp">我要注册</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
      </form>
    </div>
    
    <script>
    function dologin() {
    	var loginacct = $("#loginacct").val();
    	var userpswd = $("#userpswd").val();
    	var userType = $("#usertype").val();
    	
    	if($.trim(loginacct) == ""){
    		alert("用户名不能为空！");
    		$("#loginacct").prop("value","");
    		return false;
    	}
    	
    	if(userpswd.length < 6){
    		alert("密码长度至少为6！");
    		$("#userpswd").prop("value","");
    		return false;
    	}
    	
    	$.ajax({
    		url:"/loginUser",
    		data:{
    			"loginacct":loginacct,
    			"userpswd":userpswd,
    			"usertype":userType
    		},
    		type:"POST",
    		success:function(result){
    			if(result.success){
    				window.location.href="/member";
    			}else{
    				alert("登录失败");
    			}
    		}
    	});
    	
    }
    </script>
</body>
</html>