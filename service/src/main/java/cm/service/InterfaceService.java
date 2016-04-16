package cm.service;

import cm.entity.InterfaceInfo;
import org.springframework.stereotype.Service;

/**
 * Created by CM on 16/4/16.
 */
@Service
public class InterfaceService extends BaseService {


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
}
