<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
    if(!path.endsWith("/")){
           path=path+"/";
    }
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    String uiPath=basePath+"resources/";
%>

<!-- CSS样式 -->
<link rel="shortcut icon" href="<%=uiPath%>common/css/icon/favicon.ico">
<link href="<%=uiPath%>common/css/animate.min.css" rel="stylesheet">
<link href="<%=uiPath%>common/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=uiPath%>common/css/bootstrap.minb16a.css?v=3.3.5" rel="stylesheet">
<link href="<%=uiPath%>common/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="<%=uiPath%>common/css/style.min1fc6.css?v=4.0.0" rel="stylesheet">

<link href="<%=uiPath%>common/css/validform/valid.css" rel="stylesheet">

<link href="<%=uiPath%>common/css/plugins/jqgrid/ui.jqgridffe4.css?0820" rel="stylesheet">
<link href="<%=uiPath%>common/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="<%=uiPath%>common/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">
<link href="<%=uiPath%>common/css/plugins/ligerUI-tree/ligerui-all.css" rel="stylesheet">

<!-- JavaScript -->
<script type="text/javascript" src="<%=uiPath%>common/js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/jquery-ui-1.10.4.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/jquery.autogrow-textarea.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/jquery.history.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/jquery.min63b9.js?v=2.1.4"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/jquery.noty.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/jquery.raty.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/jquery.uploadify-3.1.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/bootstrap.minb16a.js?v=3.3.5"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/contabs.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/content.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/content.mine209.js?v=1.0.0"></script>

<script type="text/javascript" src="<%=uiPath%>common/js/validform/Validform_v5.3.1_min.js"></script>

<script type="text/javascript" src="<%=uiPath%>common/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/plugins/pace/pace.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/plugins/peity/jquery.peity.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/plugins/jqgrid/i18n/grid.locale-cnffe4.js?0820"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/plugins/jqgrid/jquery.jqGrid.minffe4.js?0820"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/plugins/treeview/bootstrap-treeview.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/plugins/ligerUI-tree/base.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/plugins/ligerUI-tree/ligerui.all.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/plugins/ligerUI-tree/ligerui.min.js"></script>

<script type="text/javascript" src="<%=uiPath%>common/js/hplus.min1fc6.js?v=4.0.0"></script>
<script type="text/javascript" src="<%=uiPath%>common/js/welcome.min.js"></script>

<script type="text/javascript" src="<%=uiPath%>common/plugins/fullavatareditor/scripts/fullAvatarEditor.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/plugins/fullavatareditor/scripts/jQuery.Cookie.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/plugins/fullavatareditor/scripts/swfobject.js"></script>
<script type="text/javascript" src="<%=uiPath%>common/plugins/fullavatareditor/scripts/test.js"></script>
