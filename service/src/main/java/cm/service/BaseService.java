package cm.service;

import cm.dao.HibernateBaseDao;
import cm.entity.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 类名：cm.service.BaseService
 * 创建者： CM .
 * 创建时间：2016/4/15
 */
@Component
public class BaseService {
    @Autowired
    protected HibernateBaseDao baseDao;


    public List<ServerInfo> findAllServer() {
        return (List<ServerInfo>) baseDao.getAll(ServerInfo.class);
    }
}
