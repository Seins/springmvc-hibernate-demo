<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jtsage-datebox-4.0.0.bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jtsage-datebox-4.0.0.bootstrap.min.js"
            type="text/javascript"></script>
    <script>
        function loading() {
            $("#queryBtn").attr("disabled", true);
            $(".loading-cover").show();
            $(".loading").show();
        }

        function hideLoading() {
            $("#queryBtn").attr("disabled", false);
            $(".loading-cover").hide();
            $(".loading").hide();
        }
    </script>
</head>
<body>
<%--数据载入过度动画--%>
<div class="loading-cover"></div>
<div class="loading">
    <img src="${pageContext.request.contextPath}/images/loading.gif" class="loading">

    <p>玩命载入数据中...</p>
</div>
<%--顶部导航--%>
<div class="nav-top">
    <h3 class="page-title">大型WEB服务器日志分析系统</h3>

    <div class="query-box">
        <div class="input-box">
            <input type="text" id="date" name="date" class="form-control" data-role="datebox"
                   data-options='{"mode":"datebox"}'/>
        </div>
        <c:if test="${not empty si}">
            <jsp:include page="/WEB-INF/view/server-condition.jsp"/>
        </c:if>
        <input id="queryBtn" type="button" value="Query" class="btn btn-primary"/>
    </div>
</div>
<%--主体内容--%>
<div class="content">
    <%--左侧导航--%>
    <div class="nav-left">
        <ul>
            <li class="title">
                非业务指标分析
            </li>
            <li><a href="/CONCURRENT">服务器节点并发数量分析</a></li>
            <li><a href="/TIME_OUT">服务器节点超时数量分析</a></li>
            <li class="title">
                业务指标分析
            </li>
            <li>
                <a href="/ALL_INTERFACE_FREQUENCE">接口请求频率分析</a>
            </li>
            <li><a href="/INTERFACE_RESPONSE">接口响应时间分析</a></li>
            <li><a href="/CACHE_DATA_AMOUNT">Redis缓存数据分析</a></li>

        </ul>
    </div>
    <%--右侧内容--%>
    <c:if test="${empty uri}">
        <jsp:include page="/WEB-INF/view/concurrent.jsp"></jsp:include>
    </c:if>
    <c:if test="${not empty uri}">
        <jsp:include page="${uri}"></jsp:include>
    </c:if>
</div>

<div class="analysis-note-box">
    <div class="note-add-box">
        <input type="hidden" value="${module}" name="module" id="module"/>
        <textarea name="noteContent" id="noteContent"></textarea>

        <div class="submit-box">
            <input type="button" class="btn btn-primary" value="保存" onclick="addNote()"/>
        </div>
    </div>
    <div class="history-note-list">
        <c:if test="${empty notes}">
            <p id="noDataTip">没有找到分析记录数据...</p>
        </c:if>
        <ul id="noteList">
            <c:forEach items="${notes}" var="note">
                <li>
                    <h3>
                        <fmt:formatDate value="${note.logTime}" pattern="yyyy-MM-dd HH:mm:ss"/>

                    </h3>

                    <p>${note.noteContent}</p>

                    <p class="note-time">记录时间：<fmt:formatDate value="${note.noteTime}"
                                                              pattern="yyyy-MM-dd HH:mm:ss"/></p>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<script>
    function addNote() {
        //凌晨0点-4点数据请求出现异常，应重点排查此阶段是否受到攻击。
        var note = $("#noteContent").val();
        if (note === null || note === "" || note === undefined) {
            alert("请填写分析内容，再提交！");
            return;
        }

        var module = $("#module").val();
        var logTime = new Date($("#date").val()).getTime();
        var noteTime = new Date();

        var data = {
            "logTime": logTime,
            "noteTime": noteTime.getTime(),
            "noteContent": note,
            "module": module
        };

        $.ajax({
            url: "${pageContext.request.contextPath}/note/add",
            type: 'post',
            data: data,
            beforeSend: function () {
                loading();
            },
            success: function (res) {
                hideLoading();
                if (res.result != "0") {
                    alert(res.msg);
                } else {
                    alert("添加成功!");
                    var dateStr = noteTime.getFullYear() + "-" + (noteTime.getMonth() + 1) + "-" + noteTime.getDate()
                            + " " + noteTime.getHours() + ":" + noteTime.getMinutes() + ":" + noteTime.getSeconds();
                    var e = '<li><h3>' + $("#date").val() + '</h3><p>' + $("#noteContent").val() + '</p><p class="note-time">记录时间：' + dateStr + '</p></li>';
                    $("#noteList").append(e);
                    $("#noDataTip").remove();
                }
            },
            error: function (rq, st, msg) {
                hideLoading();
                alert("error:" + msg);
            }
        })
    }
</script>
</body>
</html>
