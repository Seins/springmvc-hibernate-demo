package cm.service;

import cm.entity.ServerConcurrentLog;
import cm.entity.ServerInfo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * 类名：cm.service.NonBusinessLogService
 * 创建者： CM .
 * 创建时间：2016/4/15
 */
@Service
public class ConcurrentLogService extends BaseService {

    public List<ServerConcurrentLog> findConcurrentByCondition(ServerInfo serverInfo, Timestamp logTime) {
        String hql = "SELECT scl.logTime ,scl.concurrentAmount from ServerConcurrentLog  scl where scl.serverId=?";
        return (List<ServerConcurrentLog>) baseDao.queryForList(hql, serverInfo.getId());
    }
}
