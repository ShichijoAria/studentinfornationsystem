<%--
  Created by IntelliJ IDEA.
  User: Ace
  Date: 2017/5/18
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>$Title$</title>
    <script src="../modules/jquery.js"></script>
    <link rel="stylesheet" href="../modules/semantic.min.css">
    <script src="../modules/semantic.min.js"></script>
    <link rel="stylesheet" href="../css/index.css">
  </head>
  <body>
  <div class="container" style="height: 100%">
    <div class="ui massive attached stackable menu borderless">
      <div class="ui tiny header item" style="width:260px">
        <i class="circular users icon"></i>
        <div class="content">
          学生信息系统
        </div>
      </div>
      <div class="item indexmenu">
        <a href="javascript:void(0);" class="menu"><i class="home icon"></i> 主页</a>
      </div>
      <div class="item indexmenu">
        <div class="ui input action">
          <input type="text" placeholder="最新资讯……">
          <div class="ui blue button">搜索</div>
        </div>
      </div>
      <div class="menu right">
        <div class="ui dropdown item indexmenu">
          <i class="user icon"></i>user
          <div class="menu">
            <a class="item"><i class="icon setting"></i> 个人信息</a>
            <a class="item" href="logout.action"><i class="icon sign out"></i> 注销</a>
          </div>
        </div>
        <div class="ui item">
          <i class="icon help"></i>
          帮助
        </div>
      </div>
    </div>
    <div class="ui bottom attached segment pushable">
      <div class="ui inverted labeled icon left inline vertical sidebar menu accordion" style="width: 260px">
        <div class="item acc">
          <a class="title" style="color:#AEB7C2;">
            <i class="icon users padrig"></i>
            用户管理
            <i class="dropdown icon"></i>
          </a>
          <div class="content">
            <p><a class="child" href="javascript:void(0)">密码管理</a></p>
            <p><a class="child" href="javascript:void(0)">教师管理</a></p>
            <p><a class="child" href="javascript:void(0)">学生管理</a></p>
          </div>
        </div>
      </div>
      <div class="pusher">
        <div class="ui basic segment">
          <h3 class="ui header"></h3>
          <p></p>
          <p></p>
          <p></p>
          <p></p>
        </div>
      </div>
    </div>
  </div>
  <script>
      $('.ui.menu')
          .on('click', '.item', function() {
              if(!$(this).hasClass('dropdown')) {
                  $(this)
                      .addClass('active')
                      .siblings('.item')
                      .removeClass('active')
                  ;
              }
          })
      ;/*菜单控件初始化*/
      $('.ui.inverted.labeled.icon.left.inline.vertical.sidebar.menu')
          .sidebar({
              context: $('.ui.bottom.attached.segment.pushable'),
              closable:false,
              dimPage:false
          })
      ;
      $('.ui.sidebar')
          .sidebar('toggle')
      ;
      $('.ui.dropdown')
          .dropdown()
      ;/*下拉菜单初始化*/
      $('.ui.accordion')
          .accordion()
      ;/*手风琴初始化*/
      $(".title").mouseover(function(){
          $(this).css("color","white")
              .children().addClass("myblue")
      });
      $(".title").mouseout(function(){
          if(!$(this).hasClass("active")) {
              $(this).css("color", "#AEB7C2")
                  .children().removeClass("myblue");
          }
      });
  </script>
  </body>
</html>
