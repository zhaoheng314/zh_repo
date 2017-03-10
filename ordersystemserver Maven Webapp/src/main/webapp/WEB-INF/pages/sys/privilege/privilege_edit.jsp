<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'blank.jsp' starting page</title>
    <base href="<%=basePath%>">
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
   					save_sys_privilege();
   					return false;
   				}
   			});
   			
   		});
        
        //保存系统角色信息
        function save_sys_privilege(){
           if(flag){
              alert("表单正在提交，请稍后操作!");
           }
           var url = "<%=basePath%>sysPri/saveSysPri.do";
           $.post(url, $("#privilegeForm").serialize(), function(data) {
			    var jsonReturn = eval("(" + data + ")");
			    if(jsonReturn.code == "1"){   //保存成功
					alert(jsonReturn.msg);
					//防止重复提交表单
		            flag = true;
					//跳转至角色列表页面
					location.href = "<%=basePath%>sysPri/priList.do"; 
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
                        <form method="get" class="form-horizontal" id="privilegeForm">
                           <input type="hidden" name="op" id="op" value="${op}" /> 
		                   <input type="hidden" name="id" id="id" value="${sysPrivilege.id}" />
		                   <input type="hidden" name="parentId" id="parentId" value="${sysPrivilege.parentId}" />
		                   <input type="hidden" name="privilegeStatus" id="privilegeStatus" value="${sysPrivilege.privilegeStatus}" />    
                            <div class="form-group">
                                <label class="col-sm-2 control-label">权限名称:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="privilegeName" name="privilegeName" class="form-control" datatype="s4-8" errormsg="权限名称至少4个字符,最多8个字符！" value="${sysPrivilege.privilegeName}" >                                   
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">权限URL:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="privilegeUrl" name="privilegeUrl" class="form-control" value="${sysPrivilege.privilegeUrl}" >                                   
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">是否系统权限:</label>
                                <div class="col-sm-4">
                                    <label class="checkbox-inline i-checks">
                                        <input type="radio" id="ifSys_1" name="ifSys"  value="1"<c:if test="${op == 'add'}">  checked="checked" </c:if> <c:if test="${op == 'modify' && sysPrivilege.ifSys == 1}">  checked="checked" </c:if> >是</label>
                                    <label class="checkbox-inline i-checks">
                                        <input type="radio" id="ifSys_0" name="ifSys" value="0" <c:if test="${op == 'modify' && sysPrivilege.ifSys == 0}">  checked="checked" </c:if> >否</label>                    
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
