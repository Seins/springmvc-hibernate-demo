package cm.domainEnum;

/**
 * Created by CM on 16/4/16.
 */
public enum ModuleEnmu {

    CONCURRENT("服务器节点并发数量分析"), TIME_OUT("服务器节点超时数量分析"), CACHE_DATA_AMOUNT("Redis缓存数据分析"),
    ALL_INTERFACE_FREQUENCE("服务器接口请求频率分析"), INTERFACE_RESPONSE("服务器某接口响应时间分析");
    private String desc;

    ModuleEnmu(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
