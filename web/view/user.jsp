
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
        <div class="ui accordion" style="width: 100%">
            <div class="title" style="height:0px; padding:0 0 0 0">
            </div>
            <div class="content">
                <div class="ui grid segment">
                    <div class="row">
                        <div class="ui two wide column input fieldinput">用户编号</div>
                        <div class="ui three wide column input">
                            <input class="input" type="text" name="search.id" value="${search.id}">
                        </div>
                        <div class="ui two wide column input fieldinput">用户名称</div>
                        <div class="ui three wide column input">
                            <input class="input" type="text" name="search.name" value="${search.name}">
                        </div>
                        <div class="ui two wide column input fieldinput">用户权限</div>
                        <div class="ui three wide column input">
                            <select class="ui dropdown" name="search.type">
                                <option value="4"></option>
                                <option value="3"
                                        <c:if test="${search.type eq '3'}">
                                            selected=/"selected/"
                                        </c:if>
                                >学生</option>
                                <option value="2"
                                        <c:if test="${search.type eq '2'}">
                                            selected=/"selected/"
                                        </c:if>
                                >教师</option>
                                <option value="1"
                                        <c:if test="${search.type eq '1'}">
                                            selected=/"selected/"
                                        </c:if>
                                >管理员</option>
                            </select>
                        </div>
                    </div>
                    <div style="text-align: center;width:100%;">
                        <button class="ui button blue">查询</button>
                        <a class="ui button" id="reset">重置</a>
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
            <%
                int j=0;
            %>
            <c:forEach items="${list}" var="bean" begin="${page.begin}" end="${page.end}">
                <tr>
                    <td>
                        <div class="ui checkbox">
                            <input type="checkbox" name="item" value="${bean.id}"><label></label>
                        </div>
                    </td>
                    <td <%=center%>><a class="rowitem" href="field.action?id=${bean.id}">${bean.id}</a></td>
                    <td <%=center%>><a class="rowitem" href="field.action?id=${bean.id}">${bean.name}</a></td>
                    <td <%=center%>><a class="rowitem" href="field.action?id=${bean.id}">${bean.type}</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="ui four item menu" style="text-align: center!important;width: 100%;position: fixed;bottom: 0px">
        <div class="item">
            <button class="ui blue basic button height" id="previous">上页</button>
            <select class="ui search compact dropdown height">
                <%
                    int i=1;
                %>
                <c:forEach items="${list}" var="bean" begin="0" end="${page.pages-1}">
                    <option value="<%=i%>" <%
                        if(request.getParameter("curpage")!=null&&request.getParameter("curpage").equals(String.valueOf(i)))
                            out.print(" selected = \"selected\"");
                        else if(request.getParameter("curpage")==null&&i==1)
                            out.print(" selected = \"selected\"");
                    %>><%=i%></option>
                    <%
                        i++;
                    %>
                </c:forEach>
            </select>
            <div class="ui buttons height">
                <button class="ui button" id="goto">跳转</button>
            </div>
            <button class="ui blue basic button height" id="next">下页</button>
        </div>
    </div>
</form>
<script>
    $('#reset').click(function () {
        $('.input').val("");
    });
    initialize("view.action");
</script>
</body>
</html>