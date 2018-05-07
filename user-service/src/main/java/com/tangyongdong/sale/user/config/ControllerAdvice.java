package com.tangyongdong.sale.user.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 切面打印controller入参，出参日志
 *
 * @author tangyongdong
 * @create 2018-05-02 10:55
 */
@Aspect
@Order(-1)
@Component
@Slf4j
public class ControllerAdvice {

    @Pointcut("execution(public * com.tangyongdong.sale.user.controller.*.*(..))")
    public void expression() {
    }

    @Around("expression()")
    public Object businessLogBefore(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        String methodName = point.getSignature().getName();
        if (CollectionUtils.isEmpty(Lists.newArrayList(args))) {
            log.info("{}  start : no param ", methodName);
        } else {
            if (log.isInfoEnabled() && !exceptURI()) {
                log.info("{}  start : {}", methodName, JSON.toJSONString(args));
            }
        }
        Object proceed = point.proceed();
        if (null != proceed) {
            if (log.isInfoEnabled()) {
                log.info("{}  end : {}", methodName, JSON.toJSONString(proceed));
            }
        }

        return proceed;
    }

    private boolean exceptURI() {
        List<String> exceptURIList = new ArrayList<>();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String uri = request.getRequestURI();
        return exceptURIList.contains(uri);
    }
}
