<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 16/4/16
  Time: 上午12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main" class="main-right">

</div>
<script type="text/javascript">

    $("#queryBtn").bind("click", function () {

        var date = new Date($("#date").val()).getTime();
        if (isNaN(date)) {
            alert('请选择要查询的时间');
        }
        changeTable(date);
    })
    // 指定图表的配置项和数据
    var option;
    //    var _c = 15;
    changeTable();
    //    var _i = setInterval("changeTable()", 15000);
    function changeTable(date) {
//        if (_c <= 1) {
//            clearInterval(_i);
//        }
//        _c--;
        $.ajax({
            url: '${pageContext.request.contextPath}/analysis/non-bussiness/timeout/list',
            data: {
                "logTime": date ? date : getData(0).getTime()
            },
            type: 'post',
            dataType: 'json',
            async: true,
            beforeSend:function(){
                loading();
            },
            success: function (res) {
                hideLoading();
                if (res.result !== "0") {
                    alert(res.msg);
                    return;
                }
                console.log("res:%o", res);
                if (res.data.series.length <= 0) {
                    alert('没有找到对应的日志数据!');
                    return;
                }
                var myChart = echarts.init(document.getElementById('main'));
                var series = [];
                var legend = [];
                for (var _c in res.data.series) {
                    legend.push(_c);
                    series.push({
                        name: _c,
                        type: 'bar',
                        data: res.data.series[_c]

                    });
                }
                option = {
                    title: {
                        text: '服务器超时数量统计',
                        subtext: '超时数量'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: legend
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            data: res.data.xAxis
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: series
                };
                myChart.setOption(option);
            },
            error: function (rq, st, msg) {
                hideLoading();
                console.error("req:%o,st:%o,msg:%o", rq, st, msg);
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
