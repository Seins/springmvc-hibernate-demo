wx.config({
    debug: false,
    appId: appId,
    timestamp: timestamp,
    nonceStr: nonceStr,
    signature: signature,
    jsApiList: [
        'checkJsApi',
        'hideMenuItems',
        'closeWindow',
        'scanQRCode'
    ]
});
//微信菜单按钮监听事件
wx.ready(function () {
    //// 8.7 关闭当前窗口
    $('#closeWindow').bind("click tab", function () {
        wx.closeWindow();
    });
    //// 9.1.1 扫描二维码并返回结果
    $('#scanQRCode0').bind("click tab", function () {
        wx.scanQRCode();
    });
    //// 9.1.2 扫描二维码并返回结果
    $('#scanQRCode1').bind("click tab", function () {
        wx.scanQRCode({
            needResult: 1,
            desc: 'scanQRCode desc',
            success: function (res) {
                if(res.errMsg =="scanQRCode:ok"){
                    alert("签到成功！增加了"+JSON.parse(res.resultStr).icon+"个积分");
                }else{
                    alert(res.errMsg);
                }
            }
        });
    })
})
wx.error(function (res) {
    alert(res.errMsg);
});
