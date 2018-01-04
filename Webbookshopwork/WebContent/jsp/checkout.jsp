<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Checkout</title>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //Custom Theme files -->
<link href="../css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
<link href="../css/style1.css" type="text/css" rel="stylesheet" media="all">
<!-- js -->
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-3.1.1.min.js"></script>
<!-- //js -->
<!-- cart -->
<script src="../js/simpleCart.min.js"> </script>
<!-- cart -->

</head>
<body>
	<!--header-->
	<div class="header">
		<div class="container">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<h1 class="navbar-brand"><a  href="index.html">Book</a></h1>
				</div>
				<!--navbar-header-->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="recommend.do" class="active">亲爱的</a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">图书<b class="caret"></b></a>
							<ul class="dropdown-menu multi-column columns-4">
								<div class="row">
									<div class="col-sm-3">
										<h4><a href="booklist.do"><font color="#FF4500" style="font-weight=bolder">全部</font></a></h4>
										<!--  <ul class="multi-column-dropdown">
											<li><a class="list" href="products.html">Friend</a></li>
											<li><a class="list" href="products.html">Lover</a></li>
											<li><a class="list" href="products.html">Sister</a></li>
											<li><a class="list" href="products.html">Brother</a></li>
											<li><a class="list" href="products.html">Kids</a></li>
											<li><a class="list" href="products.html">Parents</a></li>
										</ul>-->
									</div>																		
									<div class="col-sm-3">
										<h4>按类型</h4>
										<ul class="multi-column-dropdown">
										<c:forEach items="${typelist}" var="type">
												<li><a class="list" href="seeTypeBook.do?type=${type.type}">${type.type}</a></li>
										</c:forEach>
											<!--<li><a class="list" href="seeTypeBook.do?type=计算机">计算机</a></li>
											<li><a class="list" href="seeTypeBook.do?type=小说">小说</a></li>
											<li><a class="list" href="seeTypeBook.do?type=历史">历史</a></li>
											<li><a class="list" href="seeTypeBook.do?type=纪实">纪实</a></li>
											<li><a class="list" href="seeTypeBook.do?type=中国当代随笔">中国当代随笔</a></li>
											<li><a class="list" href="seeTypeBook.do?type=外国随笔">外国随笔</a></li>
											<li><a class="list" href="seeTypeBook.do?type=科普/百科">科普/百科</a></li>
											<li><a class="list" href="seeTypeBook.do?type=心理学">心理学</a></li>
											<li><a class="list" href="seeTypeBook.do?type=医学">医学</a></li>
											<li><a class="list" href="seeTypeBook.do?type=自然科学">自然科学</a></li>
											-->
										</ul>
									  </div>
									<!--  <div class="col-sm-3">
										<h4><font color="white">按类型</font></h4>
										<ul class="multi-column-dropdown">
											<li><a class="list" href="seeTypeBook.do?type=科技">科技</a></li>
											<li><a class="list" href="seeTypeBook.do?type=经管">经管</a></li>
											<li><a class="list" href="seeTypeBook.do?type=文艺">文艺</a></li>
											<li><a class="list" href="seeTypeBook.do?type=童书">童书</a></li>
											<li><a class="list" href="seeTypeBook.do?type=期刊/音像">期刊/音像</a></li>
											<li><a class="list" href="seeTypeBook.do?type=教育">教育</a></li>
											<li><a class="list" href="seeTypeBook.do?type=政治/军事">政治/军事</a></li>
											<li><a class="list" href="seeTypeBook.do?type=农林">农林</a></li>
											<li><a class="list" href="seeTypeBook.do?type=生活">生活</a></li>
											<li><a class="list" href="seeTypeBook.do?type=法律">法律</a></li>
										</ul>
									</div>-->
									<div class="col-sm-3">
										<h4>按标签</h4>
										<ul class="multi-column-dropdown">
											<li><a class="list" href="seeMarkBook.do?type=推荐">热门</a></li>
											<li><a class="list" href="seeMarkBook.do?type=上新">上新</a></li>
											<li><a class="list" href="seeMarkBook.do?type=主推">主推</a></li>
											<!--  <li><a class="list" href="products.html">2 kG</a></li>
											<li><a class="list" href="products.html">3 kG</a></li>
											<li><a class="list" href="products.html">4 kG</a></li>
											<li><a class="list" href="products.html">Large</a></li>-->
										</ul>
									</div>
								</div>
							</ul>
						</li>
					   <li class="dropdown grid">
							<a href="seeNewBook.do" class="dropdown-toggle list1" data-toggle="dropdown">上新<b class="caret"></b></a>
							<ul class="dropdown-menu multi-column columns-4">
								<div class="row">
									<div class="col-sm-3">
										<h4><a href="newbook.do"><font color="#FF4500" style="font-weight=bolder">全部</font></a></h4>
										<!--  <ul class="multi-column-dropdown">
											<li><a class="list" href="products.html">Friend</a></li>
											<li><a class="list" href="products.html">Lover</a></li>
											<li><a class="list" href="products.html">Sister</a></li>
											<li><a class="list" href="products.html">Brother</a></li>
											<li><a class="list" href="products.html">Kids</a></li>
											<li><a class="list" href="products.html">Parents</a></li>
										</ul>-->
									</div>																		
									<div class="col-sm-3">
										<h4>按标签</h4>
										<ul class="multi-column-dropdown">
											<li><a class="list" href="seeMarkBook.do?type=推荐">推荐</a></li>
											<li><a class="list" href="seeMarkBook.do?type=上新">上新</a></li>
											<li><a class="list" href="seeMarkBook.do?type=主推">主推</a></li>
											<!--  <li><a class="list" href="products.html">2 kG</a></li>
											<li><a class="list" href="products.html">3 kG</a></li>
											<li><a class="list" href="products.html">4 kG</a></li>
											<li><a class="list" href="products.html">Large</a></li>-->
										</ul>
									</div>
									<div class="col-sm-3">
										<!--  <h4>Weight</h4>
										<ul class="multi-column-dropdown">
											<li><a class="list" href="products.html">1 kG</a></li>
											<li><a class="list" href="products.html">1.5 kG</a></li>
											<li><a class="list" href="products.html">2 kG</a></li>
											<li><a class="list" href="products.html">3 kG</a></li>
											<li><a class="list" href="products.html">4 kG</a></li>
											<li><a class="list" href="products.html">Large</a></li>
										</ul>-->
									</div>
								</div>
							</ul>
						</li>				
						<li class="dropdown grid">
							<a href="/Webbookshopwork/user/aboutMe.do?id=${user1.id}" class="multi-column-dropdown" >我的帐号</a>
						</li>
						<li class="dropdown grid">
							<a href="/Webbookshopwork/book/seeShopping.do" class="multi-column-dropdown" >我的订单</a>
						</li>
						<!--  <li class="dropdown grid">
							<a href="#" class="dropdown-toggle list1" data-toggle="dropdown">Store<b class="caret"></b></a>
							<ul class="dropdown-menu multi-column columns-3">
								<div class="row">
									<div class="col-sm-4">
										<h4>By Relation</h4>
										<ul class="multi-column-dropdown">
											<li><a class="list" href="products.html">Friend</a></li>
											<li><a class="list" href="products.html">Lover</a></li>
											<li><a class="list" href="products.html">Sister</a></li>
											<li><a class="list" href="products.html">Brother</a></li>
											<li><a class="list" href="products.html">Kids</a></li>
											<li><a class="list" href="products.html">Parents</a></li>
										</ul>
									</div>																		
									<div class="col-sm-4">
										<h4>By Flavour</h4>
										<ul class="multi-column-dropdown">
											<li><a class="list" href="products.html">Chocolate</a></li>
											<li><a class="list" href="products.html">Mixed Fruit</a></li>
											<li><a class="list" href="products.html">Butterscotch</a></li>
											<li><a class="list" href="products.html">Strawberry</a></li>
											<li><a class="list" href="products.html">Vanilla</a></li>
											<li><a class="list" href="products.html">Eggless Cakes</a></li>
										</ul>
									</div>								
									<div class="col-sm-4">
										<h4>Specials</h4>
										<ul class="multi-column-dropdown">
											<li><a class="list" href="products.html">Ice cream cake</a></li>
											<li><a class="list" href="products.html">Swiss roll</a></li>
											<li><a class="list" href="products.html">Ruske kape</a></li>
											<li><a class="list" href="products.html">Cupcakes</a></li>
											<li><a class="list" href="products.html">Muffin</a></li>
											<li><a class="list" href="products.html">Merveilleux</a></li>										
										</ul>
									</div>
								</div>
							</ul>
						</li>	-->							
					</ul> 
					<!--/.navbar-collapse-->
				</div>
				<!--//navbar-header-->
			</nav>
			${user.username}
			<div class="header-info">
				<div class="header-right search-box">
					<a href="#"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>				
					<div class="search">
						<form class="navbar-form" action="foundBook.do">
							<input type="text" class="form-control" name="tag">
							<button type="submit" class="btn btn-default" aria-label="Left Align">
								查找
							</button>
						</form>
					</div>	
				</div>
				<div class="header-right login">
					<a href="regist.do"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
					<div id="loginBox">                
						<form id="loginForm" action="/Webbookshopwork/user/login.do">
							<fieldset id="body">
								<fieldset>
									<label for="email">用户名</label>
									<input type="text" name="username" id="email">
								</fieldset>
								<fieldset>
									<label for="password">密码</label>
									<input type="password" name="password" id="password">
								</fieldset>
								<input type="submit" id="login" value="登 录">
								<label for="checkbox"><input type="checkbox" id="checkbox"> <i>Remember me</i></label>
							</fieldset>
							<p>New User ? <a class="sign" href="regist.do">注册</a> <span><a href="#">Forgot your password?</a></span></p>
						</form>
					</div>
				</div>
				<div class="header-right cart">
					<a href="seeBookChat.do?id=${user1.id}"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>
					<div class="cart-box">
						<h4><!-- <a href="/Webbookshopwork/book/seeBookChat.do?id=${user1.id}">
							<span class="simpleCart_total"> $0.00 </span> (<span id="simpleCart_quantity" class="simpleCart_quantity"> 0 </span>) 
						</a> --></h4>
						<p><a href="deleteAllChat.do" class="simpleCart_empty">清空购物车</a></p>
						<div class="clearfix"> </div>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
	<!--//header-->
	<!--cart-items-->
	<script type="text/javascript">
		function aonclick(){
		var len = document.getElementById("delete").length;
		var ahref = document.getElementById("ahref");
		ahref.href = "deleteApart.do?delete="+len;
		}
	</script>
	<form action="seePartShop.do" style="margin: 35px;">
	<div class="cart-items">
		<div class="container">
		<input style="float: right;" type="submit" value="立即购买" />
		<a href="deleteAllChat.do" style="float: right;">清空购物车</a>
			<h2>我的购物车</h2>
			
			<script>$(document).ready(function(c) {
				$('.close1').on('click', function(c){
					$('.cart-header').fadeOut('slow', function(c){
						$('.cart-header').remove();
					});
					});	  
				});
			</script>
			
			<c:forEach items="${orders}" var="order">
			<div style="margin: 10px;border-color:#FF7F00; border-style: solid;border-width: 2px;margin-top: 15px;">
			
			<input type="hidden" name="id" value="${order.id}" />
			<div class="cart-header">
				<a href="deleteOrder.do?id=${order.id}"><div class="close1"> </div></a>
				<div class="cart-sec simpleCart_shelfItem" style="margin: 20px;" >
					<div class="cart-item cyc" style="margin: 10px;">
						 <img src="../${order.book.imgUrl}" class="img-responsive" alt="">
					</div>
					<div class="cart-item-info">
						<h3><a href="#"> ${order.book.bookName} </a>
						<span>数量：${order.quentity} <!-- <input type="text" id="quentity"  name="qu" value="${order.quentity}" style="width: 50px;"/> --></span></h3>
						<ul class="qty">
							<li><p>${order.book.author}</p></li>
							<li><p>${order.book.publish}</p></li>
						</ul>
						<div class="delivery">
												<span>Delivered in 1-1:30 hours</span><br/><br/>
							<p>价格：￥ ${order.book.price}</p>
							<span><input type="checkbox" name="buy"  value="${order.id}"/></span><br/><br/>
							<div class="clearfix">
								<!-- <input type="submit" value="立即购买" /> -->
								<a href="seeShop.do?id=${order.id}">立即购买</a>
							</div>
						</div>	
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			</div>
			 <script>$(document).ready(function(c) {
					$('.close2').on('click', function(c){
							$('.cart-header2').fadeOut('slow', function(c){
						$('.cart-header2').remove();
					});
					});	  
					});
			 </script>
			 </c:forEach>
			
		</div>
	</div>
	</form>
	<!--//checkout-->	
	<!--footer-->
	<div class="footer">
		<div class="container">
			<div class="footer-grids">
				<div class="col-md-2 footer-grid">
					<h4>company</h4>
					<ul>
						<li><a href="products.html">products</a></li>
						<li><a href="#">Work Here</a></li>
						<li><a href="#">Team</a></li>
						<li><a href="#">Happenings</a></li>
						<li><a href="#">Dealer Locator</a></li>
					</ul>
				</div>
				<div class="col-md-2 footer-grid">
					<h4>service</h4>
					<ul>
						<li><a href="#">Support</a></li>
						<li><a href="#">FAQ</a></li>
						<li><a href="#">Warranty</a></li>
						<li><a href="contact.html">Contact Us</a></li>
					</ul>
				</div>
				<div class="col-md-3 footer-grid">
					<h4>order & returns</h4>
					<ul>
						<li><a href="#">Order Status</a></li>
						<li><a href="#">Shipping Policy</a></li>
						<li><a href="#">Return Policy</a></li>
						<li><a href="#">Digital Gift Card</a></li>
					</ul>
				</div>
				<div class="col-md-2 footer-grid">
					<h4>legal</h4>
					<ul>
						<li><a href="#">Privacy</a></li>
						<li><a href="#">Terms and Conditions</a></li>
						<li><a href="#">Social Responsibility</a></li>
					</ul>
				</div>
				<div class="col-md-3 footer-grid icons">
					<h4>Connect with Us</h4>
					<ul>
						<li><a href="#"><img src="images/i1.png" alt=""/>Follow us on Facebook</a></li>
						<li><a href="#"><img src="images/i2.png" alt=""/>Follow us on Twitter</a></li>
						<li><a href="#"><img src="images/i3.png" alt=""/>Follow us on Google-plus</a></li>
						<li><a href="#"><img src="images/i4.png" alt=""/>Follow us on Pinterest</a></li>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--footer-->
	<div class="footer-bottom">
		<div class="container">
			<p>Copyright &copy; 2015.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
		</div>
	</div>
</body>
</html>