package kz.mun.webserviceclient;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(public * kz.mun.webserviceclient.*.*(..))")
    private void publicMethodsFromWebServiceClient() {

    }

    @Around(value = "publicMethodsFromWebServiceClient()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Map<String, Object> parameters = getParameters(joinPoint);
        String methodName = joinPoint.getSignature().getName();
        log.info("start {}() - {}", methodName, parameters);
        Object result = joinPoint.proceed();
        log.info("end {}() - {}", methodName, result);
        return result;
    }

    private Map<String, Object> getParameters(ProceedingJoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        HashMap<String, Object> map = new HashMap<>();
        String[] parameterNames = signature.getParameterNames();

        for (int i = 0; i < parameterNames.length; i++) {
            map.put(parameterNames[i], joinPoint.getArgs()[i]);
        }

        return map;
    }
}
