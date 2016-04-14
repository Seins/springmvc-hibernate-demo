package cm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextUtils.class);
    /**
     * 以静态变量保存ApplicationContext,可在任意代码中取出ApplicaitonContext.
     */
    private static ApplicationContext context;

    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     *
     * @param context 本bean定义后spring自动注入context
     */
    public void setApplicationContext(ApplicationContext context) {
        SpringContextUtils.context = context;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     *
     * @param name bean名称
     * @return 返回bean实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        if (context == null) {
            LOGGER.error("error: context is null!",new RuntimeException());
            return null;
        }
        return (T) context.getBean(name);
    }

    public static <T> T getBean(Class<? extends Object> clazz) {
        if (context == null) {
            LOGGER.error("error: context is null!",new RuntimeException());
            return null;
        }
        return (T) context.getBean(clazz);
    }

    /**
     * 从spring容器里取出<context:property-placeholder
     * location="classpath:logsys.properties"/>中配置的属性值
     *
     * @param name 属性文件里的属性名称
     * @return 属性值字符串
     */
    public static String getPropertyByName(String name) {
        AbstractApplicationContext ct = null;
        if (context == null) {
            LOGGER.error("error: context is null!",new RuntimeException());
            return "";
        }
        if (context instanceof ClassPathXmlApplicationContext) {
            ct = (ClassPathXmlApplicationContext) context;
        } else if (context instanceof GenericApplicationContext) {
            ct = (GenericApplicationContext) context;
        } else {
            ct = (XmlWebApplicationContext) context;
        }
        if (name == null) {
            return null;
        }
        name = name.trim();
        if (!name.startsWith("${") && !name.endsWith("}")) {
            name = "${" + name + "}";
        }
        return ct.getBeanFactory().resolveEmbeddedValue(name);
    }

    public static String getPropertyByName(String name, String defaultValue) {
        String result = getPropertyByName(name);
        if (result == null) {
            result = defaultValue;
        }
        return result;
    }
}
