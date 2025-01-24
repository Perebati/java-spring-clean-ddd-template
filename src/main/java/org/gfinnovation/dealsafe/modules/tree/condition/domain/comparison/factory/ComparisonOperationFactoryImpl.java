package org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.factory;

import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryEntityNotFoundException;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.InputEntity;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.Comparison;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.factory.interfaces.ComparisonOperationFactory;
import org.gfinnovation.dealsafe.modules.tree.condition.domain.comparison.logic.enums.ComparisonTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.service.interfaces.RootTreeService;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

/**
 * Handles Comparisons creation.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperationFactory
 * @since 30/10/2024
 */

@Component
public class ComparisonOperationFactoryImpl implements ComparisonOperationFactory {
    private final RootTreeService rootTreeService;
    private final InputService inputService;

    public ComparisonOperationFactoryImpl(
            RootTreeService rootTreeService,
            InputService inputService
    ) {
        this.rootTreeService = rootTreeService;
        this.inputService = inputService;
    }

    /**
     * This method creates an operation,
     * however before creating it checks the node in order to find the root node,
     * then through the root it checks if the jsonPath existis inside the input variable.
     * After all this validation, the Comparison is created.
     *
     * @param type     Comparison type.
     * @param jsonPath JsonPath to compared variable.
     * @param variable Variable value.
     * @param node_id  Node parentId.
     * @return Comparison
     * @throws FactoryException                  Thrown when something wrong happened on factory layer.
     * @throws BadRequestException               Thrown when there's something wrong in user input.
     * @throws RepositoryEntityNotFoundException Thrown when something wrong happened on reading entities.
     * @throws ValidationException               Thrown when something wrong happened on factory layer.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    public Comparison produce(ComparisonTypeEnum type, String jsonPath, String variable, UUID node_id) throws FactoryException, BadRequestException {
        try {
            Object rootTreeEntity = this.rootTreeService
                    .readGenericRoot(this.rootTreeService
                            .findRootIdByNodeId(node_id)
                            .orElseThrow(() -> new RepositoryEntityNotFoundException("Factory: Root parent not found!")));
            if (rootTreeEntity instanceof RootTreeStatic) {
                PredefinedTypeEnum predefinedTypeEnum = ((RootTreeStatic) rootTreeEntity).getInput_type();
                try {
                    predefinedTypeEnum.validateJsonPath(predefinedTypeEnum, jsonPath);
                } catch (NoSuchFieldException e) {
                    throw new BadRequestException("Factory: field not found: " + e.getMessage());
                }
            } else if (rootTreeEntity instanceof RootTreeDynamic) {
                InputEntity inputEntity = this.inputService.read(((RootTreeDynamic) rootTreeEntity).getDynamicInputId());
                inputEntity.validateJsonPathAndType(jsonPath, variable);
            }

            return new Comparison(type, jsonPath, Set.of(variable));
        } catch (BadRequestException | RepositoryEntityNotFoundException e) {
            throw new FactoryException(e.getMessage());
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a comparison operation.", e);
        }
    }
}