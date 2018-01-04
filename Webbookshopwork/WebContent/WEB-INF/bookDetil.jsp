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
                <li><a class="on" href="index.html">首页</a></li>
                <li><a href="#" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="http://www.jscss.me">管理员</a></li>
                <li><a href="http://www.jscss.me">修改密码</a></li>
                <li><a href="http://www.jscss.me">退出</a></li>
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
                    <a href="#"><i class="icon-font">&#xe003;</i>客房管理</a>
                    <ul class="sub-menu">
                        <li><a href="allRooms.html"><i class="icon-font">&#xe008;</i>所有客房</a></li>
                        <li><a href="addRoom.html"><i class="icon-font">&#xe005;</i>添加客房</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>客房分类管理</a>
                    <ul class="sub-menu">
                        <li><a href="allCategorys.html"><i class="icon-font">&#xe017;</i>所有分类<li><a href="addCategory.html"><i class="icon-font">&#xe037;</i>添加分类</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>新闻管理</a>
                    <ul class="sub-menu">
                        <li><a href="allNewses.html"><i class="icon-font">&#xe017;</i>所有新闻<li><a href="addNews.html"><i class="icon-font">&#xe037;</i>添加新闻</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>商务会议管理</a>
                    <ul class="sub-menu">
                        <li><a href="allMeetings.html"><i class="icon-font">&#xe017;</i>所有会议<li><a href="addMeeting.html"><i class="icon-font">&#xe037;</i>添加会议</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="allRooms.html">客房管理</a><span class="crumb-step">&gt;</span><span>新增客房</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="insertUser.to" method="post" id="myform" name="myform" enctype="multipart/form-data">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                        	<th width="120"><i class="require-red">*</i>头像：</th>
                            <td>
                            	<img src=".././${user.imgUrl}" style="width:100px;height:100px;">
                                <input type="file" name="upfile" />
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>用户名：</th>
                            <td>
                                <input type="text" name="username"  value="${user.username}"/>
                            </td>
                        </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>密码：</th>
                            <td>
                                <input type="text" name="password"  value="${user.password}"/>
                            </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>地址：</th>
                            <td>
                                <input type="text" name="address" value="${user.address}" />
                            </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>联系电话：</th>
                            <td>
                                <input type="text" name="phone"  value="${user.phone}"/>
                            </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>类型：</th>
                            <td>
                                <select name="category">
                                	<option>customer</option>
                                	<option>vip</option>
                                </select>
                            </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>email：</th>
                            <td>
                                <input type="text" name="email" value="${user.email}" />
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