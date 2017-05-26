
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
    <link rel="stylesheet" href="../modules/semantic.css">
    <script src="../modules/semantic.min.js"></script>
    <link rel="stylesheet" href="../css/index.css">
    <link rel="stylesheet" href="../css/view.css">
    <link rel="stylesheet" href="../css/general.css">
</head>
<body style="background-color: #fff;">
<div class="ui top left vertical menu sidebar">
    <a class="ui right grey large corner label">
        <i class="close icon"></i>
    </a>
    <div class="content">
        <div class="ui grid stackable centered segment three column vertical container">
                <div class="column">
                    <div class="ui right pointing black basic label">
                        用户编号
                    </div>
                    <div class="ui input">
                        <input class="input" type="text" name="search.id" value="${search.id}">
                        <i class="icon"></i>
                    </div>
                </div>
                <div class="column">
                    <div class="ui right pointing black basic label">
                        用户名称
                    </div>
                    <div class="ui input">
                        <input class="input" type="text" name="search.name" value="${search.name}">
                    </div>
                </div>
                <div class="column">
                    <div class="ui right pointing black basic label">
                        用户角色
                    </div>
                    <div class="ui input">
                        <select class="ui fluid dropdown" name="search.type">
                            <option value="">权限</option>
                            <option value="3"
                            >学生</option>
                            <option value="2"
                            >教师</option>
                            <option value="1"
                            >管理员</option>
                        </select>
                    </div>
                </div>
            <div style="text-align: center;width:100%;padding-top: 5%">
                <button class="ui button blue">查询</button>
                <a class="ui button" id="reset">重置</a>
            </div>
        </div>
    </div>
</div>
<form class="ui form pusher" method="post" action="view.action" id="main">
    <div>
        <h2 class="ui header">
            <i class="unlock alternate icon"></i>
            <div class="content">密码管理</div>
            <div class="sub header">password manager</div>
        </h2>
        <div  id="menu">
            <div class="ui small menu">
                <div class="right menu">
                    <div class="item">
                        <div id="search" class="ui teal icon button">
                            <i class="search icon"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="ui segment"  id="segment">
        <table class="very small unstackable compact ui samll table">
            <thead>
            <tr class="center aligned">
                <th>
                    <div class="ui checkbox">
                        <input type="checkbox" name="switch" id="checkall"><label></label>
                    </div>
                </th>
                <th class="six wide">用户编号</th>
                <th class="five wide">用户名称</th>
                <th class="five wide">用户角色</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <div class="ui tiny green inverted three item menu " id="foot">
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
    $('form').outerWidth($('body').width())
    $('#foot').width($('body').width())
    $('.ui.input').outerWidth($('input').outerWidth())
    $('.ui.grid.stackable.centered.segment.three.column.vertical.container').outerWidth($('form').outerWidth())
    $('.ui.dropdown')
        .dropdown()
    ;/*下拉菜单初始化*/
    $(window).resize(function() {//缩放事件
        $('form').outerWidth($('body').width())
        $('#foot').width($('body').width())
        $('.ui.input').outerWidth($('input').outerWidth())
    });
    $('.ui.sidebar')
    .sidebar({
        context: $('body'),
        useLegacy:true,
    })
    $('#search,.close.icon').click(function () {
        $('.ui.sidebar')
            .sidebar('toggle')
    })

</script>
</body>
</html>