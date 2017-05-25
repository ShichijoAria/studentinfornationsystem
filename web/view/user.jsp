
<%--
  Created by IntelliJ IDEA.
  User: Ace
  Date: 2017/4/5
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String center="style=\"text-align: center;\"";
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta charset="UTF-8">
    <meta charset="UTF-8">
    <title>学生信息系统</title>
    <script src="../modules/jquery.js"></script>
    <link rel="stylesheet" href="../modules/semantic.min.css">
    <script src="../modules/semantic.min.js"></script>
    <link rel="stylesheet" href="../css/index.css">
    <link rel="stylesheet" href="../css/view.css">
</head>
<body>
<form class="ui form" method="post" action="view.action" id="main">
    <h2 class="ui header">
        <i class="unlock alternate icon"></i>
        <div class="content">密码管理</div>
        <div class="sub header">password manager</div>
    </h2>
    <div  id="menu">
        <div class="ui small menu">
            <div class="right menu">
                <div class="item">
                    <div class="ui teal icon button">
                        <i class="search icon"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="ui segment"  id="segment">
        <table class="ui samll padded table">
            <thead>
            <tr>
                <th>
                    <div class="ui checkbox">
                        <input type="checkbox" name="switch" id="checkall"><label></label>
                    </div>
                </th>
                <th <%=center%>>用户编号</th>
                <th <%=center%>>用户名称</th>
                <th <%=center%>>用户权限</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <div class="ui tiny green inverted three item menu very padded" id="foot">
        <a class="item">
            <i class="long arrow left icon"></i>
            上页
        </a>
        <a class="item">
            <select class="ui dropdown compact">
                <option value="">页数</option>
                <option value="1">1</option>
                <option value="2">2</option>
            </select>
        </a>
        <a class="item">
            下页
            <i class="long arrow right icon"></i>
        </a>
    </div>
</form>
<script>

    $('.ui.dropdown')
        .dropdown()
    ;/*下拉菜单初始化*/
    $(window).resize(function() {//缩放事件

        $('#foot').outerWidth($('body').outerWidth())
    });
</script>
</body>
</html>