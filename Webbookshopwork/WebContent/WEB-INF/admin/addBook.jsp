<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href=".././css/common.css"/>
    <link rel="stylesheet" type="text/css" href=".././css/main.css"/>
    <script type="text/javascript" src=".././js/libs/modernizr.min.js"></script>
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
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="index.to">首页</a></li>
                <li><a href="/Webbookshopwork/index.html" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
               <li><a href="seeAllAdmin.to">管理员</a></li>
                <li><a href="seeAdminDetil.to">详情/修改密码</a></li>
                <li><a href="loginout.to">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
         <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="seeAllUser.to"><i class="icon-font">&#xe003;</i>用户管理</a>
                    <ul class="sub-menu">
                        <li><a href="seeAllUser.to"><i class="icon-font">&#xe008;</i>所有用户</a></li>
                        <li><a href="addUser.to"><i class="icon-font">&#xe005;</i>添加用户</a></li>
                    </ul>
                </li>
                <li>
                    <a href="seeAllBook.to"><i class="icon-font">&#xe018;</i>商品管理</a>
                    <ul class="sub-menu">
                        <li><a href="seeAllBook.to"><i class="icon-font">&#xe017;</i>所有商品</a><li><a href="addBook.to"><i class="icon-font">&#xe037;</i>添加商品</a></li>
                    </ul>
                </li>
                <li>
                    <a href="seeAllShop.to"><i class="icon-font">&#xe018;</i>订单管理</a>
                    <ul class="sub-menu">
                        <li><a href="seeAllShop.to"><i class="icon-font">&#xe017;</i>所有订单</a></li><li><a href="findShopByState.to?state=已发货"><i class="icon-font">&#xe037;</i>已处理订单</a></li><li><a href="findShopByState.to?state=未发货"><i class="icon-font">&#xe037;</i>未处理订单</a></li>
                    </ul>
                </li>
                <li>
                    <a href="findAllType.to"><i class="icon-font">&#xe018;</i>分类管理</a>
                    <ul class="sub-menu">
                        <li><a href="getAllType.to"><i class="icon-font">&#xe017;</i>所有分类</a><li><a href="addType.to"><i class="icon-font">&#xe037;</i>添加分类</a></li> 
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <input type="hidden" id="msg" name="msg"  value="${msg}"/>
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.to">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="seeAllBook.to">商品管理</a><span class="crumb-step">&gt;</span><span>新增商品</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="insertBook.to" method="post" id="myform" name="myform" enctype="multipart/form-data">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                        	<input type="hidden" name="id" value="${book.id}" />
                        	<th width="120"><i class="require-red">*</i>图书：</th>
                            <td>
                            	<img src=".././${book.imgUrl}" style="width:200px;height:200px;">
                                <input type="file" name="upfile" />
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>书名：</th>
                            <td>
                                <input style="width: 500px;" type="text" name="bookName"  value="${book.bookName}"/>
                            </td>
                        </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>作者：</th>
                            <td>
                                <input style="width: 500px;" type="text" name="author"  value="${book.author}"/>
                            </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>出版社：</th>
                            <td>
                                <input style="width: 500px;" type="text" name="publish" value="${book.publish}" />
                            </td>
                            </tr>
                            <tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>原价：</th>
                            <td>
                                <input style="width: 500px;" type="text" name="oldPrice" value="${book.oldPrice}" />
                            </td>
                            </tr>
                            <tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>现价：</th>
                            <td>
                                <input style="width: 500px;" type="text" name="price" value="${book.price}" />
                            </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>类别：</th>
                            <td>
                            	
                                	<c:forEach items="${typelist}" var = "type">
             						<input type="checkbox" value="${type.type}"  name="type"/>${type.type}&nbsp;&nbsp;        
                                		
                                	</c:forEach>
                               
                            </td>
                            
                            </tr>
                            <tr>
                            <th width="120"><i class="require-red">*</i>标签：</th>
                            <td>
                            	
                                	<c:forEach items="${marketlist}" var = "market">
             						<input type="checkbox" value="${market.type}"  name="market"/>${market.type}&nbsp;&nbsp;        
                                		
                                	</c:forEach>
                               
                            </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>描述：</th>
                            <td>
                                <textarea style="width: 500px;height: 150px;" name="description">${book.description}</textarea>
                            </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>内容概述：</th>
                            <td>
                                <textarea style="width: 500px;height: 150px;" name="content">${book.content}</textarea>
                            </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>作者介绍：</th>
                            <td>
                                <textarea style="width: 500px;height: 150px;" name="authorDes">${book.authorDes}</textarea>
                            </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>推荐理由：</th>
                            <td>
                                <textarea style="width: 500px;height: 150px;" name="recommendation">${book.recommendation}</textarea>
                            </td>
                            </tr>
                            
                            
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                    <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
    <!--/main-->
</div>
</body>
</html>
</body>
</html>