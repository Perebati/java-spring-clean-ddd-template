package org.gfinnovation.dealsafe.configuration.exception;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.DomainException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.configuration.logging.LogService;
import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.ErrorLogSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Every single thrown exception pass through here.
 * The error is saved on db and then filtered for the client.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GlobalExceptionHandler
 * @since 30/10/2024
 */

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final LogService logService;

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleBusinessException(ServiceException ex, WebRequest request) {
        ErrorLogSchema errorLog = buildErrorLog(ex);
        logService.saveErrorLogAsync(errorLog);

        logger.error("Must likely a logic mistake!", ex);
        ErrorResponse response = new ErrorResponse("An error occurred:", ex.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(ServiceException ex, WebRequest request) {
        ErrorLogSchema errorLog = buildErrorLog(ex);
        logService.saveErrorLogAsync(errorLog);

        logger.error("A request was badly made!", ex);
        ErrorResponse response = new ErrorResponse("Request error", ex.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(ServiceException ex, WebRequest request) {
        ErrorLogSchema errorLog = buildErrorLog(ex);
        logService.saveErrorLogAsync(errorLog);

        logger.error("Authentication failed!", ex);
        ErrorResponse response = new ErrorResponse("Authentication failed!", ex.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        ErrorLogSchema errorLog = buildErrorLog(ex);
        logService.saveErrorLogAsync(errorLog);

        logger.error("Something terrible happened!", ex);
        logger.error(ex.getCause().getMessage());

        ErrorResponse response = new ErrorResponse("An unexpected error occurred!", ":(");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private ErrorLogSchema buildErrorLog(Exception ex) {
        ErrorLogSchema errorLog = new ErrorLogSchema();
        errorLog.setErrorMessage(ex.getMessage());
        errorLog.setErrorStackTrace(formatStackTrace(ex.getStackTrace()));
        errorLog.setTimestamp(new Date());

        errorLog.setUserId(MDC.get("userId"));
        errorLog.setCompanyId(MDC.get("companyId"));
        errorLog.setRequestId(MDC.get("requestId"));
        errorLog.setMethodId(MDC.get("methodId"));

        return errorLog;
    }

    private String formatStackTrace(StackTraceElement[] stackTrace) {
        return Arrays.stream(stackTrace)
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));
    }
}

