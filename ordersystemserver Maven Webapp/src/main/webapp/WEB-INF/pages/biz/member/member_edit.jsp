<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'member_edit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script>
        
        var flag = false;
        
        $(document).ready(function(){
           $(".i-checks").iCheck({
              //checkboxClass:"icheckbox_square-green",
              radioClass:"iradio_square-green"
           });
        
        });
        
        $(function(){
   				
   			$(".form-horizontal").Validform({
   				tiptype:4,
   				label:".label",
   				showAllError:true,
   				callback:function(data){
   					save_member();
   					return false;
   				}
   			});
   			
   		});
        
        //保存系统用户信息
        function save_member(){
           if(flag){
              alert("表单正在提交，请稍后操作!");
           }
           var url = "<%=basePath%>member/saveMember.do";
           $.post(url, $("#memberForm").serialize(), function(data) {
			    var jsonReturn = eval("(" + data + ")");
			    if(jsonReturn.code == "1"){   //保存成功
					alert(jsonReturn.msg);
					//防止重复提交表单
		            flag = true;
					//跳转至会员列表页面
					location.href = "<%=basePath%>member/memberList.do"; 
				}else{                        //保存失败
					alert(jsonReturn.msg);
				}

		   });
        }
    </script>
  </head>
  
  <body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                   <div class="ibox-content">
                        <form method="get" class="form-horizontal" id="memberForm">
                           <input type="hidden" name="op" id="op" value="${op}" /> 
		                   <input type="hidden" name="id" id="id" value="${member.id}" />
		                   <input type="hidden" name="memberStatus" id="memberStatus" value="${member.memberStatus}" />
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">会员姓名:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="memberName" name="memberName" class="form-control" datatype="s2-4" errormsg="用户真实姓名至少2个字符,最多4个字符！" value="${member.memberName}" >                                   
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">电话号码:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="phone" name="phone" class="form-control" value="${member.phone }"  datatype="m" errormsg="请输入正确的手机号！">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">保存</button>
                                    <button class="btn btn-white" type="reset">取消</button>
                                </div>
                            </div>
                         </form>
                   </div>
                </div>
            </div>
        </div>
    </div> 
</body>
</html>
