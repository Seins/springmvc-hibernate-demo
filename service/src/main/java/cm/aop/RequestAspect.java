package cm.aop;

import cm.redis.ConfigRedisOperator;
import cm.redis.RedisKeyCenter;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名：cm.aop.RequestAspect
 * 创建者： CM .
 * 创建时间：2016/4/21
 */

@Aspect
@Component
public class RequestAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestAspect.class);
    @Autowired
    private HttpServletRequest request;
    @Autowired
    protected ConfigRedisOperator redisOperator;


    @Pointcut(value = "execution(* cm.controller.*.*(..))")
    public void pointCut() {
        //切入点方法， 监控所有的controller层的所有方法
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String uri = request.getRequestURI();
        Object[] args = joinPoint.getArgs();
        String ip = StringUtils.isNotEmpty(request.getHeader("X-Forwarded-For")) ? request.getHeader("X-Forwarded-For").split(",")[0] : request.getRemoteAddr();
        Map log = new HashMap();
        log.put("requestURI", uri);
        log.put("methodName", method);
        log.put("args", args);
        log.put("ip", ip);
        log.put("logTime", System.currentTimeMillis());
        redisOperator.lpush(RedisKeyCenter.getTodayRequestLogKey(), JSON.toJSONString(log));
        LOGGER.info("HOST:{},REQUEST URI:{},METHOD NAME:{},ARGS:{}", ip, uri, method, args);
    }
}
