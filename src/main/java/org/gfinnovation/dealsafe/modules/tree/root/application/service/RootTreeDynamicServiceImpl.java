package org.gfinnovation.dealsafe.modules.tree.root.application.service;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.exception.models.InfrastructureException;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.repository.interfaces.NodeRepository;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeDynamicService;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.factory.interfaces.RootTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeDynamicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class RootTreeDynamicServiceImpl
 * @since v1.0 (06/02/2025)
 */
@Service
public class RootTreeDynamicServiceImpl
        extends GenericServiceImpl<RootTreeDynamic, RootTreeDynamicRepository>
        implements RootTreeDynamicService {
    private final RootTreeFactory rootTreeFactory;
    private final NodeRepository nodeRepository;

    @Autowired
    public RootTreeDynamicServiceImpl(
            RootTreeDynamicRepository repository,
            RootTreeFactory rootTreeFactory,
            NodeRepository nodeRepository
    ) {
        super(repository);
        this.rootTreeFactory = rootTreeFactory;
        this.nodeRepository = nodeRepository;
    }

    /**
     * Creates a Root node that references a dynamic input by its id.
     *
     * @param name          Name of given root node.
     * @param dynamic_input Identification of referenced dynamic input.
     * @return RootTreeDynamic
     * @throws ApplicationException    Thrown when an error occurs on business level.
     * @throws ValidationException     Thrown when an error occurs on factory level.
     * @throws InfrastructureException Thrown when an error occurs on repository level.
     */
    @Override
    public RootTreeDynamic create(String name, UUID dynamic_input) throws SystemGlobalException {
        try {
            return this.repository
                    .create(rootTreeFactory
                            .produce(
                                    name,
                                    dynamic_input
                            ), getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong creating a dynamic root.");
        }
    }

    /**
     * Deletes a Root node by its id.
     *
     * @param id Identification of given root node.
     * @throws ApplicationException    Thrown when an error occurs on business level.
     * @throws InfrastructureException Thrown when an error occurs on repository level.
     */
    @Override
    public void delete(UUID id) throws SystemGlobalException {
        try {
            this.nodeRepository.deleteNode(id, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong deleting a dynamic root.");
        }
    }
}
