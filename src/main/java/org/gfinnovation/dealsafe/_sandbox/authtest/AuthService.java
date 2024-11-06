package org.gfinnovation.dealsafe._sandbox.authtest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Set;

/**
 * Test, don't bother.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class AuthService
 * @since 01/11/2024
 */
@Service
public class AuthService {
    private final CompanyBusiness companyBusiness;
    private final UserBusiness userBusiness;

    public AuthService(CompanyBusiness companyBusiness, UserBusiness userBusiness) {
        this.companyBusiness = companyBusiness;
        this.userBusiness = userBusiness;
    }

    @Transactional
    public String registerOrLogin(String email) {
        UserEntity user = userBusiness.findUserByEmail(email)
                .orElseGet(() -> {
                    try {
                        UserEntity userEntity = userBusiness.create(email);
                        CompanyEntity company = companyBusiness.create("Tabajara", Set.of(userEntity.getId()));
                        userEntity.setCompanyId(company.getId());
                        userBusiness.update(userEntity);
                        return userEntity;
                    } catch (Exception e) {
                        throw new RuntimeException("Erro!");
                    }
                });

        return generateToken(user);
    }

    private Key getSigningKey() {
        String jwtSecret = "segredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredosegredo";
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String generateToken(UserEntity user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("user_id", user.getId())
                .claim("company_id", user.getCompanyId())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
