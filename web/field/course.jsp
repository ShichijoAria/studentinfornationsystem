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
    <meta charset="UTF-8">
    <title>学生信息系统</title>
    <script src="../modules/jquery.js"></script>
    <link rel="stylesheet" href="../modules/semantic.min.css">
    <script src="../modules/semantic.min.js"></script>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/index.css">
    <link rel="stylesheet" href="../css/field.css">
    <script src="../js/util.js"></script>
</head>
<body>
<form class="ui form" id="field" method="post" action="update.action" id="main">
    <div class="ui small menu" id="menu">
        <div class="left menu">
            <div class="item">
                <div class="ui green button" id="save" style="margin-right: 5px">保存</div>
                <div class="ui grey button" id="return">返回</div>
            </div>
        </div>
    </div>
    <div class="ui segment grid">
        <div class="ui two wide column input fieldinput">课程编号</div>
        <div class="ui three wide column input">
            <input type="text" name="id" value="${param.id}">
        </div>
        <div class="ui two wide column input fieldinput">课程名称</div>
        <div class="ui three wide column input">
            <input type="text" name="name" value="${param.name}">
        </div>
    </div>
    <div class="ui error message"></div>
</form>
<script>
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
        setAction('#field','view.action');
    })
    ;
    $('#save').click(function () {
        $('#field')
            .form({
                fields: {
                    id: {
                        identifier: 'id',
                        rules: [
                            {
                                type: 'empty',
                                prompt: '课程编号不能为空！！！'
                            }
                        ]
                    },
                    name: {
                        identifier: 'name',
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