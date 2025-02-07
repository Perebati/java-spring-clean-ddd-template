package org.gfinnovation.dealsafe.logging;

import org.gfinnovation.dealsafe.logging.infrastrutcture.ErrorLogSchema;
import org.gfinnovation.dealsafe.logging.infrastrutcture.MethodCallLogSchema;
import org.gfinnovation.dealsafe.logging.infrastrutcture.RequestLogSchema;
import org.gfinnovation.dealsafe.logging.infrastrutcture.repository.ErrorLogRepository;
import org.gfinnovation.dealsafe.logging.infrastrutcture.repository.MethodCallLogRepository;
import org.gfinnovation.dealsafe.logging.infrastrutcture.repository.RequestLogRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * TODO: Need refactor.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class LogService
 * @since v1.0 (30/11/2024)
 */
@Service
@Profile({"dev", "prod"})
public class LogService {
    private final MethodCallLogRepository methodCallLogRepository;
    private final ErrorLogRepository errorLogRepository;
    private final RequestLogRepository requestLogRepository;

    public LogService(MethodCallLogRepository methodCallLogRepository, ErrorLogRepository errorLogRepository, RequestLogRepository requestLogRepository) {
        this.methodCallLogRepository = methodCallLogRepository;
        this.errorLogRepository = errorLogRepository;
        this.requestLogRepository = requestLogRepository;
    }

    public void saveMethodCallLogSync(MethodCallLogSchema log) {
        methodCallLogRepository.save(log);
    }

    @Async
    public void saveMethodCallLogAsync(MethodCallLogSchema log) {
        methodCallLogRepository.save(log);
        CompletableFuture.completedFuture(null);
    }

    @Async
    public void saveErrorLogAsync(ErrorLogSchema errorLog) {
        errorLogRepository.save(errorLog);
        CompletableFuture.completedFuture(null);
    }

    @Async
    public void saveRequestLogAsync(RequestLogSchema requestLog) {
        requestLogRepository.save(requestLog);
    }

    @Async
    public void saveRequest(
            String userId,
            String whitelabelId,
            String uri
    ){
        RequestLogSchema requestLog = new RequestLogSchema();
        requestLog.setUserId(userId);
        requestLog.setCompanyId(whitelabelId);
        requestLog.setRequestType("REST");
        requestLog.setTimestamp(new Date());
        requestLog.setUri(uri);
        this.saveRequestLogAsync(requestLog);
    }

    public boolean requestIdExists(String requestId) {
        return requestLogRepository.findById(requestId).isPresent();
    }
}