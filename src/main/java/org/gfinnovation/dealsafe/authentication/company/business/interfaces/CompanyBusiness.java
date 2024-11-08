package org.gfinnovation.dealsafe.authentication.company.business.interfaces;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.application.interfaces.GenericBusinessOld;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;

import java.util.Set;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface CompanyBusiness
 * @since 30/10/2024
 */
public interface CompanyBusiness extends GenericBusinessOld<CompanyEntity> {
    CompanyEntity create(String name, Set<UUID> users_id) throws RuntimeException, BadRequestException;
}
