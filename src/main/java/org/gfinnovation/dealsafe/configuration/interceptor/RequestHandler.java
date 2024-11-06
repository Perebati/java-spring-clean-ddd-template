package org.gfinnovation.dealsafe.configuration.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gfinnovation.dealsafe.configuration.logging.LogService;
import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.RequestLogSchema;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            String token = getBearerToken(httpServletRequest);

            if (token != null) {
                Jws<Claims> claims = Jwts.parserBuilder()
                        .setSigningKey(getSigningKey())
                        .build()
                        .parseClaimsJws(token);

                String user_id = claims.getBody().get("user_id", String.class);
                String company_id = claims.getBody().get("company_id", String.class);

                if (user_id == null || company_id == null) {
                    httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Claims user_id ou company_id ausentes ou inválidos");
                    return;
                }

                MDC.put("user_id", user_id);
                MDC.put("company_id", company_id);

                RequestLogSchema requestLog = new RequestLogSchema();
                requestLog.setUserId(user_id);
                requestLog.setCompanyId(company_id);
                requestLog.setRequestType("REST");
                requestLog.setTimestamp(new Date());
                requestLog.setUri(httpServletRequest.getRequestURI());

                String requestId = logService.saveRequestLog(requestLog).getId();
                MDC.put("request_id", requestId);
            }

            chain.doFilter(request, response);
        } catch (JwtException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado");
        } finally {
            MDC.clear();
        }
    }

    private String getBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}