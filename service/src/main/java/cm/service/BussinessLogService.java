package cm.service;

import cm.dao.HibernateBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名：cm.service.BussinessLogService
 * 创建者： CM .
 * 创建时间：2016/4/15
 */
@Service
public class BussinessLogService {
    @Autowired
    private HibernateBaseDao baseDao;


    public List<Object> findAll() {
        return null;
    }
}
