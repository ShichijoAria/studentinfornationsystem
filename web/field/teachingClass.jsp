<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <%--base href="<%=basePath%>">--%>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta charset="UTF-8">
    <title>学生信息系统</title>
    <script src="../modules/jquery.js"></script>
    <link rel="stylesheet" href="../modules/semantic.css">
    <script src="../modules/semantic.min.js"></script>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/index.css">
    <link rel="stylesheet" href="../css/field.css">
    <script src="../js/util.js"></script>
</head>
<body>
<form class="ui form" id="field" method="post" action="updateSetCourse.action" id="main">
    <div class="ui small menu" id="menu">
        <div class="left menu">
            <div class="item">
                <c:if test="${operation}">
                    <div class="ui green button" id="save" style="margin-right: 5px">保存</div>
                </c:if>
                <div class="ui grey button" id="return">返回</div>
            </div>
        </div>
    </div>
    <div class="ui segment grid">
        <div class="ui two wide column input fieldinput">教师</div>
        <div class="ui three wide column input">
            <select class="ui search dropdown <c:if test="${!operation}">
    disabled
</c:if>" name="teaId">
                <option value=""></option>
                <c:forEach items="${teaList}" var="bean" begin="0" end="${teaList.size()}">
                    <option value="${bean.id}"
                            <c:if test="${bean.id}==${teaId}">
                                selected="selected"
                            </c:if>
                    >${bean.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="ui two wide column input fieldinput">课程</div>
        <div class="ui three wide column input">
            <select class="ui search dropdown <c:if test="${!operation}">
    disabled
</c:if>" name="selectCourse.couId">
                <option value=""></option>
                <c:forEach items="${couList}" var="bean" begin="0" end="${couList.size()}">
                    <option value="${bean.id}"
                            <c:if test="${bean.id==selectCourse.couId}">
                                selected="selected"
                            </c:if>
                    >${bean.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="ui error message"></div>
</form>
<script>
    $('.ui.dropdown')
        .dropdown()
    ;
    var strs= new Array(); //定义一数组
    strs=window.location.pathname.split("/");
    if(strs.length>0&&strs[strs.length-1]=="newopen.action"){
        $('form').attr('action','new.action');
    }
    $('#return').click(function () {
        $('#field')
            .form({
                fields: {}
            })
        ;
        setAction('#field','${rtn}');
    })
    ;
    $('#save').click(function () {
        $('#field')
            .form({
                fields: {
                    id: {
                        identifier: 'course.id',
                        rules: [
                            {
                                type: 'empty',
                                prompt: '课程编号不能为空！！！'
                            }
                        ]
                    },
                    name: {
                        identifier: 'course.name',
                        rules: [
                            {
                                type: 'empty',
                                prompt: '课程名称不能为空！！！'
                            }
                        ]
                    }
                }
            })
        ;
        $('#field').submit()
    })
    ;
</script>
</body>
</html>