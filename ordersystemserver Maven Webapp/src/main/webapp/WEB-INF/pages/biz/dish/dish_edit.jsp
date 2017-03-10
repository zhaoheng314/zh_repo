<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dish_edit.jsp' starting page</title>
    
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
   					save_dish();
   					return false;
   				}
   			});
   			
   		});
        
        //保存系统用户信息
        function save_dish(){
           if(flag){
              alert("表单正在提交，请稍后操作!");
           }
           var url = "<%=basePath%>dish/saveDish.do";
           $.post(url, $("#dishForm").serialize(), function(data) {
			    var jsonReturn = eval("(" + data + ")");
			    if(jsonReturn.code == "1"){   //保存成功
					alert(jsonReturn.msg);
					//防止重复提交表单
		            flag = true;
					//跳转至菜品列表页面
					location.href = "<%=basePath%>dish/dishList.do"; 
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
                        <form method="get" class="form-horizontal" id="dishForm">
                           <input type="hidden" name="op" id="op" value="${op}" /> 
		                   <input type="hidden" name="id" id="id" value="${dish.id}" />
		                   <input type="hidden" name="dishStatus" id="dishStatus" value="${dish.dishStatus}" />
                           <div class="form-group">
                                <label class="col-sm-2 control-label">菜品名称:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="dishName" name="dishName" class="form-control"  datatype="s2-10" errormsg="菜品名称至少2个字符,最多10个字符！" value="${dish.dishName}">                       
                                </div>
                            </div>
                            <c:if test="${op == 'add'}">
                                <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                     <label class="col-sm-2 control-label">菜品类别:</label>
                                     <div class="col-sm-4">
                                         <select class="form-control m-b" id="categoryId" name="categoryId">
                                             <c:forEach items="${cateList}" var="c">
                                                   <option value="${c.id}">${c.categoryName}</option>
                                             </c:forEach>
                                         </select>
                                     </div>
                                </div>
                            </c:if>
                            <c:if test="${op == 'modify'}">
                                <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                     <label class="col-sm-2 control-label">菜品类别:</label>
                                     <div class="col-sm-4">
                                         <select class="form-control m-b" id="categoryId" name="categoryId">
                                             <c:if test="${!empty catgegory}">
                                                   <option value="${catgegory.id}">${catgegory.categoryName}</option>
                                             </c:if>
                                             <c:forEach items="${cateList}" var="c">
                                                   <option value="${c.id}">${c.categoryName}</option>
                                             </c:forEach>
                                         </select>
                                     </div>
                                </div>
                            </c:if>                         
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">菜品单价:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="price" name="price" class="form-control" datatype="n1-3" errormsg="单价至少1位数字,至多3位数字！" value="${dish.price}" >                                   
                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">图片URL:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="imageUrl" name="imageUrl" class="form-control" value="${dish.imageUrl}" >                                   
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">菜品描述:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="dishDesc" name="dishDesc" class="form-control" datatype="s0-50" errormsg="菜品描述至多50个字符！" value="${dish.dishDesc}">
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
