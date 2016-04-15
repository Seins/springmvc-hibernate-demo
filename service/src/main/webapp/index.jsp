<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<div id="main" style="width: 1320px;height: 480px;margin: 0 auto">

</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例

    // 指定图表的配置项和数据
    var option;
    var _c = 15;
    changeTable();
    var _i = setInterval("changeTable()",15000);
    function changeTable() {
        if(_c<=1){
            clearInterval(_i);
        }
        _c--;
        $.ajax({
            url: '${pageContext.request.contextPath}/analysis/non-bussiness/concurrent/list',
            data: {
                "time": getData(_c-15).getTime()
            },
            type: 'post',
            dataType: 'json',
            async: false,
            success: function (res) {
                if (res.result !== "0") {
                    alert(res.msg);
                    return;
                }
                console.log("res:%o", res);
                var myChart = echarts.init(document.getElementById('main'));

                option = {
                    title: {
                        text: '服务器并发统计折线图'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: res.data.legend
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: res.data.xAxis
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: res.data.series
                };
                myChart.setOption(option);

            },
            error: function (rq, st, msg) {
                alert("error:" + msg);
            }
        })
    }

    function getData(AddDayCount) {
        var dd = new Date();
        dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期
        return dd;
    }
</script>
</body>
</html>
