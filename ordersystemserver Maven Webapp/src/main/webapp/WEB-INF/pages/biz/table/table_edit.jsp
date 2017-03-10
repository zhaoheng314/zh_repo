<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'table_edit.jsp' starting page</title>
    
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
   					save_table();
   					return false;
   				}
   			});
   			
   		});
        
        //保存系统用户信息
        function save_table(){
           if(flag){
              alert("表单正在提交，请稍后操作!");
           }
           var url = "<%=basePath%>table/saveTable.do";
           $.post(url, $("#tableForm").serialize(), function(data) {
			    var jsonReturn = eval("(" + data + ")");
			    if(jsonReturn.code == "1"){   //保存成功
					alert(jsonReturn.msg);
					//防止重复提交表单
		            flag = true;
					//跳转至餐桌列表页面
					location.href = "<%=basePath%>table/tableList.do"; 
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
                        <form method="get" class="form-horizontal" id="tableForm">
                           <input type="hidden" name="op" id="op" value="${op}" /> 
		                   <input type="hidden" name="id" id="id" value="${table.id}" />
                           <div class="form-group">
                                <label class="col-sm-2 control-label">餐桌代码:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="tableCode" name="tableCode" class="form-control"  datatype="s2-3" errormsg="餐桌代码至少2个字符,最多3个字符！" value="${table.tableCode}">                       
                                </div>
                            </div>                          
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">餐桌容量（人）:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="tableSize" name="tableSize" class="form-control" datatype="n1-2" errormsg="餐桌容量至少1位数字,最多2位数字！"  value="${table.tableSize}" >                                   
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">餐桌状态:</label>
                                <div class="col-sm-4">
                                    <label class="checkbox-inline i-checks">
                                        <input type="radio" id="tableStatus_0" name="tableStatus"  value="0"<c:if test="${op == 'add'}">  checked="checked" </c:if> <c:if test="${op == 'modify' && table.tableStatus == 0}">  checked="checked" </c:if> >空闲</label>
                                    <label class="checkbox-inline i-checks">
                                        <input type="radio" id="tableStatus_1" name="tableStatus" value="1" <c:if test="${op == 'modify' && table.tableStatus == 1}">  checked="checked" </c:if> >正在使用</label>                    
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
