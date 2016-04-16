package cm.service;

import cm.dao.HibernateBaseDao;
import cm.entity.InterfaceInfo;
import cm.entity.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ������cm.service.BaseService
 * �����ߣ� CM .
 * ����ʱ�䣺2016/4/15
 */
@Component
public class BaseService {
    @Autowired
    protected HibernateBaseDao baseDao;


    public List<ServerInfo> findAllServer() {
        return (List<ServerInfo>) baseDao.getAll(ServerInfo.class);
    }


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
     * 获取所有的接口
     *
     * @return
     */
    public List<InterfaceInfo> findAllInterface() {
        return (List<InterfaceInfo>) baseDao.getAll(InterfaceInfo.class);
    }

    /**
     * 获取服务器节点的接口
     *
     * @param serverInfo
     * @return
     */
    public List<InterfaceInfo> findInterfaceByServerInfo(ServerInfo serverInfo) {
        String hql = "from InterfaceInfo ii where ii.serverInfo=?";
        return (List<InterfaceInfo>) baseDao.queryForList(hql, serverInfo);
    }


}
