package org.gfinnovation.dealsafe.domains.tree.application.business.components.interfaces;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.application.GenericBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.EntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeTreeBusiness
 * @since 30/10/2024
 */

public interface NodeTreeBusiness extends GenericBusiness<NodeTreeEntity> {
    NodeTreeEntity create(String name, Integer sequence, UUID parent_id) throws BusinessException, FactoryException, RepositoryException, ValidationException, EntityNotFoundException;

    NodeTreeEntity updateSync(NodeTreeEntity entity) throws RuntimeException;

}
