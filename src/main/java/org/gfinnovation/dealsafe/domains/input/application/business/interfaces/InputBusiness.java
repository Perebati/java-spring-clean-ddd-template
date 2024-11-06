package org.gfinnovation.dealsafe.domains.input.application.business.interfaces;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.application.GenericBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;

import java.util.concurrent.CompletableFuture;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputBusiness
 * @since 30/10/2024
 */

public interface InputBusiness extends GenericBusiness<InputEntity> {
    CompletableFuture<InputEntity> create(String name, String json) throws FactoryException, ValidationException, RepositoryException, BusinessException;
}
