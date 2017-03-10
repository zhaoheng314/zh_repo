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
     
        var pid ;
	    $(function(){
	        var url = "<%=basePath%>sysPri/getParentPri.do";
	        //异步请求获取父权限菜单
	        $.post(url, function(data){
	            //初始化树
	            $("#privilege_tree").treeview({
	    	                        color:"#428bca",
	    	                        expandIcon:"glyphicon glyphicon-stop",
	    	                        collapseIcon:"glyphicon glyphicon-unchecked",
	    	                        nodeIcon:"glyphicon glyphicon-user",
	    	                        showTags:false,
	    	                        data:data,
	    	                        onNodeSelected:function(event, node){	    	                           
	    	                           //获取选择的权限的id
	    	                           pid = node.id;
	    	                           //获取权限列表
	    	                           get_privilege_list();
	    	                        }
	    	    });
	        });
	        	
	    	//初始化权限列表
	    	get_privilege_list();
	    }); 
	    
	    function get_privilege_list(){
	        $.jgrid.defaults.styleUI="Bootstrap";	      
	        if(undefined == pid){
	           pid = null;
	        }	       
            var url = "<%=basePath%>sysPri/getPriList.do";      
            $("#table_privilege_list").jqGrid('setGridParam',{
                   datatype:"json",
                   postData:{parentId:pid},
                   page:1,
            }).trigger("reloadGrid");   //重新加载表格
            $("#table_privilege_list").jqGrid({
                 jsonReader:{
                    root:"rows",        //数据
                    records:"total",    //记录总数
                    total:"totalpage"   //总页数
                 },
                 url:url,               //获取数据的地址
                 postData:{parentId:pid},  //参数数组
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
                 colNames:["权限名称","权限URL","是否系<br/>统权限","创建时间","修改时间","操作","启用/禁用"],
                                        //列的属性
                 colModel:[{name:"privilegeName", index:"privilegeName", width:70},
                           {name:"privilegeUrl", index:"privilegeUrl", width:100},
                           {name:"ifSys", index:"ifSys", width:60, sorttype:"int", 
                                formatter:function(cellvalue, options, rowObject){
                                   if(cellvalue == 0){
                                       return "否";
                                   }else{
                                       return "是";
                                   }
                                }
                           },          
                           {name:"createDate", index:"createDate", width:100, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},
                           {name:"updateDate", index:"updateDate", width:100, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},  
                           {width:70, formatter:function(cellvalue, options, rowObject){
                                         return '<button type="button" class="btn btn-warning btn-xs" onclick="modify_privilege('+ rowObject.id +');">修改</button>&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-xs" onclick="delete_privilege('+ rowObject.id +');">删除</button>';
                                      } 
                           },
                           {name:"privilegeStatus", index:"privilegeStatus",width:70, align:"center", formatter:function(cellvalue, options, rowObject){
                                   if(cellvalue == 0){
                                       return '<button type="button" class="btn btn-primary btn-sm" onclick="change_status('+ rowObject.id +');">启用';
                                   }else{
                                       return '<button type="button" class="btn btn-white btn-sm" onclick="change_status('+ rowObject.id +');">禁用';
                                   }
                                }
                           }
                 ],
                 pager:"#page_privilege_list",
                 viewrecords:true,          //是否要显示总记录数
                 caption:"权限列表",          //表格名称
                 hidegrid:false             //启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
            });
            
            /*
            $(window).bind("resize",function(){
                 var width = $(".jqGrid_wrapper").width();         
                 $("#table_privilege_list").setGridWidth(width);          
            });
           */
	    }
	    
	    //新增权限信息
	    function add_privilege(){	  
	        
	        if(undefined == pid){
	           alert("请选择父权限!");
	        }else if(pid >= 0){
	           //跳转至新增权限页面
	           location.href = "<%=basePath%>sysPri/addSysPri.do?parentId="+pid; 
	        }
	    }
	    
	    //修改权限信息
	    function modify_privilege(id){
	        //跳转至修改权限信息页面
	        location.href = "<%=basePath%>sysPri/modifySysPri.do?id="+id;
	    }
	   
	    //删除权限信息
	    function delete_privilege(id){
	        var flag = confirm("确定要删除此权限信息吗？");
	        if(flag){
	           var url = "<%=basePath%>sysPri/deleteSysPri.do";
	           $.post(url, {"id":id}, function(data){
	                var jsonReturn = eval("(" + data + ")");
			        if(jsonReturn.code == "1"){   //删除成功
			            alert(jsonReturn.msg);			
			            //刷新权限列表页面
				        get_privilege_list();
			        }else{                        //删除失败
				        alert(jsonReturn.msg);
		            }
	           });
	        }
	    }
	    
	    //启/禁用权限
	    function change_status(id){
	        var url = "<%=basePath%>sysPri/changeStatus.do";
	        $.post(url, {"id":id}, function(data){
	            var jsonReturn = eval("(" + data + ")");
			        if(jsonReturn.code == "1"){   //修改状态成功
			            alert(jsonReturn.msg);			
			            //刷新权限列表页面
				        get_privilege_list();
			        }else{                        //修改状态失败
				        alert(jsonReturn.msg);
		            }
	        });
	    }
    </script>
  </head> 
  <body class="gray-bg">
  <div class="wrapper wrapper-content  animated fadeInRight"> 
    <div class="row"> 
        <div class="col-sm-3">
            <div class="ibox float-e-margins">                        
               <div class="ibox-content">                  
                  <div id="privilege_tree" class="test" ></div>                   
               </div>
            </div>  
         </div>
         <div class="col-sm-9"> 
            <div class="ibox ">
               <div class="ibox-content"> 
                   <button class="btn btn-info" type="submit" onclick="add_privilege();">新增</button>
                   <div class="jqGrid_wrapper">
                      <table id="table_privilege_list" style="font-size:12px;"></table>
                      <div id="page_privilege_list"></div>
                   </div>
               </div>
            </div>
        </div>
      </div>
  </div>
</body>
</html>
