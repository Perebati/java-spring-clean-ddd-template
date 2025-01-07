package org.gfinnovation.dealsafe.configuration.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessAuthenticationException;
import org.gfinnovation.dealsafe.configuration.logging.LogService;
import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.RequestLogSchema;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

/**
 * Every single requisition in this system is registered, this class does that.
 * This class also should throw an exception if 'user_id' and 'company_id' are not present,
 * but because this is a beta and proper authentication is not yet implemented, this class
 * is shallow.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RequestLoggingFilter
 * @since 30/10/2024
 */
@Component
public class RequestHandler implements Filter {

    private final LogService logService;

    public RequestHandler(LogService logService) {
        this.logService = logService;
    }


    private Key getSigningKey() {
        String jwtSecret = "segredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredo";
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String token = getBearerToken(httpServletRequest);

            if (token != null) {
                String requestId = generateUniqueRequestId();

                Jws<Claims> claims = Jwts.parserBuilder()
                        .setSigningKey(getSigningKey())
                        .build()
                        .parseClaimsJws(token);

                String user_id = claims.getBody().get("user_id", String.class);
                String company_id = claims.getBody().get("company_id", String.class);

                if (user_id == null || company_id == null) {
                    throw new BusinessAuthenticationException("Authentication error.");
                }

                RequestLogSchema requestLog = new RequestLogSchema();
                requestLog.setUserId(user_id);
                requestLog.setCompanyId(company_id);
                requestLog.setRequestType("REST");
                requestLog.setTimestamp(new Date());
                requestLog.setUri(httpServletRequest.getRequestURI());
                logService.saveRequestLogAsync(requestLog);

                MDC.put("user_id", user_id);
                MDC.put("company_id", company_id);
                MDC.put("request_id", requestId);
            }

            chain.doFilter(request, response);
        } catch (JwtException e) {
            throw new BusinessAuthenticationException("Invalid or expired token.");
        } catch (Exception e) {
            throw new BusinessAuthenticationException("Something went wrong validation business user.");
        } finally {
            MDC.clear();
        }
    }

    private String generateUniqueRequestId() {
        String requestId;
        do {
            requestId = UUID.randomUUID().toString();
        } while (logService.requestIdExists(requestId));
        return requestId;
    }

    private String getBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}