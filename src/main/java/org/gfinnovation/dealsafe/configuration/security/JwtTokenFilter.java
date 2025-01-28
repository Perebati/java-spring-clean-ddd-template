package org.gfinnovation.dealsafe.configuration.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gfinnovation.dealsafe.configuration.logging.LogService;
import org.gfinnovation.dealsafe.configuration.logging.infrastrutcture.RequestLogSchema;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.privateKey}")
    private String privateKey;

    @Value("${jwt.publicKey}")
    private String publicKey;

    private final LogService logService;

    public JwtTokenFilter(LogService logService) {
        this.logService = logService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws IOException {
        try {
            String jwt = getBearerToken(request);

            if (jwt != null) {
                String requestId = generateUniqueRequestId();

                Jws<Claims> jwsClaims = Jwts.parserBuilder()
                        .setSigningKey(getPublicKeyFromString(publicKey))
                        .build()
                        .parseClaimsJws(jwt);

                Date expiration = jwsClaims.getBody().getExpiration();
                if (expiration != null && expiration.before(new Date())) {
                    throw new ExpiredJwtException(jwsClaims.getHeader(), jwsClaims.getBody(), "Expired token.");
                }

                String userId = jwsClaims.getBody().get("userId", String.class);
                String whitelabelId = jwsClaims.getBody().get("whitelabelId", String.class);

                if (userId != null && whitelabelId != null) {
                    RequestLogSchema requestLog = new RequestLogSchema();
                    requestLog.setUserId(userId);
                    requestLog.setCompanyId(whitelabelId);
                    requestLog.setRequestType("REST");
                    requestLog.setTimestamp(new Date());
                    requestLog.setUri(request.getRequestURI());
                    logService.saveRequestLogAsync(requestLog);

                    MDC.put("userId", userId);
                    MDC.put("whitelabelId", whitelabelId);
                    MDC.put("requestId", requestId);
                }
            }

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException ex) {
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Expired JWT token.");
        } catch (JwtException ex) {
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token.");
        } catch (Exception ex) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error validating JWT token.");
        } finally {
            MDC.clear();
        }
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(status);
        String json = String.format("{\"error\": \"%s\"}", message);
        response.getWriter().write(json);
    }

    private Key getSigningKey() {
        byte[] keyBytes = privateKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String getBearerToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    private String generateUniqueRequestId() {
        return UUID.randomUUID().toString();
    }

    private Key getPublicKeyFromString(String base64PublicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
}