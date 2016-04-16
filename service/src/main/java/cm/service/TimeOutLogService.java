package cm.service;

import cm.entity.ServerInfo;
import cm.entity.ServerTimeoutLog;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CM on 16/4/16.
 */
@Service
public class TimeOutLogService extends BaseService {

    /**
     * 获取某个服务器节点所有接口超时响应日志统计
     *
     * @param server  服务器节点
     * @param logTime 日志纪录的时间 精度到小时,从这个点开始往后一个小时的统计
     * @return
     */
    private Long countServerTimeOutLogByCondition(ServerInfo server, Timestamp logTime) {
        //获取当前时间前后一个小时
        logTime.setHours(logTime.getHours());
        Timestamp startTime = new Timestamp(logTime.getTime());
        logTime.setHours(logTime.getHours() + 1);
        Timestamp endTime = new Timestamp(logTime.getTime());
        String hql = "SELECT COUNT(stl.id) from ServerTimeoutLog stl where stl.serverInfo=? and stl.logTime>=? and stl.logTime<=?";

        return (Long) baseDao.getUnique(hql, server, startTime, endTime);
    }

    /**
     * 获取服务器节点某天的超时数据  按照小时分组,共24组
     *
     * @param server  服务器节点
     * @param logTime 日志纪录时间 精确到天,从日期开始0点 到23点
     * @return
     */
    public List findServerTimeoutLogByDate(ServerInfo server, Timestamp logTime) {
        List<Long> dateCount = new ArrayList<>();
        for (int h = 0; h < 24; h++) {
            logTime.setNanos(0);
            logTime.setHours(h);
            Long count = countServerTimeOutLogByCondition(server, logTime);
            dateCount.add(h, count);
        }

        return dateCount;
    }

    /**
     * 获取所有服务器节点某天的日志纪录统计
     *
     * @param logTime
     * @return
     */
    public Map<String, List> findAllServerTimeoutLogByDate(Timestamp logTime) {
        List<ServerInfo> servers = findAllServer();
        Map<String, List> serverLogCounts = new LinkedHashMap<>();
        for (ServerInfo server : servers) {
            List serverDateCount = findServerTimeoutLogByDate(server, logTime);
            serverLogCounts.put(server.getServerName(), serverDateCount);
        }

        return serverLogCounts;
    }

    /**
     * 添加日志
     *
     * @param log
     * @return
     */
    public Long addLog(ServerTimeoutLog log) {
        baseDao.add(log);
        return log.getId();
    }
}
