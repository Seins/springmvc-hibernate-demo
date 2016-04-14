package com.tencent.service;

import com.tencent.common.Configure;
import com.tencent.protocol.send_red_pack.SendRedPackReqData;

/**
 * Created by 蔻丁同学 on 2015/6/23.
 */
public class SendRedPackService extends BaseService {
    public SendRedPackService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        super(Configure.SEND_RED_PACK);
    }


    /**
     * 发送现金红包
     * @param sendRedPackReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(SendRedPackReqData sendRedPackReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(sendRedPackReqData);

        return responseString;
    }
}
