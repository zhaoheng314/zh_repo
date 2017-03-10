<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'member_list.jsp' starting page</title>
    
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
            var url = "<%=basePath%>member/getMemberList.do";
            $("#table_member_list").jqGrid({
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
                 colNames:["","会员姓名","联系电话","创建时间","修改时间","操作"],
                                        //列的属性
                 colModel:[{width:20, formatter:function(cellvalue, options, rowObject){
                                         return '<input id="' + rowObject.id + '" name="radio_list" type="radio">';
                                      }
                           },
                           {name:"memberName", index:"memberName", width:70},
                           {name:"phone", index:"phone", width:80, align:"left"},
                           {name:"createDate", index:"createDate", width:120, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},
                           {name:"updateDate", index:"updateDate", width:120, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},
                           {width:80, align:"center", formatter:function(cellvalue, options, rowObject){
                                         return '<button type="button" class="btn btn-warning btn-xs" onclick="modify_member('+ rowObject.id +');">修改</button>&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-xs" onclick="delete_member('+ rowObject.id +');">删除</button>';
                                      } 
                           },
                 ],
                 pager:"#page_member_list",
                 viewrecords:true,          //是否要显示总记录数
                 caption:"会员列表",          //表格名称
                 hidegrid:false             //启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
            });
            
            $(window).bind("resize",function(){
                 var width=$(".jqGrid_wrapper").width();
                 $("#table_member_list").setGridWidth(width);
           });
    
     });
    
     //修改会员信息
     function modify_member(id){
        //跳转至修改会员页面
		location.href = "<%=basePath%>member/modifyMember.do?id="+id;
     }
     
     //新增会员信息
     function add_member(){
        //跳转至新增会员页面
	    location.href = "<%=basePath%>member/addMember.do";
     }
           
     //删除会员信息
     function delete_member(id){
        var flag = confirm("确定要删除此会员信息吗？");
        if(flag){
           var url = "<%=basePath%>member/deleteMember.do";
           $.post(url, {"id":id}, function(data) {
			   var jsonReturn = eval("(" + data + ")");
			   if(jsonReturn.code == "1"){   //删除成功
			       alert(jsonReturn.msg);			
			       //跳转至会员列表页面
				   location.href = "<%=basePath%>member/memberList.do"; 
			   }else{                        //删除失败
				    alert(jsonReturn.msg);
		       }
		   });
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
                        <button class="btn btn-info" type="submit" onclick="add_member();">新增</button>
                        <div class="jqGrid_wrapper">
                            <table id="table_member_list" style="font-size:13px;"></table>
                            <div id="page_member_list"></div>
                        </div>
                      
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
