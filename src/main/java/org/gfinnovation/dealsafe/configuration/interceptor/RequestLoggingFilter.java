package org.gfinnovation.dealsafe.configuration.interceptor;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.gfinnovation.dealsafe.configuration.logging.LogService;
import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.RequestLogSchema;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RequestLoggingFilter
 * @authorNote Every single requisition in this system is registered, this class does that.
 * This class also should throw an exception if 'user_id' and 'company_id' are not present,
 * but because this is a beta and proper authentication is not yet implemented, this class
 * is shallow.
 * @since 30/10/2024
 */
@Component
public class RequestLoggingFilter implements Filter {
    private final LogService logService;

    public RequestLoggingFilter(LogService logService) {
        this.logService = logService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;

            String userId = httpServletRequest.getHeader("X-User-Id");
            String companyId = httpServletRequest.getHeader("X-Company-Id");
            MDC.put("userId", userId);
            MDC.put("companyId", companyId);

            RequestLogSchema requestLog = new RequestLogSchema();
            requestLog.setUserId(userId);
            requestLog.setCompanyId(companyId);
            requestLog.setRequestType("REST");
            requestLog.setTimestamp(new Date());
            requestLog.setUri(httpServletRequest.getRequestURI());

            MDC.put("requestId", logService.saveRequestLog(requestLog).getId());
            chain.doFilter(request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            MDC.clear();
        }
    }
}