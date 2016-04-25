package cm.service;

import cm.dao.HibernateBaseDao;
import cm.entity.CacheDataChangeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * 类名：cm.service.CacheLogService
 * 创建者： CM .
 * 创建时间：2016/4/25
 */
@Service
public class CacheLogService {
    @Autowired
    private HibernateBaseDao baseDao;

    /**
     * 添加日志
     *
     * @param log
     * @return
     */
    public Long addLog(CacheDataChangeLog log) {
        baseDao.add(log);
        return log.getId();
    }

    /**
     * 获取缓存数据每小时的统计信息，一天24小时
     *
     * @param logTime 记录时间，精确到天
     * @return
     */
    public List<CacheDataChangeLog> findByLogTime(Timestamp logTime) {
        logTime.setHours(0);
        Timestamp startTime = new Timestamp(logTime.getTime());
        logTime.setHours(23);
        Timestamp endTime = new Timestamp(logTime.getTime());
        String hql = "from CacheDataChangeLog  cdcl where cdcl.logTime>=? and cdcl.logTime<=?";
        return (List<CacheDataChangeLog>) baseDao.queryForList(hql, startTime, endTime);
    }
}
