<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jtsage-datebox-4.0.0.bootstrap.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jtsage-datebox-4.0.0.bootstrap.min.js"></script>
    <script>
        function loading() {
            $(".loading-cover").show();
            $(".loading").show();
        }

        function hideLoading() {
            $(".loading-cover").hide();
            $(".loading").hide();
        }
    </script>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        html {
            font-size: 62.5%;
        }

        a {
            text-decoration: none;
            color: inherit;
        }

        .page-title {
            font-size: 3.8rem;
            text-align: center;
            color: #333;
            font-family: Helvetica;
        }

        .nav-top {
            width: 1320px;;
            height: 120px;
            margin: 40px auto 0 auto;
            display: block;
            position: relative;
        }

        .query-box {
            width: 600px;
            position: absolute;
            bottom: 0;
            right: 50%;
            margin: 0 -300px;
            text-align: center;
        }

        .input-box {
            width: 300px;
            display: inline;
        }

        .input-box label {
            font-size: 1.2rem;
            color: #333;
            width: 80px;
            display: inline-block;
        }

        /*.input-box input{*/
        /*color: #999;*/
        /*border:1px solid #ddd;*/
        /*border-radius: 3px;*/
        /*width: 180px;*/
        /*height: 33px;*/
        /*line-height: 33px;*/
        /*font-size:1.4rem;*/
        /*text-align: center;*/
        /*}*/
        #queryBtn {
            min-width: 180px;
            margin: 5px auto;
            outline: none;
        }

        .btn:active {
            background-color: #ccc;
        }

        .content {
            min-height: 520px;
            margin: 0 auto;
            display: block;
            position: relative;
            margin: 20px auto;
            overflow-x: hidden;
            min-width: 1320px;
        }

        .nav-left {
            display: block;
            width: 200px;
            border: 1px solid #ddd;
            border-radius: 5px;
            position: absolute;
            left: 1em;
            top: 25px;
        }

        .nav-left ul {
            list-style: none;
            width: 100%;
            display: block;
        }

        .nav-left ul li {
            list-style: none;
            width: 100%;
            line-height: 38px;
            font-size: 1.2rem;
            text-align: left;
            text-indent: 3em;
            color: #999;
        }

        .nav-left ul li:active {
            color: orangered;
        }

        .nav-left ul li.title {
            font-size: 1.4rem;
            color: #333;
            text-align: left;
            text-indent: .8rem;
            cursor: auto;
        }

        .nav-left ul li.title:active {
            color: #333;
        }

        .main-right {
            width: 1120px;
            height: 480px;
            display: block;
            margin: 0 auto 0 220px;
        }

        .loading-cover {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 3;
            background: #000;
            opacity: 0.5;
            display: none;
        }

        .loading {
            display: none;
            position: absolute;
            top: 50%;
            left: 50%;
            width: 398px;
            height: 450px;
            border-radius: 50%;
            overflow: hidden;
            z-index: 4;
            margin: -225px -199px;
            text-align: center;
            color: #fff;
            line-height:25px;
        }
        .loading img{
            height:398px;
            display: block;
        }
    </style>
</head>
<body>
<div class="nav-top">
    <h3 class="page-title">大型WEB服务器日志分析系统</h3>

    <div class="query-box">
        <div class="input-box">
            <input type="text" id="date" name="date" class="form-control" data-role="datebox"
                   data-options='{"mode":"datebox"}'/>
        </div>
        <input id="queryBtn" type="button" value="Query" class="btn btn-primary"/>
    </div>
</div>
<div class="content">
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
                <a href="#">服务器接口请求频率分析</a>
            </li>
            <li><a href="#">服务器某接口请求频率分析</a></li>
            <li><a href="#">服务器某接口响应时间分析</a></li>
        </ul>
    </div>
    <c:if test="${empty uri}">
        <jsp:include page="/WEB-INF/view/concurrent.jsp"></jsp:include>
    </c:if>
    <c:if test="${not empty uri}">
        <jsp:include page="${uri}"></jsp:include>
    </c:if>

</div>
<div class="loading-cover"></div>
<div class="loading">
    <img src="${pageContext.request.contextPath}/images/loading.gif" class="loading">

    <p>玩命载入数据中...</p>
</div>

</body>
</html>
