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
    changeTable();
    function changeTable(date) {
        var serverId = $("#server").val();
        $.ajax({
            url: '${pageContext.request.contextPath}/analysis/business/response/list',
            data: {
                "logTime": date ? date : getData(0).getTime(),
                "serverId": serverId ? serverId : 0
            },
            type: 'post',
            dataType: 'json',
            async: true,
            beforeSend: function () {
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
                        data: res.data.series[_c],
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    });
                }
                option = {
                    title: {
                        text: '服务器接口响应时间统计',
                        subtext: '每小时统计三次,纪录平均值'
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
                            dataZoom: {},
                            dataView: {readOnly: false},
                            magicType: {type: ['line', 'bar']},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: true,
                        data: res.data.xAxis
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} ms'
                        }
                    },
                    series: series,
                    dataZoom: {
                        show: true,
                        start : 0,
                        end : 23
                    }
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
        $("#date").val(dd.getFullYear() + "-" + (dd.getMonth() + 1) + "-" + dd.getDate());
        return dd;
    }
</script>
