<%--
  Created by IntelliJ IDEA.
  User: Ace
  Date: 2017/5/18
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <meta charset="UTF-8">
    <title>学生信息系统</title>
    <script src="<%=basePath%>modules/jquery.js"></script>
    <link rel="stylesheet" href="<%=basePath%>modules/semantic.css">
    <script src="<%=basePath%>modules/semantic.min.js"></script>
    <script src="<%=basePath%>modules/echarts-all-3.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/index.css">
    <link rel="stylesheet" href="<%=basePath%>css/general.css">
</head>
<body>
<h2 class="ui dividing header">
    欢迎使用学生信息系统
    <div class="sub header">Business Process Management</div>
</h2>
<div class="ui grid" style="margin: 10px 10px 10px 10px">
    <div class="row">
        <div class="ui statistic three wide column left floated small">
            <div class="value">
                <i class="icon unhide"></i>
                ${countAll}
            </div>
            <div class="label">
                登录次数
            </div>
        </div>
    </div>
    <div class="row ui grid stackable">
        <div id="main" class="eight wide column" style="height: 450px"></div>
        <div id="visiteChart" class="eight wide column" style="height: 450px"></div>
    </div>
</div>
<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var option = {
        itemStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#83bff6'},
                        {offset: 0.5, color: '#188df0'},
                        {offset: 1, color: '#188df0'}
                    ]
                )
            },
            emphasis: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#2378f7'},
                        {offset: 0.7, color: '#2378f7'},
                        {offset: 1, color: '#83bff6'}
                    ]
                )
            }
        },
        title: {
            text: '数据一览'
        },
        tooltip : {
            trigger: 'item',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        label: {
            normal: {
                show: true,
                position: 'top',
                formatter: '{c}'
            }
        },
        legend: {
            data:['数量']
        },
        xAxis: {
            data: ["学生","教学班","课程","教师"]
        },
        yAxis: {},
        series: [{
            name: '数量',
            type: 'bar',
            barMaxWidth:'100px',
            data: [
                <c:forEach items="${list}" var="bean" begin="0" end="${list.size()}">
                "${bean}",
                </c:forEach>
                ""],
            animationDelay: function (idx) {
                return idx * 125;
            }
        }],
        animationEasing: 'elasticOut',
        animationDelayUpdate: function (idx) {
            return idx * 2;
        }
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    myChart.on('click', function (param){
        if(param.name=="学生") {
            window.location.href="/SIS/student/view?curPage=1"
        }else if(param.name=="教学班") {
            window.location.href="/SIS/teachingClass/view?curPage=1"
        }else if(param.name=="课程") {
            window.location.href="/SIS/course/view?curPage=1"
        }else if(param.name=="教师") {
            window.location.href="/SIS/teacher/view?curPage=1"
        }
    });

    var visiteChart = echarts.init(document.getElementById('visiteChart'));

    var visitedOption = {
        title: {
            text: '最近登录数'
        },
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        legend: {
            data:['登录次数']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : [
                    <c:forEach items="${statistical}" var="bean" begin="0" end="${statistical.size()}" varStatus="stat">
                    '${bean.visitedDay}'<c:if test="${!stat.last}">,</c:if>
                    </c:forEach>
                    ]
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        dataZoom: [{
            startValue: <c:forEach items="${statistical}" var="bean" begin="${statistical.size()-7}" end="${statistical.size()-7}">
            '${bean.visitedDay}'
            </c:forEach>
        }, {
            type: 'inside'
        }],

        series : [
            {
                name:'登录次数',
                type:'line',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                        }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                        }])
                    }
                },
                data:[
                    <c:forEach items="${statistical}" var="bean" begin="0" end="${statistical.size()}" varStatus="stat">
                    ${bean.visitedCount}<c:if test="${!stat.last}">,</c:if>
                    </c:forEach>
                ],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };

    visiteChart.setOption(visitedOption);

</script>
</body>
</html>
