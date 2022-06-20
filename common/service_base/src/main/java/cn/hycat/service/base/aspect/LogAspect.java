package cn.hycat.service.base.aspect;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面类
 */
@Component
@Aspect
@Slf4j
/*1.切点 2.通知方法*/
public class LogAspect {

    @Pointcut("@annotation(cn.hycat.service.base.annotation.SysLog)")
    public void pt() {
    }

    @Around("pt()")
    public Object pointLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        try {
            logBefore(joinPoint);
            res = joinPoint.proceed();
            logAfter(res);
        } finally {
            log.info("========End========" + System.lineSeparator());
        }
        return res;
    }

    private void logBefore(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes  = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        log.info("========Start========");
        //请求URL
        log.info("URL               : {}", request.getRequestURL());
        //注解信息
        log.info("描述信息            : ", getApiOperation(joinPoint));
        //请求方法
        log.info("HTTP Method       : {}", request.getMethod());
        //controller全路径和方法名
        log.info("全路径             : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        //请求IP
        log.info("HTTP Method       : {}", request.getRemoteHost());
        //入参
        log.info("入参               : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private void logAfter(Object res) {
        //打印出参
        log.info("入参               : {}", JSON.toJSONString(res));
    }

    private String getApiOperation(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String value = signature.getMethod().getAnnotation(ApiOperation.class).value();
        return value;
    }
}
