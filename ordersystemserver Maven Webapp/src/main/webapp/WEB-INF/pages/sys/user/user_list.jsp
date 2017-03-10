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
        $(document).ready(function(){
            $.jgrid.defaults.styleUI="Bootstrap";
            var url = "<%=basePath%>sysUser/getUserList.do";
            $("#table_user_list").jqGrid({
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
                 colNames:["","用户名","真实姓名","性别","联系电话","邮箱","地址","创建时间","修改时间","备注","操作",""],
                                        //列的属性
                 colModel:[{width:20, formatter:function(cellvalue, options, rowObject){
                                         return '<input id="' + rowObject.id + '" name="radio_list" type="radio">';
                                      }
                           },
                           {name:"username", index:"username", width:60},
                           {name:"realname", index:"realname", width:70},
                           {name:"gender", index:"gender", width:40, sorttype:"int", 
                                formatter:function(cellvalue, options, rowObject){
                                   if(cellvalue == 0){
                                       return "男";
                                   }else{
                                       return "女";
                                   }
                                }
                           },
                           {name:"phone", index:"phone", width:80, align:"left"},
                           {name:"email", index:"email", width:90, align:"left"},
                           {name:"address", index:"address", width:100, align:"left"},
                           {name:"createDate", index:"createDate", width:120, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},
                           {name:"updateDate", index:"updateDate", width:120, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},
                           {name:"remark", index:"remark", width:60, sortable:false},
                           {width:80, align:"center", formatter:function(cellvalue, options, rowObject){
                                         return '<button type="button" class="btn btn-warning btn-xs" onclick="modify_user('+ rowObject.id +');">修改</button>&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-xs" onclick="delete_user('+ rowObject.id +');">删除</button>';
                                      } 
                           },
                           {name:"userStatus", index:"userStatus",hidden:true}
                 ],
                 pager:"#page_user_list",
                 viewrecords:true,          //是否要显示总记录数
                 caption:"用户列表",          //表格名称
                 hidegrid:false             //启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
            });
            
            $(window).bind("resize",function(){
                 var width=$(".jqGrid_wrapper").width();
                 $("#table_user_list").setGridWidth(width);
           });
    
     });
    
     //修改用户信息
     function modify_user(id){
        //跳转至修改用户页面
		location.href = "<%=basePath%>sysUser/modifySysUser.do?id="+id;
     }
     
     //新增用户信息
     function add_user(){
        //跳转至新增用户页面
	    location.href = "<%=basePath%>sysUser/addSysUser.do";
     }
           
     //删除用户信息
     function delete_user(id){
        var flag = confirm("确定要删除此用户信息吗？");
        if(flag){
           var url = "<%=basePath%>sysUser/deleteSysUser.do";
           $.post(url, {"id":id}, function(data) {
			   var jsonReturn = eval("(" + data + ")");
			   if(jsonReturn.code == "1"){   //删除成功
			       alert(jsonReturn.msg);			
			       //跳转至用户列表页面
				   location.href = "<%=basePath%>sysUser/userList.do"; 
			   }else{                        //删除失败
				    alert(jsonReturn.msg);
		       }
		   });
        }
     }
     
     //重置用户密码
     function reset_password(){
        //获取选中的radio对象
        var checkedObj = $('input[name="radio_list"]:checked');
        //获取选中的radio的id
        var id = checkedObj.attr("id");
        if(id == undefined){
            alert("请选择一行记录!");
        }else{
            var flag = confirm("确定要重置选中的用户的密码吗？");
            if(flag){
               var url = "<%=basePath%>sysUser/resetPassword.do";
               $.post(url, {"id":id}, function(data) {
			       var jsonReturn = eval("(" + data + ")");
			       if(jsonReturn.code == "1"){   //重置密码成功
			           alert(jsonReturn.msg);			
			           //跳转至用户列表页面
				       location.href = "<%=basePath%>sysUser/userList.do"; 
			       }else{                        //重置密码失败
				       alert(jsonReturn.msg);
		           }
		       });
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
                        <button class="btn btn-info" type="button" onclick="add_user();">新增</button>
                        &nbsp;&nbsp;&nbsp;
                        <button class="btn btn-success" type="button" onclick="reset_password();">重置密码</button>            
                        <div class="jqGrid_wrapper">
                            <table id="table_user_list" style="font-size:13px;"></table>
                            <div id="page_user_list"></div>
                        </div>
                      
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
