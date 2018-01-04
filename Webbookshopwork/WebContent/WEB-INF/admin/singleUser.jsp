<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href=".././css/common.css"/>
    <link rel="stylesheet" type="text/css" href=".././css/main.css"/>
    <script type="text/javascript" src=".././js/libs/modernizr.min.js"></script>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="/Webbookshopwork/admin/login.to">首页</a></li>
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
            <h1>后台管理</h1>
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
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">作品管理</a><span class="crumb-step">&gt;</span><span>新增作品</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="seeAllUser.to"  id="myform" name="myform" >
                    <table class="insert-tab" width="100%">
                        <tbody><tr>
                            <th width="120"><i class="require-red">*</i>用户头像：</th>
                            <td>
                            	<img src=".././${user.imgUrl}" alt="用户头像" style="width: 100px;height: 100px" />
                                <!--  <select name="colId" id="catid" class="required">
                                    <option value="">请选择</option>
                                    <option value="19">精品界面</option><option value="20">推荐界面</option>
                                </select>-->
                            </td>
                        </tr>
                            <tr>
                                <th><i class="require-red">*</i>用户ID：</th>
                                <td>
                                    <input class="common-text required" id="title" name="id" size="50" value="${user.id}" type="text" style="width: 300px;" readonly="readonly">
                                </td>
                            </tr>
                            <tr>
                                <th>用户名：</th>
                                <td><input class="common-text" name="username" size="50" value="${user.username}" type="text" style="width: 300px;" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>地址：</th>
                                <td><input class="common-text" name="address" size="50" value="${user.address}" type="text" style="width: 300px;" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>联系电话：</th>
                                <td><!--<textarea name="content" class="common-textarea" id="content" cols="30" style="width: 98%;" rows="10"></textarea>  -->
                                	  <input class="common-text" name="phone" size="50" value="${user.phone}" type="text" style="width: 300px;" readonly="readonly">
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>e-mail：</th>
                                <td><input class="common-text" name="email" size="50" value="${user.email}" type="text" style="width: 300px;" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="返回" type="submit">
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