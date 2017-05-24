
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
    <%--base href="<%=basePath%>">--%>
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
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/index.css">
    <link rel="stylesheet" href="../css/view.css">
    <script src="../js/util.js"></script>
</head>
<body>
<form class="ui form" method="post" action="view.action" id="main">
    <h3 class="ui header">密码管理</h3>
    <div  id="menu">
        <div class="ui small menu">
            <div class="right menu">
                <div class="item">
                    <div class="ui button compact teal" id="search" style="text-align: center">
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
    <div class="ui four item menu" style="text-align: center!important;width: 100%;position: fixed;bottom: 0px">
        <div class="item">
            <button class="ui blue basic button height" id="previous">上页</button>
            <select class="ui search compact dropdown height">


            </select>
            <div class="ui buttons height">
                <button class="ui button" id="goto">跳转</button>
            </div>
            <button class="ui blue basic button height" id="next">下页</button>
        </div>
    </div>
</form>
<script>

</script>
</body>
</html>