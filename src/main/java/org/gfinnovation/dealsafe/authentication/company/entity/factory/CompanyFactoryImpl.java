package org.gfinnovation.dealsafe.authentication.company.entity.factory;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class CompanyFactoryImpl
 * @authorNote The way authentication works in DealSafe will change in the future,
 * so don't even bother trying to understand this
 * @since 30/10/2024
 */
@Component
@RequiredArgsConstructor
class CompanyFactoryImpl implements CompanyFactory {
    private static final Logger logger = LoggerFactory.getLogger(CompanyFactoryImpl.class);
    private final UserBusiness userBusiness;

    public CompanyEntity createCompany(String name, Set<UUID> users_id) throws BadRequestException, RuntimeException {
        try {
            this.userBusiness.checkAll(users_id);
            Optional<List<UserEntity>> userEntities = this.userBusiness.readAllByIds(new ArrayList<>(users_id));
            Set<UserEntity> userEntitySet = new HashSet<>(userEntities.orElseThrow(() -> new BadRequestException("Requested users were not found!")));
            return new CompanyEntity(name, userEntitySet);
        } catch (Exception e) {
            logger.error("An error occurred when creating a company!");
            throw e;
        }
    }
}
