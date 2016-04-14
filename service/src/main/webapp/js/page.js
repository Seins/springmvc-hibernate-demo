var applyflag = true;
$(document).ready(function() {
    //弹出填写框
    $("#showcard").click(function() {
        $("#txt").html("填写真实的姓名以及电话号码。");
        $("#windowcenter").slideToggle("slow");
        $("#overlay").show();
    });
    //关闭填写框
    $("#alertclose").click(function() {
        $("#windowcenter").slideUp(500);
        $("#overlay").hide();
    });
    //领取会员卡
    $("#apply").click(function() {
        var name = $("#truename").val();
        var mobile = $("#tel").val();
        var openId = $("#hideopenId").val();
        var beginNum = $("#hideBeginNum").val();
        if (name == "") {
            $("#truename").focus();
            return false;
        }
        if (mobile == "") {
            $("#tel").focus();
            return false;
        }
        if (mobile.length != 11) {
            $("#tel").focus();
            return false;
        }
        var postData = {
            name: name,
            mobile: mobile,
            openId: openId,
            beginNum: beginNum
        };
        if (applyflag) {
            $.ajax({
                type: "get",
                url: absoluteContextPath = "ajax/apply.ashx?" + Math.random(),
                data: postData,
                beforeSend: function() {
                    applyflag = false;
                },
                success: function(d) {
                    d = eval("(" + d + ")");
                    if (d.hadtel) {
                        alert("该电话号码已领取实体卡,请进行绑定操作!");
                        window.location.href = window.location.href;
                    }
                    if (d.success) {
                        alert("恭喜您微信开卡成功!");
                        window.location.href = window.location.href;
                    } else {
                        alert("微信开卡失败，请检查您的网络");
                        window.location.href = window.location.href;
                    }
                },
                error: function() {
                    alert("网络错误");
                    window.location.href = window.location.href;
                }
            })
        }
    })
});
