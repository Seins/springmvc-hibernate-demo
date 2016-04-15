package cm.service;

import cm.entity.ServerConcurrentLog;
import cm.entity.ServerInfo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * ������cm.service.NonBusinessLogService
 * �����ߣ� CM .
 * ����ʱ�䣺2016/4/15
 */
@Service
public class ConcurrentLogService extends BaseService {

    /**
     *
     * @param serverInfo
     * @param logTime
     * @return
     */
    public List<ServerConcurrentLog> findConcurrentByCondition(ServerInfo serverInfo, Timestamp logTime) {
        logTime.setHours(0);
        Timestamp startTime = new Timestamp(logTime.getTime());
        logTime.setHours(23);
        Timestamp endTime = new Timestamp(logTime.getTime());
        String hql =  "from ServerConcurrentLog  scl where scl.serverInfo=? and scl.logTime>=? and scl.logTime<=?";
        return (List<ServerConcurrentLog>) baseDao.queryForList(hql, serverInfo,startTime,endTime);
    }


    public ServerConcurrentLog findById(Long id){
        return (ServerConcurrentLog) baseDao.getById(ServerConcurrentLog.class,id);
    }
    /**
     *
     * @param log
     * @return
     */
    public Long addConcurrentLog(ServerConcurrentLog log){
        baseDao.add(log);
        return log.getId();
    }
}
