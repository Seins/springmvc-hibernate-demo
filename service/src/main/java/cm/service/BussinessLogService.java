package cm.service;

import cm.dao.HibernateBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ������cm.service.BussinessLogService
 * �����ߣ� CM .
 * ����ʱ�䣺2016/4/15
 */
@Service
public class BussinessLogService {
    @Autowired
    private HibernateBaseDao baseDao;


    public List<Object> findAll() {
        return null;
    }
}
