<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bill_detail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript">
     
       $(function(){
            var date = new Date();
            //获得年
            var year = date.getFullYear();
            //获得月
            var month = date.getMonth() + 1;
            //获得日
            var day = date.getDate();
            if(day >= 0 && day <=10){
               day = "0" + day;
            }
            //获得小时
            var hour = date.getHours();
            //获得分钟
            var minute = date.getMinutes();
            //获得秒
            var second = date.getSeconds();
            if(second >= 0 && second <=10){
               second = "0" + second;
            }
            //拼接时间字符串
            var time = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
            //为日期<span>标签赋值
            $("#create_date").html(time);
       });
     
       //计算获得折扣价
       function discount(){
          //获得总金额
          var totalMoney = "${bill.totalMoney}";
          //会员9折
          var dishcountMoney = totalMoney*0.9;
          //向下取整
          dishcountMoney = Math.floor(dishcountMoney);
          $("#discount").html("");
          $("#discount").html(dishcountMoney);
       }
       
       //恢复总金额
       function reset_total_money(){
          //获得总金额
          var totalMoney = "${bill.totalMoney}";
          $("#discount").html("");
          $("#discount").html(totalMoney);
       }
       
       function payment(){
          var id = "${bill.id}";
          var ifMember = $("input[name=ifMember]").val();
          var money =  $("#discount").html();
          var url = "<%=basePath%>bill/payment.do";
          $.post(url, {"id":id,"ifMember":ifMember,"totalMoney":money}, function(data) {
			   var jsonReturn = eval("(" + data + ")");
			   if(jsonReturn.code == "1"){   //结算成功
			       alert("结算成功！");			
			       //跳转至账单列表页面
				   location.href = "<%=basePath%>bill/billList.do"; 
			   }else{                        //结算失败
				    alert("结算失败！");	
		       }
		  });
       }
    </script>
  </head>
  
  <body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox-content p-xl">
                    <div class="row">
                        <div class="col-sm-6">
                        </div>
                        <div class="col-sm-6 text-right">
                            <h4>单据编号：<span class="text-navy">${bill.id}</span></h4>
                            <h4>餐桌编号：<span class="text-navy">${bill.tableId}</span></h4>
                            <address>
                                <strong>**餐厅</strong><br>
                                                                                郑州市新郑市双湖经济开发区淮河路一号中原工学院<br>
                            </address>
                            <p>
                                <span><strong>日期：</strong><span id="create_date"></span></span>
                            </p>
                        </div>
                    </div>

                    <div class="table-responsive m-t">
                        <table class="table invoice-table">
                            <thead>
                                <tr>
                                    <th>清单</th>
                                    <th>数量</th>
                                    <th>单价</th>
                                    <th>单品总价</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${dishList}" var="dish">
                                    <tr>
                                    <td>
                                        <div>${dish.dishName}</div>
                                    </td>
                                    <td>${dish.number}</td>
                                    <td>&yen;${dish.price}</td>
                                    <td>&yen;${dish.money}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <table class="table invoice-total">
                        <tbody>
                            <tr>
                                <td><strong>总价：</strong>
                                </td>
                                <td>&yen;${bill.totalMoney}</td>
                            </tr>
                            <tr>
                                <td><strong>是否会员：</strong>
                                </td>
                                <td><label class="checkbox-inline i-checks">
                                        <input type="radio" id="ifMember_1" name="ifMember" value="1" onclick="discount()">是</label>
                                    <label class="checkbox-inline i-checks">
                                        <input type="radio" id="ifMember_2" name="ifMember" checked="checked" value="0" onclick="reset_total_money()">否</label>                    
                               </td>
                            </tr>
                            <tr>
                                <td><strong>折扣价</strong>
                                </td>
                                <td>&yen;<span id="discount">${bill.totalMoney}</span></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="text-right">
                        <button class="btn btn-primary" onclick="payment()"><i class="fa fa-dollar"></i>付款</button>
                    </div>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
