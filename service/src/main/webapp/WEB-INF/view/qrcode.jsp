<%--
  Created by IntelliJ IDEA.
  User: dengfs
  Date: 2016/4/14
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
输入此二维码能增加的会员积分<input type="number" id="icon" value="1"/>
<input type="button" id="buildqr" value="生成二维码"/>

<div id="qrcode"></div>
<script src="/js/jquery-2.1.4.min.js"></script>
<script src="/js/jquery.qrcode.min.js"></script>
<script>
    $("#buildqr").bind("click", function () {
        var jsonStr = '{"icon":"' + $("#icon").val() + '"}';
        $('#qrcode').qrcode("http://192.168.137.1:8090/service/json/amazing/run/qrcode/check");
    })
</script>
</body>
</html>
