package top.flywen.wen.log.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.flywen.wen.entity.SysLog;
import top.flywen.wen.entity.User;
import top.flywen.wen.log.annotation.Log;
import top.flywen.wen.log.service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
  @Autowired
  private LogService logService;

  @Pointcut("@annotation(top.flywen.wen.log.annotation.Log)")
  public void pointcut() {}

  @Around("pointcut()")
  public Object around(ProceedingJoinPoint point) {
    Object result = null;
    long beginTime = System.currentTimeMillis();

    try {
      result = point.proceed();
    } catch (Throwable e) {
      e.printStackTrace();
    }
    long time = System.currentTimeMillis() - beginTime;
    saveLog(point, time);
    return result;
  }

  private void saveLog(ProceedingJoinPoint point, long time) {
    MethodSignature signature = (MethodSignature) point.getSignature();
    Method method = signature.getMethod();
    SysLog log = new SysLog();
    Log logAnnotation = method.getAnnotation(Log.class);
    if(logAnnotation != null) {
      log.setOperation(logAnnotation.value());
    }
    String className = point.getTarget().getClass().getName(),
      methodName = signature.getName();
    log.setMethod(className + "." + methodName + "()");
    Object [] args = point.getArgs();

    LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();

    String[] parameterNames = u.getParameterNames(method);
    if(args != null && parameterNames != null) {
      String params = "";
      for (int i = 0; i < args.length; i++) {
        params += " " + parameterNames[i] + ": " + args[i];
      }
      log.setParams(params);
    }
    HttpServletRequest request = Utils.getHttpServletRequest();
    log.setIp(Utils.getIpAddr(request));
    log.setUsername(Utils.getUser(request));
    log.setAddTime(new Date());
    log.setResponseTime(time);
    logService.saveLog(log);
  }
}

class Utils {
  public static HttpServletRequest getHttpServletRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }

  public static String getIpAddr(HttpServletRequest request) {

    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
  }
  public static String getUser(HttpServletRequest request) {
    User user = (User) request.getSession().getAttribute("user_info");
    return user.getUsername();
  }
}
