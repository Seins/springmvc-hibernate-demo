<%--
  Created by IntelliJ IDEA.
  User: dengfs
  Date: 2016/4/14
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>

<head>
    <title>微信会员卡</title>
    <meta name="viewport"
          content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link href="/css/card.css" rel="stylesheet"/>
    <link href="/css/index.css" rel="stylesheet"/>
</head>

<body id="card">
<form method="post">
    <div class="aspNetHidden">
        <input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE"
               value="/wEPDwULLTE0MTAzNTAxMTNkZE4iStUyWK4ZGaTIl8BJkX6bIBL9tJWK05KsOlK5yHmw"/>
    </div>
    <div class="aspNetHidden">
        <input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION"
               value="/wEdAAMNskisiWIUflA6lvRF4xZK41IWbcrM6rqP4BleY4lOb2X5CWcM/YY4wbLMWEeczNhzzPUzCtw7zw2V1lhoZhqqTbAidPXd6bQ6RiAkiRqWPA=="/>
    </div>
    <div id="overlay"></div>
    <div class="cardcenter">
        <div class="card-bg">
            <span class="top-tip">微信会员卡</span>

            <div class="vip-num-box">
                <p>会员卡号</p>

                <p class="num" id="userNumber">12345678</p>
            </div>
            <img src="/images/logo.png" class="card-logo"/>
        </div>
        <p class="explain">
            <span>使用时向服务员出示此卡</span>
        </p>
    </div>
    <div class="cardexplain">
        <!--会员积分信息-->
        <div class="vip-icon">
            <p>剩余积分</p>

            <p id="userIcons">1.00</p>
        </div>
        <!--会员资料完善-->
        <div class="box">
            <div class="box-title line-bottom" id="showcard">昵称：CM</div>
            <div class="box-title line-bottom">手机号码:183*****330</div>
        </div>
        <!--会员卡说明-->
        <div class="vip-des">
            <div class="box-title line-bottom">
                <p>会员卡说明<img src="/images/arrow3.png"></p>
            </div>
            <div class="box-content">
                <p>每周签到享受会员累积积分</p>

                <p>积分可进行礼物兑换</p>

                <p>凭微信会员卡可参与微信会员活动</p>
            </div>
        </div>
    </div>
    <div class="window" id="windowcenter">
        <div id="title" class="wtitle">
            领卡信息
            <span class="close" id="alertclose"></span>
        </div>
        <div class="content">
            <div id="txt"></div>
            <p>
                <input class="px" id="truename" type="text" placeholder="请输入您的姓名"/>
            </p>

            <p>
                <input class="px" id="tel" type="tel" placeholder="请输入您的电话"/>
            </p>
            <input type="button" value="确 定" name="确 定" class="txtbtn" id="apply">
        </div>
    </div>
    <input name="hideopenId" type="hidden" id="hideopenId"/>
    <input name="hideBeginNum" type="hidden" id="hideBeginNum"/>
</form>
<script>
    var appId = "${appid}";
    var signature = "${signature}";
    var nonceStr = "${nonceStr}";
    var timestamp = ${timestamp};
</script>
<script src="/js/jquery-2.1.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/page.js"></script>
<script type="text/javascript" src="/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="/js/jweixin_share.js"></script>
</body>
</html>

