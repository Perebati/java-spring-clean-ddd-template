package org.gfinnovation.dealsafe.configuration.exception;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.configuration.exception.models.EntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorLogSchema errorLog = buildErrorLog(ex);
        logService.saveErrorLogAsync(errorLog);

        logger.error("An entity was not found in the system!", ex);
        ErrorResponse response = new ErrorResponse("Entity not found!", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(RepositoryException ex, WebRequest request) {
        ErrorLogSchema errorLog = buildErrorLog(ex);
        logService.saveErrorLogAsync(errorLog);

        logger.error("Something went wrong validating business information!", ex);
        ErrorResponse response = new ErrorResponse("Business validation error!", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(FactoryException.class)
    public ResponseEntity<Object> handleFactoryException(RepositoryException ex, WebRequest request) {
        ErrorLogSchema errorLog = buildErrorLog(ex);
        logService.saveErrorLogAsync(errorLog);

        logger.error("Something went wrong on a factory!", ex);
        ErrorResponse response = new ErrorResponse("Entity creation error!", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<Object> handleRepositoryException(RepositoryException ex, WebRequest request) {
        ErrorLogSchema errorLog = buildErrorLog(ex);
        logService.saveErrorLogAsync(errorLog);

        logger.error("Something went wrong in the database!", ex);
        ErrorResponse response = new ErrorResponse("Data access error!", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        ErrorLogSchema errorLog = buildErrorLog(ex);
        logService.saveErrorLogAsync(errorLog);

        logger.error("Must likely a logic mistake!", ex);
        ErrorResponse response = new ErrorResponse("Business error!", ex.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BusinessException ex, WebRequest request) {
        ErrorLogSchema errorLog = buildErrorLog(ex);
        logService.saveErrorLogAsync(errorLog);

        logger.error("A request was badly made!", ex);
        ErrorResponse response = new ErrorResponse("Request error", ex.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(BusinessException ex, WebRequest request) {
        ErrorLogSchema errorLog = buildErrorLog(ex);
        logService.saveErrorLogAsync(errorLog);

        logger.error("Authentication failed!", ex);
        ErrorResponse response = new ErrorResponse("Authentication failed!", ex.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        ErrorLogSchema errorLog = buildErrorLog(ex);
        logService.saveErrorLogAsync(errorLog);

        logger.error("Something terrible happened!", ex);
        logger.error(ex.getCause().getMessage());
        String asciiArt = String.join("\n",
                "⠀⠀⠀⠀⠀⠀⠀⠀⣤⡀⠀⣶⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠀⠀⠀⠀⠀⠀⠀⠙⣿⣆⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠀⠀⠀⠀⠀⠀⠀⠸⣷⣮⣿⣿⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠀⠀⠀⠀⠀⢀⡠⠒⠉⠀⠀⠀⠀⠀⠀⠈⠁⠲⢖⠒⡀⠀⠀",
                "⠀⠀⠀⡠⠴⣏⠀⢀⡀⠀⢀⡀⠀⠀⠀⡀⠀⠀⡀⠱⡈⢄⠀",
                "⠀⠀⢠⠁⠀⢸⠐⠁⠀⠄⠀⢸⠀⠀⢎⠀⠂⠀⠈⡄⢡⠀⢣",
                "⠀⢀⠂⠀⠀⢸⠈⠢⠤⠤⠐⢁⠄⠒⠢⢁⣂⡐⠊⠀⠸",
                "⠀⡘⠀⠀⠀⢸⠀⢠⠐⠒⠈⠀⠀⠀⠀⠀⠀ ⠈⢆⠀ ⢸",
                "⠀⡇⠀⠀⠀⠀⡗⢺⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠑⢀⠎",
                "⠀⢃⠀⠀⠀⢀⠃⢠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠷⡃⠀",
                "⠀⠈⠢⣤⠀⠈⠀⠀⠑⠠⠤⣀⣀⣀⣀⣀⡀⠤⢡⠀",
                "⡀⣀⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢘⠀",
                "⠑⢄⠉⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⠀",
                "⠀⠀⠑⠢⢱⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠁⠀",
                "⠀⠀⠀⠀⢀⠠⠓⠢⠤⣀⣀⡀⠀⠀⣀⣀⡀⠤⠒⠑⢄⠀⠀",
                "⠀⠀⠀⠰⠥⠤⢄⢀⡠⠄⡈⡀⠀⠀⣇⣀⠠⢄⠀⠒⠤⠣⠀",
                "⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀"
        );

        System.out.println(asciiArt);
        ErrorResponse response = new ErrorResponse("An unexpected or an unmapped error occurred!", ex.getCause().getMessage());
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

