<%--
  Created by IntelliJ IDEA.
  User: Ace
  Date: 2017/5/27
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</tbody>
</table>
</div>
<div class="ui tiny green inverted three item menu " id="foot">
    <a id="previous" class="item foot">
        <i class="long arrow left icon"></i>
        上页
    </a>
    <a class="item foot">
        <select name="curPage" id="page" class="ui fluid dropdown">
            <%
                int i=1;
            %>
            <c:forEach items="${list}" var="bean" begin="0" end="${page.pages-1}">
                <option value="<%=i%>" <%
                    if(request.getParameter("curPage")!=null&&request.getParameter("curPage").equals(String.valueOf(i)))
                        out.print(" selected = \"selected\"");
                    else if(request.getParameter("curpage")==null&&i==1)
                        out.print(" selected = \"selected\"");
                %>><%=i%></option>
                <%
                    i++;
                %>
            </c:forEach>
        </select>
    </a>
    <a id="next" class="item foot">
        下页
        <i class="long arrow right icon"></i>
    </a>
</div>
</div>
</form>
<script>
    changeSize();
    $('.ui.dropdown').dropdown()/*下拉菜单初始化*/
    $(window).resize(function() {//缩放事件
        changeSize();
    });
    $('.ui.sidebar').sidebar({
        context: $('form'),
        useLegacy:true,
    })
    $('#search,.close.icon').click(function () {
        $('.ui.sidebar')
            .sidebar('toggle')
    })
    $('.ui.button.blue').click(function () {
        setAction('form',"/SIS/<%=viewName%>/view");
    })
    $("#previous").click(function(){
        if(typeof(getUrlVars()["curPage"])=="undefined"||Number(getUrlVars()["curPage"])<=1)
            setAction("form","/SIS/<%=viewName%>/view?curPage=1");
        else
            setAction("form","/SIS/<%=viewName%>/view?curPage="+(Number(getUrlVars()["curPage"])-1));
    })
    $("#next").click(function(){
        if(typeof(getUrlVars()["curPage"])=="undefined")
            setAction("form","/SIS/<%=viewName%>/view?curPage=2");
        else if(Number(getUrlVars()["curPage"])==${page.pages})
            setAction("form","/SIS/<%=viewName%>/view?curPage=${page.pages}");
        else
            setAction("form","/SIS/<%=viewName%>/view?curPage="+(Number(getUrlVars()["curPage"])+1));

    })
    $("select#page").change(function() {
        setAction("form","/SIS/<%=viewName%>/view?curPage="+$('select').val());
    });
    $('td.td').click(function () {
        setAction("form","/SIS/<%=viewName%>/field?resourceId="+$(this).parent().attr("id"));
    })
    $('#new').click(function () {
        setAction('form',"/SIS/<%=viewName%>/open")
    })
    $('#delete').click(function () {
        setAction('form',"/SIS/<%=viewName%>/delete")
    })
    $('#reset').click(function () {
        $('.input').val("");
    });
</script>
</body>
</html>
