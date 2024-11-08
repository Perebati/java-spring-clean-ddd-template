package org.gfinnovation.dealsafe.domains.tree.entity.factory.components;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.factory.components.interfaces.NodeTreeFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Handles the creation and validation of Nodes.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class NodeFactoryImpl
 * @since 30/10/2024
 */

@Component
class NodeTreeFactoryImpl implements NodeTreeFactory {

    /**
     * Validates business information and creates a NodeTreeEntity.
     *
     * @param user_id    UserId.
     * @param company_id CompanyID.
     * @param name       Name of the node.
     * @param sequence   Sequence position of given node.
     * @return NodeTreeEntity
     * @throws FactoryException    Thrown when an error occurs on factory level.
     * @throws ValidationException Thrown when an error occurs on factory level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    public NodeTreeEntity produce(UUID user_id, UUID company_id, String name, Integer sequence) throws FactoryException {
        try {
            return new NodeTreeEntity(user_id, company_id, name, sequence);
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a node.", e);
        }
    }
}
