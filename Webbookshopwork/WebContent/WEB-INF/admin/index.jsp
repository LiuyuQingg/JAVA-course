<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>亲爱的书城后台管理</title>
    <link rel="stylesheet" type="text/css" href=".././css/common.css"/>
    <link rel="stylesheet" type="text/css" href=".././css/main.css"/>
    <script type="text/javascript" src=".../js/libs/modernizr.min.js"></script>
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
            <div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>欢迎&nbsp;&nbsp;${admin.username}&nbsp;&nbsp;进入<font color="#FF8C00">亲爱的书店</font>后台管理系统</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>快捷操作</h1>
            </div>
            <div class="result-content">
                <div class="short-wrap">
                    <a href="seeAllUser.to"><i class="icon-font">&#xe001;</i>所有用户</a>
                    <a href="seeAllBook.to"><i class="icon-font">&#xe048;</i>所有书籍</a>
                    <a href="addBook.to"><i class="icon-font">&#xe001;</i>添加书籍</a>
                    <a href="seeAllShop.to"><i class="icon-font">&#xe048;</i>所有订单</a>
                </div>
            </div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>书城情况</h1>
            </div>
            <div class="result-content">
                <ul class="sys-info-list">
                    <li>
                        <label class="res-lab">书籍总数</label><span class="res-info">${bookCount}</span>
                    </li>
                    <li>
                        <label class="res-lab">用户总数</label><span class="res-info">${userCount}</span>
                    </li>
                    <li>
                        <label class="res-lab">未处理订单总数</label><span class="res-info">${shopCount}</span>
                    </li>
                </ul>
            </div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>系统基本信息</h1>
            </div>
            <div class="result-content">
                <ul class="sys-info-list">
                    <li>
                        <label class="res-lab">操作系统</label><span class="res-info">${osName}</span>
                    </li>
                    <li>
                        <label class="res-lab">运行环境</label><span class="res-info">java_jdk/${java}</span>
                    </li>
                    <li>
                        <label class="res-lab">上传附件限制</label><span class="res-info">2M</span>
                    </li>
                    <li>
                        <label class="res-lab">北京时间</label><span class="res-info">${date}</span>
                    </li>
                    <li>
                        <label class="res-lab">服务器域名/IP</label><span class="res-info">${ip}</span>
                    </li>
                    <li>
                        <label class="res-lab">Host</label><span class="res-info">${port}</span>
                    </li>
                </ul>
            </div>
        </div>
        
    </div>
    <!--/main-->
</div>
</body>
</html>