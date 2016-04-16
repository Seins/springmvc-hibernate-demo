package cm.service;

import cm.entity.InterfaceInfo;
import cm.entity.InterfaceRequestLog;
import cm.entity.ServerInfo;
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
public class MulInterfaceService extends BaseService {


    /**
     * 添加接口
     *
     * @param interfaceInfo
     * @return
     */
    public Long addInterface(InterfaceInfo interfaceInfo) {
        baseDao.add(interfaceInfo);
        return interfaceInfo.getId();
    }

    /**
     * 添加日志
     *
     * @param requestLog
     * @return
     */
    public Long addLog(InterfaceRequestLog requestLog) {
        baseDao.add(requestLog);
        return requestLog.getId();
    }

    /**
     * 获取某个服务器节点所有接口请求频率统计日志
     *
     * @param server
     * @return
     */
    public Map<String, List> findServerInterfaceLogByDate(ServerInfo server, Timestamp logTime) {
        List<InterfaceInfo> ins = findInterfaceByServerInfo(server);
        Map<String, List> inLogs = new LinkedHashMap<>();
        for (InterfaceInfo in : ins) {
            inLogs.put(in.getInterfaceName(), findInterfaceLogByDate(in, logTime));
        }
        return inLogs;
    }

    /**
     * 获取某个接口某天的请求日志
     *
     * @param in
     * @param logTime 日志纪录时间,精确到天
     * @return
     */
    private List<InterfaceRequestLog> findInterfaceLogByDate(InterfaceInfo in, Timestamp logTime) {
        logTime.setHours(0);
        logTime.setMinutes(0);
        logTime.setSeconds(0);
        logTime.setNanos(0);
        Timestamp startTime = new Timestamp(logTime.getTime());
        logTime.setHours(23);
        logTime.setMinutes(59);
        logTime.setSeconds(59);
        Timestamp endTime = new Timestamp(logTime.getTime());
        String hql = "select irl.requestAmount from InterfaceRequestLog irl where irl.logTime>? and irl.logTime<? and irl.interfaceInfo=?";
        return (List<InterfaceRequestLog>) baseDao.queryForList(hql, startTime, endTime, in);
    }
}
