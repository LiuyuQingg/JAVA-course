<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
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
    	
    	
    	
    	var ckall = document.getElementById('allChoose');
        var ck = document.getElementsByName('ifdelete');
        ckall.onclick = function () {
            for (var i = 0; i < ck.length; i++) {
                if (ckall.checked == true) {
                    ck[i].checked = true;
                }
                else {
                    ck[i].checked = false;
                }
            }
        };
    	
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
        <input type="hidden" value="${message}" id="msg" />
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
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.to">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">用户管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="findUserByPoint.to" method="post">
                    <table class="search-tab">
                        <tr>
                        	<th width="120px"></th>
                          <!--   <th width="120">选择分类:</th> -->
                            <td>
                                <!--<select name="search-sort" id="">
                                    <option value="">全部</option>
                                    <option value="19">豪华双人床</option>
                                    <option value="20">豪华单人床</option>
                                    <option value="19">豪华套间</option>
                                    <option value="20">高级双人房</option>
                                    <option value="19">标准大房间</option>
                                    <option value="20">标准双人房</option>  
                                </select>-->
                            </td>
                            <th width="70">关键字:</th>
                            <td><input class="common-text" placeholder="关键字" name="point" value="" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        
        <div class="result-wrap">
           <form action="deleteApart.to">
                <div class="result-title">
                
                    <div class="result-list">
                    
                        <a href="addUser.to"><i class="icon-font"></i>新增用户</a>
                        <a id="batchDel" href="javascript:void(0)"><i class="icon-font"></i><input type="submit"  id="batchDel"  value="批量删除"  style="background: none;border:0;color:#6495ED;font-size: 13px;font-weight: 700px;"/></a>
                        <a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a> 
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input id="allChoose" class="allChoose" name="" type="checkbox" ></th>
                            
                            <th>ID</th>
                            <th>用户名</th>
                            <th>地址</th>
                            <th>联系电话</th>
                            <th>客户类型</th>
                            <th>e-mail</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${userlist}" var="user">
                        <tr>
                        	<td class="tc"><input type="checkbox" name="ifdelete" class="ifdelete" id="ifdelete" value="${user.id}" /></td>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.address}</td>
                            <td>${user.phone}</td>
                            <td>${user.category}</td>
                            <td>${user.email}</td>
                            <td>
                                <a class="link-update" href="findUser.to?id=${user.id}">查看</a>
                                <a class="link-del" href="deleteUser.to?id=${user.id}">删除</a>
                            </td>
                        </tr>
                        </c:forEach>
                        
                    </table>
                    <div>
                    		<font color="blue">统计结果：共${page.totalCount}条</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    		<c:if test="${page.totalPage == 0}">
                    			<font color="blue">当前0条</font>
                    		</c:if>
                    		<c:if test="${page.dpage != page.totalPage && page.totalPage != 0}">
                    			<font color="blue">当前${page.pageCount}条</font>
                    		</c:if>
                    		<c:if test="${page.dpage == page.totalPage}">
                    			<font color="blue">当前${page.totalCount-page.pageCount*(page.totalPage-1)}条</font>
                    		</c:if>
                    	</div>
                    	<br/>
                    	<a href="seeAllUser.to?pages=1">首页</a>&nbsp;&nbsp;&nbsp;
						<c:if test="${page.dpage != 1}">
							<a href="seeAllUser.to?pages=${page.dpage-1 }">上一页</a>&nbsp;&nbsp;&nbsp;
						</c:if>
						<c:if test="${(page.dpage != page.totalPage) && (page.totalPage != 0)}">
							<a href="seeAllUser.to?pages=${page.dpage+1}">下一页</a>&nbsp;&nbsp;&nbsp;
						</c:if>
						<a href="seeAllUser.to?pages=${page.totalPage}">尾页</a>
                    </div>
                </div>
                    <div class="list-page"> </div>
                    
                </div>
           </form>
        </div>
     
    </div>
    <!--/main-->
</div>
</body>
</html>