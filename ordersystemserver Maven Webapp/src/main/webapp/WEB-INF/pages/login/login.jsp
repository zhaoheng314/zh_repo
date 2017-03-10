<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script>
         
         $(function(){
         
              var flag = true;
              
              $("#login_form").submit(function(){
                  if(flag){
			 	      var url = "<%=basePath%>login/doLogin.do";			 
				      $.post(url, $("#login_form").serialize(), function(data){
					     var jsonReturn = eval("(" + data +")");
					     alert(jsonReturn.msg);
		   			     if(jsonReturn.code == "1"){//登录成功
			   				 //$("#confirm_button").on('click',function(){			   					
			   					 location.href = "<%=basePath%>main/init.do"; 
			   				 //});		   					
		   			     }else{
		   					 return;
		   			     }
				      });			
			     } else {
				      alert("用户名或者密码不能为空！");
			     }

		         return false;
              });
         });
    </script>
    <style type="text/css">
         #footer{
            position: fixed;
            bottom:140px;
            width: 100%;
            font-size: 14px;
	     }     
    </style>
  </head>
  
  <body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">S</h1>

            </div>
            <h3>基于ARM的嵌入式无线点餐机</h3>
            <h4>后台管理系统</h4>

            <form class="m-t" role="form" action="#" method="post" id="login_form">
                <div class="form-group">
                    <input type="text" class="form-control" id="username" name="username" value="" placeholder="用户名" >
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" value="" placeholder="密码" >
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
            </form>
        </div>
    </div>
    <div id="footer" align="center">
       <span >Copyright &copy; 2016-2020 计算机学院 All right Reserved</span>
    </div>
   </body>
</html>
