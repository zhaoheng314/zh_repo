<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dish_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

   <script>
     
        var categoryId ;
	    $(function(){
	        var url = "<%=basePath%>dish/getCategoryTree.do";
	        //异步请求获取菜品类别树
	        $.post(url, function(data){
	            //初始化树
	            $("#category_tree").treeview({
	    	                        color:"#428bca",
	    	                        expandIcon:"glyphicon glyphicon-stop",
	    	                        collapseIcon:"glyphicon glyphicon-unchecked",
	    	                        nodeIcon:"glyphicon glyphicon-user",
	    	                        showTags:false,
	    	                        data:data,
	    	                        onNodeSelected:function(event, node){	    	                           
	    	                           //获取选择的菜品的id
	    	                           categoryId = node.id;
	    	                           //获取菜品列表
	    	                           get_dish_list();
	    	                        }
	    	    });
	        });
	        	
	    	//初始化菜品列表
	    	get_dish_list();
	    }); 
	    
	    function get_dish_list(){
	        $.jgrid.defaults.styleUI="Bootstrap";	      
	        if(undefined == categoryId){
	           categoryId = null;
	        }	       
            var url = "<%=basePath%>dish/getDishList.do";      
            $("#table_dish_list").jqGrid('setGridParam',{
                   datatype:"json",
                   postData:{categoryId:categoryId},
                   page:1,
            }).trigger("reloadGrid");   //重新加载表格
            $("#table_dish_list").jqGrid({
                 jsonReader:{
                    root:"rows",        //数据
                    records:"total",    //记录总数
                    total:"totalpage"   //总页数
                 },
                 url:url,               //获取数据的地址
                 postData:{categoryId:categoryId}, //参数数组
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
                 colNames:["菜品名称","单价","菜品描述","图片URL","创建时间","修改时间","操作"],
                                        //列的属性
                 colModel:[{name:"dishName", index:"dishName", width:70},
                           {name:"price", index:"price", width:50},
                           {name:"dishDesc", index:"dishDesc", width:100},
                           {name:"imageUrl", index:"imageUrl", width:100},
                           {name:"createDate", index:"createDate", width:100, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},
                           {name:"updateDate", index:"updateDate", width:100, sorttype:"date", formatter:"date", formatoptions:{srcformat:"Y-m-d H:i:s", newformat:"Y-m-d H:i:s"}},  
                           {width:70, formatter:function(cellvalue, options, rowObject){
                                         return '<button type="button" class="btn btn-warning btn-xs" onclick="modify_dish('+ rowObject.id +');">修改</button>&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-xs" onclick="delete_dish('+ rowObject.id +');">删除</button>';
                                      } 
                           }
                 ],
                 pager:"#page_dish_list",
                 viewrecords:true,          //是否要显示总记录数
                 caption:"菜品列表",          //表格名称
                 hidegrid:false             //启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
            });
            
            /*
            $(window).bind("resize",function(){
                 var width = $(".jqGrid_wrapper").width();         
                 $("#table_dish_list").setGridWidth(width);          
            });
           */
	    }
	    
	    //新增菜品信息
	    function add_dish(){	  
	       //跳转至新增菜品页面
	       location.href = "<%=basePath%>dish/addDish.do?"; 
	    }
	    
	    //修改菜品信息
	    function modify_dish(id){
	        //跳转至修改菜品信息页面
	        location.href = "<%=basePath%>dish/modifyDish.do?id="+id;
	    }
	   
	    //删除菜品信息
	    function delete_dish(id){
	        var flag = confirm("确定要删除此菜品信息吗？");
	        if(flag){
	           var url = "<%=basePath%>dish/deleteDish.do";
	           $.post(url, {"id":id}, function(data){
	                var jsonReturn = eval("(" + data + ")");
			        if(jsonReturn.code == "1"){   //删除成功
			            alert(jsonReturn.msg);			
			            //刷新菜品列表页面
				        get_dish_list();
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
                  <div id="category_tree" class="test" ></div>                   
               </div>
            </div>  
         </div>
         <div class="col-sm-9"> 
            <div class="ibox ">
               <div class="ibox-content"> 
                   <button class="btn btn-info" type="submit" onclick="add_dish();">新增</button>
                   <div class="jqGrid_wrapper">
                      <table id="table_dish_list" style="font-size:12px;"></table>
                      <div id="page_dish_list"></div>
                   </div>
               </div>
            </div>
        </div>
      </div>
  </div>
</body>
</html>
