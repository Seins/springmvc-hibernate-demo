<%--
  Created by IntelliJ IDEA.
  User: dengfs
  Date: 2016/4/21
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Redis缓存数据量分析</title>
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
        $.ajax({
            url: '${pageContext.request.contextPath}/analysis/non-bussiness/cache/list',
            data: {
                "logTime": date ? date : getData(0).getTime()
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

                option = {
                    title: {
                        text: 'Redis缓存数据量分析'
                    },
                    tooltip: {
                        trigger: 'axis'
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
                    legend: {
                        data: res.data.legend
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: true,
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
                hideLoading();
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
