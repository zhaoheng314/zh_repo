<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bill_edit.jsp' starting page</title>
    
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
        var dishIds = "";
        var dishNums = "";
        $(document).ready(function(){
           $(".i-checks").iCheck({
              checkboxClass:"icheckbox_square-green",
              radioClass:"iradio_square-green"
           });
        
        });
        
        $(function(){
   				
   			$(".form-horizontal").Validform({
   				tiptype:4,
   				label:".label",
   				showAllError:true,
   				callback:function(data){
   					save_bill();
   					return false;
   				}
   			});
   			
   		});
        
        //结算
        function save_bill(){
           if(!confirm("确定保存账单信息吗?")){
              return;
           }
           //定义选中的菜品编号数组
           var checkedIdArr = new Array();
           var i = 0;
           //获得选择的菜品的编号，拼接成字符串，使用“,”分割
           $("input[type=checkbox]").each(function(){
                if($(this).prop("checked")){
                   //将选中的菜品编号加入数组
                   checkedIdArr[i] = $(this).val();
                   dishIds += $(this).val() + ",";
                   i++;
                }
           });
           //判断是否选择了菜品
           if(dishIds.length == 0){
              alert("请选择菜品!");
              return;
           }
           //给页面菜品编号串赋值
           $("#dishIds").val(dishIds);
           //获取选择的菜品的数量，拼接成字符串，使用“,”分割
           for(var j = 0; j<checkedIdArr.length; j++){
                dishNums += $("#"+checkedIdArr[j]).val() + ",";
           } 
           //给页面菜品数量串赋值    
           $("#dishNums").val(dishNums); 
           if(flag){
              alert("正在保存账单信息，请稍后操作!");
           }
           var url = "<%=basePath%>bill/saveBill.do";
           $.post(url, $("#billForm").serialize(), function(data) {
			    var jsonReturn = eval("(" + data + ")");
			    if(jsonReturn.code == "1"){   //保存成功
					alert(jsonReturn.msg);
					//防止重复提交表单
		            flag = true;
					//跳转至账单列表页面
					location.href = "<%=basePath%>bill/billList.do"; 
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
                        <form method="get" class="form-horizontal" id="billForm">
                           <input type="hidden" name="op" id="op" value="${op}" /> 
                           <input type="hidden" name="dishIds" id="dishIds" value="${bill.dishIds}" />
                           <input type="hidden" name="dishNums" id="dishNums" value="${bill.dishNums}" />
		                   <input type="hidden" name="id" id="id" value="${bill.id}" />
		                   <input type="hidden" name="billStatus" id="billStatus" value="${bill.billStatus}" />
                            <c:if test="${op == 'add'}">
                                <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                     <label class="col-sm-2 control-label">餐桌编号:</label>
                                     <div class="col-sm-4">
                                         <select class="form-control m-b" id="tableId" name="tableId">
                                             <c:forEach items="${tableList}" var="t">
                                                   <option value="${t.id}">${t.tableCode}&nbsp;&nbsp;&nbsp;&nbsp;(${t.tableSize}人桌)</option>
                                             </c:forEach>
                                         </select>
                                     </div>
                                </div>
                            </c:if>
                            <c:if test="${op == 'modify'}">
                                <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                     <label class="col-sm-2 control-label">餐桌编号:</label>
                                     <div class="col-sm-4">
                                         <select class="form-control m-b" id="tableId" name="tableId">
                                             <c:if test="${!empty table}">
                                                   <option value="${table.id}">${table.tableCode}&nbsp;&nbsp;&nbsp;&nbsp;${table.tableSize}人桌</option>
                                             </c:if>
                                             <c:forEach items="${tableList}" var="t">
                                                   <option value="${t.id}">${t.tableCode}&nbsp;&nbsp;&nbsp;&nbsp;(${t.tableSize}人桌)</option>
                                             </c:forEach>
                                         </select>
                                     </div>
                                </div>
                            </c:if>
                            <c:if test="${op == 'add'}">
                                <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                     <label class="col-sm-2 control-label">菜品列表:</label>
                                     <div class="col-sm-10">
                                          <c:forEach items="${dishList}" var="dish">
                                               <label class="checkbox-inline i-checks">
                                               <input type="checkbox" value="${dish.id}">${dish.dishName}(${dish.price}元)</label><br>
                                               <label class="checkbox-inline i-checks" for="num">数量:
                                               <input type="number" name="num" id="${dish.id}" min="1" max="10" step="1" value="1" ></label><br>
                                          </c:forEach>
                                     </div>
                                </div>   
                            </c:if> 
                            <c:if test="${op == 'modify'}">
                                <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                     <label class="col-sm-2 control-label">菜品列表:</label>
                                     <div class="col-sm-10">
                                          <c:forEach items="${checkedList}" var="cdish">
                                               <label class="checkbox-inline i-checks">
                                               <input type="checkbox" value="${cdish.id}" checked="checked">${cdish.dishName}(${cdish.price}元)</label><br>
                                               <label class="checkbox-inline i-checks" for="num">数量:
                                               <input type="number" name="num" id="${cdish.id}" min="1" max="10" step="1" value="${cdish.number}" ></label><br>
                                          </c:forEach>
                                          <c:forEach items="${uncheckedList}" var="udish">
                                               <label class="checkbox-inline i-checks">
                                               <input type="checkbox" value="${udish.id}">${udish.dishName}(${udish.price}元)</label><br>
                                               <label class="checkbox-inline i-checks" for="num">数量:
                                               <input type="number" name="num" id="${udish.id}" min="1" max="10" step="1" value="1" ></label><br>
                                          </c:forEach>
                                     </div>
                                </div>  
                            </c:if>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">保存</button>
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
