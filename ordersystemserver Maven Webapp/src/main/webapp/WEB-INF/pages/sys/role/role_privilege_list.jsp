<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'role_privilege_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script>
        
        var selectId; //选中的角色编号
        
        $(document).ready(function(){
       
            $.jgrid.defaults.styleUI="Bootstrap";
            var url = "<%=basePath%>sysRole/getRoleList.do";
            $("#table_role_list").jqGrid({
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
                 colNames:["角色名称","角色代码","角色描述","是否系统角色","操作"],
                                        //列的属性
                 colModel:[{name:"roleName", index:"roleName", width:70},
                           {name:"roleCode", index:"roleCode", width:60},
                           {name:"roleDesc", index:"roleDesc", width:110},
                           {name:"ifSys", index:"ifSys", width:100, sorttype:"int", 
                                formatter:function(cellvalue, options, rowObject){
                                   if(cellvalue == 0){
                                       return "否";
                                   }else{
                                       return "是";
                                   }
                                }
                           },
                           {width:50, align:"center", formatter:function(cellvalue, options, rowObject){
                                         return '<button type="button" class="btn btn-primary btn-xs" onclick="get_role_privilege('+ rowObject.id +');">查看权限</button>';
                                      } 
                           }
                 ],
                 pager:"#page_role_list",
                 viewrecords:true,          //是否要显示总记录数
                 caption:"角色列表",          //表格名称
                 hidegrid:false,            //启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
                 onSelectRow:function(rowid, status){
                      selectId = rowid;
                 }
            });
            
            $(window).bind("resize",function(){
                 var width=$(".jqGrid_wrapper").width();
                 $("#table_role_list").setGridWidth(width);
           });
    
     });
          
     function get_role_privilege(roleId){
        var url = "<%=basePath%>rolePri/getRolePriList.do?roleId="+roleId;
        //异步请求获取角色拥有的权限信息
        $("#privilege_tree").ligerTree({  
            url:url, 
            checkbox:true,
            idFieldName :'id',
          	parentIDFieldName :'pid',
          	enabledCompleteCheckbox:false
        });
        $("#privilege_div").css('display','block');
     }
     
     //授权
     function grant(){
       
       var flag = window.confirm("确定为当前角色授权吗?")
       if(flag){
          //获取权限树对象
          var treeObj = $("#privilege_tree").ligerTree();
          //获取选中的树节点
          var selectNodes = treeObj.getChecked();
          if(selectNodes.length <= 0){
             alert("您没有选择任何权限!");
             return ;
          }
          //定义权限编号字符串
          var privilegeIds = "";
          //遍历选中的树节点数组
          for(var i = 0; i < selectNodes.length ; i++){
              privilegeIds += selectNodes[i].data.id;
              privilegeIds += ",";
          }
          var url = "<%=basePath%>rolePri/addRolePri.do";
          //执行授权操作
          $.post(url, {"roleId":selectId, "privilegeIds":privilegeIds}, function(data){
                var jsonReturn = eval("(" + data + ")");
                if(jsonReturn.code == "1"){
                   alert("授权成功!");
                }else{
                   alert("授权失败!");
                }				
			    //重新加载树						   			
		        treeObj.reload();
          });
       }else{
          return;
       }
     }
     
     //回收
     function recovery(){
        var flag = window.confirm("确定回收当前角色的权限吗?");
        if(flag){
            //获取权限树对象
            var treeObj = $("#privilege_tree").ligerTree();
            //获取选中的树节点
            var selectNodes = treeObj.getChecked();
            if(selectNodes.length <= 0){
               alert("当前角色无任何权限，无需回收!");
               return ;
            }
  
            var url = "<%=basePath%>rolePri/deleteByRoleId.do";
            //执行回收权限操作
            $.post(url, {"roleId":selectId}, function(data){
                var jsonReturn = eval("(" + data + ")");			
			     if(jsonReturn.code == "1"){
                   alert("回收成功!");
                }else{
                   alert("回收失败!");
                }	
			    //重新加载树						   			
		        treeObj.reload();
           });
        }else{
           return;
        }
     }
    </script>
  </head>
  
  <body class="gray-bg">
  <div class="wrapper wrapper-content  animated fadeInRight"> 
    <div class="row"> 
         <div class="col-sm-9"> 
            <div class="ibox ">
               <div class="ibox-content"> 
                   <div class="jqGrid_wrapper">
                      <table id="table_role_list" style="font-size:12px;"></table>
                      <div id="page_role_list"></div>
                   </div>
               </div>
            </div>
        </div>
        <div class="col-sm-3" id="privilege_div" style="display: none">
            <div class="ibox float-e-margins">                        
                 <div class="ibox-content">                          
                    <div id="privilege_tree"></div>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-info btn-sm" onclick="grant()" >授权</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
                    <button type="button" class="btn btn-white btn-sm" onclick="recovery()" >回收</button>                 
                 </div>                          
               </div>
            </div> 
         </div>
      </div>
  </div>
</body>
</html>
