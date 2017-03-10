<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>系统主页</title>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript">
       /*
       $(function(){
            if($("#biz_menu").show()){
               $("#biz_menu").hide(); 
            }
            
            if($("#sys_menu").show()){
               $("#sys_menu").hide(); 
            }
       });
    
       function open_biz_menu(){
          $("#biz_menu").show();  
       }
       
       function close_biz_menu(){
          $("#biz_menu").hide();
       }
       
       function open_sys_menu(){
          $("#sys_menu").show();
       }
       
        function close_sys_menu(){
          $("#sys_menu").hide();
       }
       */
       function logout(){
          var flag = confirm("确定要退出系统吗?");
          if(flag){
             location.href = "<%=basePath%>login/logout.do";
          }
       }
    </script>
  </head>
  
  <body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" src="<%=uiPath%>common/img/profile_small.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#" title="点击修改个人信息">
                             <span class="clear">
                                <span class="block m-t-xs"><strong class="font-bold">当前用户:${loginUser.username}</strong></span>
                                <span class="text-muted text-xs block">欢迎你:${loginUser.realname}<b class="caret"></b></span>
                             </span>
                             </a>
                        </div>
                    </li>
                <!--     <li onmouseover="open_biz_menu();" onmouseout="close_biz_menu();"> -->
                    <!-- 动态生成菜单  -->
                    <c:forEach items="${parentList}" var="parent">
                      <li>
                          <a href="#" >
                            <i class="fa fa-home"></i>
                            <span class="nav-label">${parent.privilegeName}</span>
                            <span class="fa arrow"></span>
                          </a>
                          <ul class="nav nav-second-level">
                          <c:forEach items="${childList}" var="child">
                                <c:if test="${child.parentId == parent.id}">
                                      <li>
                                          <a class="J_menuItem" href="<%=basePath%>${child.privilegeUrl}">${child.privilegeName}</a>
                                      </li>
                                </c:if>
                          </c:forEach>
                          </ul>
                     </li>
                    </c:forEach>
                    <!--  
                    <li>
                        <a href="#" >
                            <i class="fa fa-home"></i>
                            <span class="nav-label">业务管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul id="biz_menu" class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="<%=basePath%>dish/dishList.do">菜品列表</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="<%=basePath%>category/categoryList.do">菜品类别列表</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="<%=basePath%>table/tableList.do">餐桌列表</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="<%=basePath%>member/memberList.do">会员列表</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="<%=basePath%>bill/billList.do">账单列表</a>
                            </li>
                        </ul>
                    </li>
                 -->
                 <!--    <li onmouseover="open_sys_menu();" onmouseout="close_sys_menu();">  
                    <li>
                        <a href="#" >
                            <i class="fa fa fa-bar-chart-o"></i>
                            <span class="nav-label">系统管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul id="sys_menu" class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="<%=basePath%>sysUser/userList.do">用户列表</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="<%=basePath%>sysRole/roleList.do">角色列表</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="<%=basePath%>sysPri/priList.do">权限列表</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="<%=basePath%>roleUser/roleUserList.do">角色用户</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="<%=basePath%>rolePri/rolePriList.do">角色权限</a>
                            </li>              
                        </ul>
                    </li>
                    -->
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom" align="center">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                         <div class="form-group">
                              <h2><span style="font-family:serif;">欢迎使用基于ARM的嵌入式无线点餐机的后台管理系统</span></h2>
                         </div>
                    </div>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="#" onclick="return false" class="active J_menuTab" data-id="#">主页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="javascript:logout();" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="<%=basePath%>logo.jsp" frameborder="0" data-id="#" seamless></iframe>
            </div>
            <div class="footer" align="center">
                <div class="pull-right">&copy; 2016-2020 计算机学院 All right Reserved</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>
  
    </body>
</html>
