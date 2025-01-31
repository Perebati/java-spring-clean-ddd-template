package org.gfinnovation.dealsafe.modules.tree.structure.domain.root.service;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe._shared.modules.application.GenericDomainServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.factory.interfaces.RootTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.structure.infrastructure.root.repository.interfaces.RootTreeDynamicRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.service.interfaces.RootTreeDynamicDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class RootTreeDynamicDomainServiceImpl
        extends GenericDomainServiceImpl<RootTreeDynamic, RootTreeDynamicRepository>
        implements RootTreeDynamicDomainService {
    private final RootTreeFactory rootTreeFactory;

    @Autowired
    public RootTreeDynamicDomainServiceImpl(
            RootTreeDynamicRepository repository,
            RootTreeFactory rootTreeFactory
    ) {
        super(repository);
        this.rootTreeFactory = rootTreeFactory;
    }

    /**
     * Creates a Root node that references a dynamic input by its id.
     *
     * @param name          Name of given root node.
     * @param dynamic_input Identification of referenced dynamic input.
     * @return RootTreeDynamic
     * @throws ServiceException    Thrown when an error occurs on business level.
     * @throws FactoryException    Thrown when an error occurs on factory level.
     * @throws ValidationException Thrown when an error occurs on factory level.
     * @throws RepositoryException Thrown when an error occurs on repository level.
     */
    @Override
    public CompletableFuture<RootTreeDynamic> create(String name, UUID dynamic_input) throws ServiceException {
        try {
            return this.repository
                    .createAsync(rootTreeFactory
                            .produce(
                                    name,
                                    dynamic_input
                            ), getRepositoryAuth());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong creating a dynamic root.", e);
        }
    }
}
