package org.gfinnovation.dealsafe.modules.tree.root.application.service;

import org.gfinnovation.dealsafe._shared.modules.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeStaticService;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.factory.interfaces.RootTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.root.domain.valueobjects.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.root.infrastructure.repository.interfaces.RootTreeStaticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class RootTreeStaticServiceImpl
        extends GenericServiceImpl<RootTreeStatic, RootTreeStaticRepository>
        implements RootTreeStaticService {
    private final RootTreeFactory rootTreeFactory;

    @Autowired
    protected RootTreeStaticServiceImpl(
            RootTreeStaticRepository repository,
            RootTreeFactory rootTreeFactory
    ) {
        super(repository);
        this.rootTreeFactory = rootTreeFactory;
    }

    /**
     * Creates a Root node that references a static input by its type.
     *
     * @param name         Name of given root node.
     * @param static_input Identification of referenced predefined input.
     * @return RootTreeStatic
     * @throws ApplicationException Thrown when an error occurs on business level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    @Override
    public RootTreeStatic create(String name, PredefinedTypeEnum static_input) throws SystemGlobalException {
        try {
            return this.repository
                    .create(
                            rootTreeFactory
                                    .produce(
                                            name,
                                            static_input
                                    ), getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Business: Something went wrong creating a static root.");
        }
    }
}