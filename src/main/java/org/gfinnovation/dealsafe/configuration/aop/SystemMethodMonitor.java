package org.gfinnovation.dealsafe.configuration.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.gfinnovation.dealsafe.configuration.logging.LogService;
import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.MethodCallLogSchema;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class SystemMethodMonitor
 * @authorNote This class basically monitors the whole domains package.
 * If a method throws an exception, this class captures that, and then
 * it saves on MongoDb for later analysis.
 * @since 30/10/2024
 */
@Aspect
@Component
public class SystemMethodMonitor {

    private final LogService logService;

    public SystemMethodMonitor(LogService logService) {
        this.logService = logService;
    }

    @Around("execution(* org.gfinnovation.dealsafe.domains..*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        String requestId = MDC.get("requestId");
        String userId = MDC.get("userId");
        String companyId = MDC.get("companyId");
        try {
            return joinPoint.proceed();
        } catch (Exception ex) {
            MethodCallLogSchema methodCallLog = buildMethodCallLog(joinPoint, requestId, userId, companyId);
            methodCallLog.setError(true);
            logService.saveMethodCallLogSync(methodCallLog);
            MDC.put("methodId", methodCallLog.getId());
            throw ex;
        }
    }

    private MethodCallLogSchema buildMethodCallLog(ProceedingJoinPoint joinPoint, String requestId, String userId, String companyId) {
        MethodCallLogSchema methodCallLog = new MethodCallLogSchema();
        methodCallLog.setMethodName(joinPoint.getSignature().getName());
        methodCallLog.setClassName(joinPoint.getTarget().getClass().getSimpleName());
        methodCallLog.setArguments(formatArguments(joinPoint.getArgs()));
        methodCallLog.setRequestId(defaultIfNull(requestId));
        methodCallLog.setUserId(defaultIfNull(userId));
        methodCallLog.setCompanyId(defaultIfNull(companyId));
        methodCallLog.setTimestamp(new Date());
        return methodCallLog;
    }

    private String formatArguments(Object[] args) {
        if (args == null || args.length == 0) {
            return "[]";
        }
        return Arrays.stream(args)
                .map(Object::toString)
                .reduce((arg1, arg2) -> arg1 + ", " + arg2)
                .orElse("");
    }

    private String defaultIfNull(String value) {
        return value != null ? value : "N/A";
    }
}
