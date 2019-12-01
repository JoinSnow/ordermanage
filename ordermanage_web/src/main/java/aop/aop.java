package aop;

import domain.ErrorLog;
import domain.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import service.ErrorLogService;
import service.LogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class aop {
    @Autowired
    private LogService logService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ErrorLogService errorLogService;


    private Date visitTime;

    @Pointcut("execution(* controller.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before() {
        visitTime = new Date();
    }

    @AfterReturning("pointcut()")
    public void afterreturning(JoinPoint joinPoint) {
        // 访问人，从spring security中取
        SecurityContext context = SecurityContextHolder.getContext();// 获取到Security容器
        User user = (User) context.getAuthentication().getPrincipal();// 获取Security存的User对象
        String username = user.getUsername();// 获取到访问人

        String ip = request.getRemoteAddr();//获取ip
        String url = request.getRequestURI();//获取访问路径
        Long time = new Date().getTime() - visitTime.getTime();//获取执行时长 ms
        String method = joinPoint.getSignature().getName();//获取访问方法

        Log log = new Log();
        log.setId(UUID.randomUUID().toString());
        log.setVisitTime(visitTime);
        log.setUsername(username);
        log.setIp(ip);
        log.setUrl(url);
        log.setExecutionTime(time);
        log.setMethod(method);
        logService.add(log);
    }

    @AfterThrowing("pointcut()")
    public void afterthrowing(JoinPoint joinPoint) {
        SecurityContext context = SecurityContextHolder.getContext();// 获取到Security容器
        User user = (User) context.getAuthentication().getPrincipal();// 获取Security存的User对象
        String username = user.getUsername();// 获取到访问人

        String ip = request.getRemoteAddr();//获取ip
        String url = request.getRequestURI();//获取访问路径
        String method = joinPoint.getSignature().getName();//获取访问方法
        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        int status = httpServletResponse.getStatus();

        ErrorLog errorLog = new ErrorLog();
        errorLog.setId(UUID.randomUUID().toString());
        errorLog.setVisitTime(new Date());
        errorLog.setUsername(username);
        errorLog.setIp(ip);
        errorLog.setUrl(url);
        errorLog.setMethod(method);
        errorLog.setErrorCode(status);
        errorLogService.add(errorLog);
    }
}
