<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'blank.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script>
     
        var roleId ;
	    $(function(){
	        var url = "<%=basePath%>sysRole/getAllRoles.do";
	        //异步请求获取父权限菜单
	        $.post(url, function(data){
	            //初始化树
	            $("#role_tree").treeview({
	    	                        color:"#428bca",
	    	                        expandIcon:"glyphicon glyphicon-stop",
	    	                        collapseIcon:"glyphicon glyphicon-unchecked",
	    	                        nodeIcon:"glyphicon glyphicon-user",
	    	                        showTags:false,
	    	                        data:data,
	    	                        onNodeSelected:function(event, node){	    	                           
	    	                           //获取选择的角色的id
	    	                           roleId = node.id;
	    	                           //获取角色用户列表
	    	                           get_role_user_list();
	    	                        }
	    	    });
	        });
	        	
	    	//初始化角色用户列表
	    	get_role_user_list();
	    }); 
	    
	    function get_role_user_list(){
	        $.jgrid.defaults.styleUI="Bootstrap";	      
	        if(undefined == roleId){
	           roleId = null;
	        }	       
            var url = "<%=basePath%>roleUser/getRoleUserList.do";      
            $("#table_role_user_list").jqGrid('setGridParam',{
                   datatype:"json",
                   postData:{roleId:roleId},
                   page:1,
            }).trigger("reloadGrid");   //重新加载表格
            $("#table_role_user_list").jqGrid({
                 jsonReader:{
                    root:"rows",        //数据
                    records:"total",    //记录总数
                    total:"totalpage"   //总页数
                 },
                 url:url,               //获取数据的地址
                 postData:{roleId:roleId},  //参数数组
                 datatype:"json",       //数据类型
                 mtype:"GET",           //ajax提交方式 
                 height:"100%",         //表格高度
                 rownumbers:true,       //如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'
                 multiselect:false,     //是否显示复选框
                 autowidth:true,     
                 shrinkToFit:true,
                 //autoScroll:true, 
                 rowNum:10,             //页的大小
                 rowList:[10,20,30],    //页的大小集合
                                        //列名
                 colNames:["角色名称","用户名称","创建时间","操作"],
                                        //列的属性
                 colModel:[{name:"roleName", index:"roleName", width:80},
                           {name:"username", index:"username", width:80},                                  
                           {name:"createDate", index:"createDate", width:100, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},                          
                           {width:70, formatter:function(cellvalue, options, rowObject){
                                         return '<button type="button" class="btn btn-danger btn-xs" onclick="delete_role_user('+ rowObject.id +');">删除</button>';
                                      } 
                           },
                          
                 ],
                 pager:"#page_role_user_list",
                 viewrecords:true,          //是否要显示总记录数
                 caption:"角色用户列表",       //表格名称
                 hidegrid:false             //启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
            });
            
	    }
	    
	    //给角色添加用户
	    function show_no_role_user(){	  
	        
	        if(undefined == roleId){
	           alert("请选择一个角色!");
	        }else{
	           $("#add").attr("href", "#modalDialog");
	           //获取未授予角色的用户列表
	           var url = "<%=basePath%>roleUser/getNoRoleUserList.do";
	           $("#table_no_role_user_list").jqGrid('setGridParam',{
                   datatype:"json",
                   page:1,
               }).trigger("reloadGrid");   //重新加载表格                
               $("#table_no_role_user_list").jqGrid({
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
                    pagerpos:"center",     //指定分页栏位置，默认值center
                    //autoScroll:true, 
                    rowNum:10,             //页的大小
                    rowList:[10,20,30],    //页的大小集合
                                           //列名
                    colNames:["","用户名","真实姓名","性别","创建时间","修改时间"],
                                        //列的属性
                    colModel:[{width:30, align:"center", formatter:function(cellvalue, options, rowObject){
                                            return '<input id="' + rowObject.userId + '" name="checkbox" value="' + rowObject.userId + '" type="checkbox">';
                                         }
                              },
                              {name:"username", index:"username", width:80},
                              {name:"realname", index:"realname", width:100},
                              {name:"gender", index:"gender", width:50, sorttype:"int", 
                                   formatter:function(cellvalue, options, rowObject){
                                      if(cellvalue == 0){
                                          return "男";
                                      }else{
                                          return "女";
                                      }
                                   }
                              },                          
                              {name:"createDate", index:"createDate", width:180, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},
                              {name:"updateDate", index:"updateDate", width:180, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},                           
                             ],
                    pager:"#page_no_role_user_list",
                    viewrecords:true,          //是否要显示总记录数
                    caption:"用户列表",          //表格名称
                    hidegrid:false             //启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
               });
               
	        }
	    }
	    	
	    function add_role_user(){
	        var userIds = "";
	        //获取选中的用户编号并拼接成字符串
	        $("input[name='checkbox']").each(function(){ 
                if($(this).prop("checked")){               
                    userIds += $(this).attr("id") + ",";
                }
            });
            if(userIds.length > 0){
                 var url = "<%=basePath%>roleUser/addRoleUser.do";
                 //保存角色用户信息
                 $.post(url, {"roleId":roleId, "userIds":userIds}, function(data){
                      var jsonReturn = eval("(" + data + ")");
			          if(jsonReturn.code == "1"){   //保存成功
			              alert(jsonReturn.msg);
			              //关闭模态框
			              $("#modalDialog").modal("hide");				
			              //刷新角色用户列表页面
				          get_role_user_list();
			          }else{                        //保存失败
				          alert(jsonReturn.msg);
		              }
                 });
            }else{
                 alert("请选择用户!");
            }
	    }
	    	
	    //删除用户的角色
	    function delete_role_user(id){
	        var flag = confirm("确定删除此用户的角色吗？");
	        if(flag){
	           var url = "<%=basePath%>roleUser/deleteRoleUser.do";
	           $.post(url, {"id":id}, function(data){
	                var jsonReturn = eval("(" + data + ")");
			        if(jsonReturn.code == "1"){   //删除成功
			            alert(jsonReturn.msg);			       		
			            //刷新角色用户列表页面
				        get_role_user_list();
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
        <div class="col-sm-3">
            <div class="ibox float-e-margins">                        
               <div class="ibox-content">                  
                  <div id="role_tree" class="test" ></div>                   
               </div>
            </div>  
         </div>
         <div class="col-sm-9"> 
            <div class="ibox ">
               <div class="ibox-content"> 
                   <button class="btn btn-info" id="add" type="button" href="" data-toggle="modal" onclick="show_no_role_user();">新增</button>
                   <div class="jqGrid_wrapper">
                      <table id="table_role_user_list" style="font-size:12px;"></table>
                      <div id="page_role_user_list"></div>
                   </div>
               </div>
            </div>
        </div>
      </div>
  </div>
  <div class="modal fade" id="modalDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog" style="width: 720px;">
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hiddden="true">&times;</button>
                 <h4 class="modal-title" id="myModalLabel">给角色添加用户</h4>
             </div>
             <div class="modal-body">
                 <div class="jqGrid_wrapper" align="center">
                      <table id="table_no_role_user_list" ></table>
                      <div id="page_no_role_user_list"></div>
                 </div>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-primary" onclick="add_role_user();">确定</button>
                 <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
             </div>
         </div>
     </div>
  </div>
</body>
</html>
