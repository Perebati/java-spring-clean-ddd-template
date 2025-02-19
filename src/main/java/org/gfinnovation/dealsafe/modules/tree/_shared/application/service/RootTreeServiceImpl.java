package org.gfinnovation.dealsafe.modules.tree._shared.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe._shared.application.GenericServiceImpl;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;
import org.gfinnovation.dealsafe.exception.models.ApplicationException;
import org.gfinnovation.dealsafe.exception.models.InfrastructureException;
import org.gfinnovation.dealsafe.modules.tree._shared.application.service.interfaces.RootTreeService;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.NodeInput;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTree;
import org.gfinnovation.dealsafe.modules.tree._shared.domain.RootTreeHistory;
import org.gfinnovation.dealsafe.modules.tree._shared.infrastructure.repository.interfaces.RootTreeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Handles business operations of RootNode.
 * A root is strongly linked to the input type, that can be
 * either a predefined input or a dynamic one. Depending on the chosen input
 * type, it will be crated a RootStatic (for predefined input) or a RootDynamic (for dynamic input).
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeServiceImpl
 * @since v1.0 (30/11/2024)
 */
@Service
class RootTreeServiceImpl
        extends GenericServiceImpl<RootTree<NodeInput>, RootTreeRepository>
        implements RootTreeService {

    protected RootTreeServiceImpl(RootTreeRepository repository) {
        super(repository);
    }

    /**
     * Return an object of RootTree, it can be either a RootTreeStatic or RootTreeDynamic,
     * response of this method needs handling.
     *
     * @param id RootId of given root.
     * @return Object(RootTree)
     * @throws InfrastructureException Thrown when an error occur on Repository level.
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Override
    public Object readGenericRoot(UUID id) throws SystemGlobalException {
        try {
            return this.repository.readGenericRoot(id, getRepositoryAuth());
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("something went wrong reading a generic root.", e);
        }
    }

    /**
     * Returns a root node through a node id.
     *
     * @param node_id NodeId of given node.
     * @return Optional<UUID>
     * @author Lucas Batista Pereira
     * @since 08/11/2024
     */
    @Override
    public Optional<UUID> findRootIdByNodeId(UUID node_id) throws SystemGlobalException {
        try {
            return this.repository.findRootIdByNodeId(node_id);
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong find a root by node id.", e);
        }
    }


    //TODO: Manter transactions a nível repository
    @Transactional
    public void addHistoryToTree(UUID id, RootTree<?> root) throws SystemGlobalException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(root);

            root.addHistory(new RootTreeHistory(json, root.getVersion()));
            this.update(root.getId(), root);
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong adding history to tree.", e);
        }
    }

    @Transactional
    public void keepHistory(UUID id) throws SystemGlobalException {
        try {
            UUID rootTreeId = this.repository.findRootIdByNodeId(id).orElseThrow(
                    () -> new ApplicationException("Root not found.", null));

            RootTree<?> rootTree = this.read(rootTreeId);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(rootTree);
            rootTree.addHistory(new RootTreeHistory(json, rootTree.getName()));
            this.update(rootTree.getId(), rootTree);
        } catch (SystemGlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("Something went wrong keeping history to tree.", e);
        }
    }
}