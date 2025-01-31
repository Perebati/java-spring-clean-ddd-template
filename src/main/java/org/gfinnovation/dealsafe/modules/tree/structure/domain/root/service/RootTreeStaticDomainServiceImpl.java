package org.gfinnovation.dealsafe.modules.tree.structure.domain.root.service;

import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.GenericDomainServiceImpl;
import org.gfinnovation.dealsafe.exception.models.layered.ServiceException;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.factory.interfaces.RootTreeFactory;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.repository.RootTreeStaticRepository;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.service.interfaces.RootTreeStaticDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class RootTreeStaticDomainServiceImpl
        extends GenericDomainServiceImpl<RootTreeStatic, RootTreeStaticRepository>
        implements RootTreeStaticDomainService {
    private final RootTreeFactory rootTreeFactory;

    @Autowired
    protected RootTreeStaticDomainServiceImpl(
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
     * @throws ServiceException Thrown when an error occurs on business level.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */
    @Override
    public RootTreeStatic create(String name, PredefinedTypeEnum static_input) throws ServiceException, BadRequestException {
        try {
            return this.repository
                    .createSync(
                            rootTreeFactory
                                    .produce(
                                            name,
                                            static_input
                                    ), getRepositoryAuth());
        } catch (ServiceException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Business: Something went wrong creating a static root.", e);
        }
    }

}
