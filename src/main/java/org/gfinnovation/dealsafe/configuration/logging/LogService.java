package org.gfinnovation.dealsafe.configuration.logging;

import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.ErrorLogSchema;
import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.MethodCallLogSchema;
import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.RequestLogSchema;
import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.repository.ErrorLogRepository;
import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.repository.MethodCallLogRepository;
import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.repository.RequestLogRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * TODO: Need refactor.
 * This is a mess, is works but its not worth commenting on.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class LogService
 * @since 30/10/2024
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
    public CompletableFuture<RequestLogSchema> saveRequestLogAsync(RequestLogSchema requestLog) {
        return CompletableFuture.completedFuture(requestLogRepository.save(requestLog));
    }

    public boolean requestIdExists(String requestId) {
        return requestLogRepository.findById(requestId).isPresent();
    }
}