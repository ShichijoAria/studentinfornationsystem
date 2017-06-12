
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
    String viewName="grade";
%>
<%@ include file="head.jsp"  %>
<c:if test="${sessionScope.userType!=\"2\"}">
<div class="column">
    <div class="ui right pointing black basic label">
        教师名称
    </div>
    <div class="ui input">
        <input class="input" type="text" name="searchTeaName" value="${param.searchTeaName}">
        <i class="icon"></i>
    </div>
</div>
</c:if>
<c:if test="${sessionScope.userType!=\"3\"}">
<div class="column">
    <div class="ui right pointing black basic label">
        学生名称
    </div>
    <div class="ui input">
        <input class="input" type="text" name="searchStuName" value="${param.searchStuName}">
        <i class="icon"></i>
    </div>
</div>
</c:if>
<div class="column">
    <div class="ui right pointing black basic label">
        课程名称
    </div>
    <div class="ui input">
        <input class="input" type="text" name="searchCouName" value="${param.searchCouName}">
    </div>
</div>
<div style="text-align: center;width:100%;padding-top: 5%">
    <button class="ui button blue">查询</button>
    <a class="ui button" id="reset">重置</a>
</div>
</div>
</div>
</div>
<div class="pusher">
    <h2 class="ui header">
        <i class="sitemap icon"></i>
        <div class="content">我的<c:if test="${sessionScope.userType==\"3\"}">课程</c:if>
            <c:if test="${sessionScope.userType!=\"3\"}">班级</c:if>
        </div>
        <div class="sub header">My <c:if test="${sessionScope.userType==\"3\"}">Course</c:if>
            <c:if test="${sessionScope.userType!=\"3\"}">Class</c:if>
        </div>
    </h2>
    <div  id="menu">
        <div class="ui small menu">
            <div class="left menu">

            </div>
            <div class="right menu">
                <div class="item">
                    <div id="search" class="ui teal icon button">
                        <i class="search icon"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="ui segment"  id="segment">
        <table class="very selectable celled small unstackable compact ui samll table">
            <thead>
            <tr class="center aligned">
                <th>
                    <div class="ui checkbox">
                        <input type="checkbox" name="switch" id="checkall"><label></label>
                    </div>
                </th>
                <c:if test="${sessionScope.userType==\"2\"}">
                    <th class="eight wide">学生名称</th>
                    <th class="eight wide">课程名称</th>
                </c:if>
                <c:if test="${sessionScope.userType==\"3\"}">
                    <th class="seven wide">教师名称</th>
                    <th class="seven wide">课程名称</th>
                </c:if>
                <c:if test="${sessionScope.userType==\"1\"}">
                    <th class="five wide">教师名称</th>
                    <th class="five wide">学生名称</th>
                    <th class="four wide">课程名称</th>
                </c:if>
                <c:if test="${sessionScope.userType!=\"2\"}">
                    <th class="two wide">操作</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="bean" begin="${page.begin}" end="${page.end}">
            <tr id="${bean.id}" class="center aligned">
                <td>
                    <div class="ui checkbox">
                        <input type="checkbox" name="item" value="${bean.id}"><label></label>
                    </div>
                </td>
                <c:if test="${sessionScope.userType==\"2\"}">
                <td class="td">${bean.stuName}</td>
                </c:if>
                <c:if test="${sessionScope.userType==\"3\"}">
                <td class="td">${bean.teaName}</td>
                </c:if>
                <c:if test="${sessionScope.userType==\"1\"}">
                    <td class="td">${bean.stuName}</td>
                    <td class="td">${bean.teaName}</td>
                </c:if>
                <td class="td">${bean.couName}</td>
                <c:if test="${sessionScope.userType!=\"2\"}">
                <td><div class="ui red inverted small button" style="width: 70px;margin: 2px 10px 2px 10px;">退选</div></td>
                </c:if>
            </tr>
            </c:forEach>
<%@ include file="foot.jsp"  %>