
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
    String viewName="teachingClass";
%>
<%@ include file="head.jsp"  %>
<div class="column">
    <div class="ui right pointing black basic label">
        课程名称
    </div>
<div class="ui input">
    <input class="input" type="text" name="searchCouName" value="${param.searchCouName}">
    <i class="icon"></i>
</div>
</div>
<div class="column">
    <div class="ui right pointing black basic label">
        教师名称
    </div>
    <div class="ui input">
        <input class="input" type="text" name="searchTeaName" value="${param.searchTeaName}">
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
        <c:if test="${sessionScope.userType!=\"3\"}">
        <i class="university icon"></i>
        <div class="content">教学查询</div>
        <div class="sub header">Teaching Class Query</div>
        </c:if>
        <c:if test="${sessionScope.userType==\"3\"}">
            <i class="puzzle icon"></i>
            <div class="content">选择课程</div>
            <div class="sub header">Select Course</div>
        </c:if>
    </h2>
    <div  id="menu">
        <div class="ui small menu">
            <c:if test="${operation}">
                <div class="left menu">
                    <div class="item">
                        <div class="ui primary button" id="new" style="margin-right: 5px">新建</div>
                        <div class="ui red button" id="delete">删除</div>
                    </div>
                </div>
            </c:if>
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
                <c:if test="${sessionScope.userType!=\"3\"}">
                    <th class="eight wide">课程名称</th>
                    <th class="eight wide">教师名称</th>
                </c:if>
                <c:if test="${sessionScope.userType==\"3\"}">
                    <th class="seven wide">课程名称</th>
                    <th class="seven wide">教师名称</th>
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
                <td class="td">${bean.couName}</td>
                <td class="td">${bean.teaName}</td>
                <c:if test="${sessionScope.userType==\"3\"}">
                    <th class=""><div class="ui black inverted small button" style="width: 70px;margin: 2px 10px 2px 10px;">选课</div></th>
                </c:if>
            </tr>
            </c:forEach>
<%@ include file="foot.jsp"  %>