<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%--base href="<%=basePath%>">--%>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<form class="ui form" id="field" method="post" action="/SIS/grade/update?resourceId=${id}" id="main">
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
    <div class="ui segment stackable three column grid">
        <div class="column">
            <div class="ui black basic horizontal label">教师名称</div>
            <div class="ui disabled input">
                <input type="text" name="teaName" value="${teaName}">
            </div>
        </div>
        <div class="column">
            <div class="ui black basic horizontal label">学生名称</div>
            <div class="ui disabled input">
                <input type="text" name="stuName" value="${stuName}">
            </div>
        </div>
        <div class="column">
            <div class="ui black basic horizontal label">课程名称</div>
            <div class="ui disabled input">
                <input type="text" name="couName" value="${couName}">
            </div>
        </div>
        <div class="column">
            <div class="ui black basic horizontal label">课程成绩</div>
            <div class="ui <c:if test="${!operation}">disabled</c:if> input">
                <input type="number" name="grade" value="${grade}">
            </div>
        </div>
    </div>
    <div class="ui error message"></div>
</form>
<script>
    var strs= new Array(); //定义一数组
    strs=window.location.pathname.split("/");
    if(strs.length>0&&strs[strs.length-1]=="open"){
        $('form').attr('action','/SIS/course/insert');
    }
    $('#return').click(function () {
        $('#field')
            .form({
                fields: {}
            })
        ;
        setAction('#field','/SIS/grade/view?curPage=${sessionScope.gradeBack}');
    })
    ;
    $('#save').click(function () {
        $('#field').submit()
    })
    ;
</script>
</body>
</html>