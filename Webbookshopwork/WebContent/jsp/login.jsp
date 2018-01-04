<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Eminent Login Form Flat Responsive widget Template :: xmoban.cn</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Eminent Login Form Widget Responsive, Login form web template,Flat Pricing tables,Flat Drop downs  Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- font files -->
<!--<link href='//fonts.googleapis.com/css?family=Raleway:400,100,200,300,500,600,700,800,900' rel='stylesheet' type='text/css'>-->
<!--<link href='//fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>-->
<!-- /font files -->
<!-- css files -->
<link href="../css/style.css" rel='stylesheet' type='text/css' media="all" />
<!-- /css files -->
<script type="text/javascript">

window.onload = function(){
	
	var msg = document.getElementById("msg").value;
	if(msg != null && msg != ""){
		
		alert(msg);
		
	}
}

</script>
</head>
<body>
<h1>登录亲爱的书城</h1>
<input type="hidden" name="msg" value="${msg}" id="msg" />
<div class="form-w3ls">
    <ul class="tab-group cl-effect-4">
        <li class="tab active"><a href="#signin-agile">登 录</a></li>
		<li class="tab"><a href="#signup-agile">注 册</a></li>        
    </ul>
    <div class="tab-content">
        <div id="signin-agile">   
			<form action="/Webbookshopwork/user/login.do" >
				
				<p class="header">用户名</p>
				<input type="text" name="username" value="请输入用户名" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入用户名';}">
				
				<p class="header">密  码</p>
				<input type="password" name="password"  onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}">
				
				<input type="checkbox" id="brand" value="">
				<label for="brand"><span></span>   记住我</label>
				<input type="submit" class="sign-in" value="登 录"><br/><br/>
				<font color="red" style="font-weight:bloder">${error}</font>
				
				<ul class="links">
					<!--  <li class="pass-w3ls"><a href="/Webbookshopwork/user/forgetPassword.do">&nbsp;&nbsp;忘记密码</a></li>-->
				</ul>
				
			</form>
		</div>
		<script type="text/javascript" >
		
		
        function checkLength(){
  
       		var password=document.getElementById("testPassword"); //获取密码框值
   
    		if(password.value.length<6){
        		 alert("密码长度必须大于六位！");
   			 }
       		
    		
      
    }
		</script>
		
		<div id="signup-agile">  
		<script type="text/javascript">
		
		function checksame(){
			
			var password=document.getElementById("testPassword").value; //获取密码框值
	       	var repassword=document.getElementById("repassword").value; //获取确认密码框值
	   
	    	if(password != repassword){
	        		 alert("确认密码必须和密码相同！");
	   		}
		}
		
		</script> 
		<script type="text/javascript">

		function checkIsExist(){
			
        	var username = $("#username").val(); 
             $.ajax({  
                type:"POST",    
                url:"${pageContext.request.contextPath}/book/checkUser.do ",
                data:{username:username},
                datatype:'json',
                async: false,
                success:function(data){ 
                    var json = eval("("+data+")");
                    if(json) { 
                        $("#showResult").html("<font color='green' id='isUserd'>用户名合法！</font>");  
                      } else {  
                          $("#showResult").html("<font color='red'>用户名已存在或为空！</font>");
                      }   
                 }   
              });  
        }


</script>
			<form action="/Webbookshopwork/user/registUser.do" method="post" enctype="multipart/form-data">
				<p class="header">头像</p><br/>
				<input type="file" name="upfile"><br/><br/>
				
				<p class="header">用户名</p>
				<input type="text" onblur="checkIsExist()" name="username" id="username" class="username" value="请输入用户名"   onfocus="this.value = '';">
                <span id="showResult"></span>

				<p class="header">手机</p>
				<input type="text" name="phone" value="请输入电话号码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入电话号码';}">
				
				<p class="header">邮寄地址</p>
				<input type="text" name="address" value="XXX省XXX市XXX镇XX区XX街道XX号" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'XXX省XXX市XXX镇XX区XX街道XX号';}">
				
				<p class="header">邮箱</p>
				<input type="text" name="email"  value="XXXX@XX.com"  onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'XXXX@XX.com';}">
				
				<p class="header">密 码</p>
				<input type="password" name="password" id="testPassword" onfocus="this.value = '';" onblur="checkLength()">
				
				<p class="header">确认密码</p>
				<input type="password" name="repassword" id="repassword" onfocus="this.value = '';" onblur="checksame()">
				<font color="red" style="font-weight:bloder">${error}</font>
				<input type="submit" class="register" value="注 册">
			</form>
		</div> 
    </div><!-- tab-content -->
</div> <!-- /form -->	  
<p class="copyright">© 2016 Eminent Login Form. All Rights Reserved | Design by mycodes.net</p>
<!-- js files -->
<script src='../js/jquery.min.js'></script>
<script src="../js/index.js"></script>
<!-- /js files -->
</body>
</html>
