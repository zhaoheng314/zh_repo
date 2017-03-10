<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bill_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script>
        $(document).ready(function(){
            $.jgrid.defaults.styleUI="Bootstrap";
            var url = "<%=basePath%>bill/getBillList.do";
            $("#table_bill_list").jqGrid({
                 jsonReader:{
                    root:"rows",        //数据
                    records:"total",    //记录总数
                    total:"totalpage"   //总页数
                 },
                 url:url,               //获取数据的地址
                 datatype:"json",       //数据类型
                 mtype:"GET",           //ajax提交方式 
                 height:"100%",         //表格高度
                 rownumbers:true,       //如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'
                 multiselect:false,     //是否显示复选框
                 autowidth:true,       
                 shrinkToFit:true, 
                 rowNum:10,             //页的大小
                 rowList:[10,20,30],    //页的大小集合
                                        //列名
                 colNames:["","餐桌</br>编号","操作员</br>编号","菜品编号","菜品数量","是否</br>会员","总金额","创建时间","修改时间","账单</br>状态","操作"],
                                        //列的属性
                 colModel:[{width:20, formatter:function(cellvalue, options, rowObject){
                                         return '<input id="' + rowObject.id + '" name="radio_list" type="radio">';
                                      }
                           },
                           {name:"tableId", index:"tableId", width:40, align:"center"},
                           {name:"userId", index:"userId", width:50, align:"center"},
                           {name:"dishIds", index:"dishIds", width:100},
                           {name:"dishNums", index:"dishNums", width:100},
                           {name:"ifMember", index:"ifMember", width:40, sorttype:"int", 
                                formatter:function(cellvalue, options, rowObject){
                                   if(cellvalue == 0){
                                       return "否";
                                   }else{
                                       return "是";
                                   }
                                }
                           },
                           {name:"totalMoney", index:"totalMoney", width:60, align:"left"},
                           {name:"createDate", index:"createDate", width:90, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},
                           {name:"updateDate", index:"updateDate", width:90, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},
                           {name:"billStatus", index:"billStatus", width:50, sorttype:"int", align:"center",
                                formatter:function(cellvalue, options, rowObject){
                                   if(cellvalue == 0){
                                       return '<span class="badge">未结算</sapn>';
                                   }else{
                                       return '<span class="badge badge-primary">已结算</span>';
                                   }
                                }
                           },
                           {width:50, align:"center", formatter:function(cellvalue, options, rowObject){
                                         //return '<button type="button" class="btn btn-warning btn-xs" onclick="modify_bill('+ rowObject.id +');">修改</button>&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-xs" onclick="delete_bill('+ rowObject.id +');">删除</button>';
                                         return '<button type="button" class="btn btn-warning btn-xs" onclick="modify_bill('+ rowObject.id +');">修改';
                                      } 
                           },
                 ],
                 pager:"#page_bill_list",
                 viewrecords:true,          //是否要显示总记录数
                 caption:"账单列表",          //表格名称
                 hidegrid:false             //启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
            });
            
            $(window).bind("resize",function(){
                 var width=$(".jqGrid_wrapper").width();
                 $("#table_bill_list").setGridWidth(width);
           });
    
     });
    
     //修改账单信息
     function modify_bill(id){
        //跳转至修改账单页面
		location.href = "<%=basePath%>bill/modifyBill.do?id="+id;
     }
     
     //新增账单信息
     function add_bill(){
        //跳转至新增账单页面
	    location.href = "<%=basePath%>bill/addBill.do";
     }
           
     //删除账单信息
     function delete_bill(id){
        var flag = confirm("确定要删除此账单信息吗？");
        if(flag){
           var url = "<%=basePath%>bill/deleteBill.do";
           $.post(url, {"id":id}, function(data) {
			   var jsonReturn = eval("(" + data + ")");
			   if(jsonReturn.code == "1"){   //删除成功
			       alert(jsonReturn.msg);			
			       //跳转至账单列表页面
				   location.href = "<%=basePath%>sysUser/userList.do"; 
			   }else{                        //删除失败
				    alert(jsonReturn.msg);
		       }
		   });
        }
     }
     
     //结算
     function balance(){
        //获取选中的radio对象
        var checkedObj = $('input[name="radio_list"]:checked');
        //获取选中的radio的id
        var id = checkedObj.attr("id");
        if(id == undefined){
            alert("请选择一条账单记录!");
        }else{
            var flag = confirm("确定要结算此账单吗？");
            if(flag){
               //跳转至结算页面
			   location.href = "<%=basePath%>bill/balance.do?id="+id; 
            }else{
               checkedObj.attr("checked", false);
            }
        }
     }
     
    </script>
  </head>
  
  <body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-content">
                        <button class="btn btn-info" type="button" onclick="add_bill();">点餐</button>
                        &nbsp;&nbsp;&nbsp;
                        <button class="btn btn-success" type="button" onclick="balance();">结算</button>
                        <div class="jqGrid_wrapper">
                            <table id="table_bill_list" style="font-size:13px;"></table>
                            <div id="page_bill_list"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
