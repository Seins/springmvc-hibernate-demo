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
        if(isNaN(date)){
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
            url: '${pageContext.request.contextPath}/analysis/non-bussiness/concurrent/list',
            data: {
                "time": date ? date : getData(0).getTime()
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
                if(res.data.series.length<=0){
                    alert('没有找到对应的日志数据!');
                }
                var myChart = echarts.init(document.getElementById('main'));

                option = {
                    title: {
                        text: '服务器并发统计折线图'
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