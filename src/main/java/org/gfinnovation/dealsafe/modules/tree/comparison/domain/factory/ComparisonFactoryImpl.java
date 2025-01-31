package org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory;

import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryEntityNotFoundException;
import org.gfinnovation.dealsafe.modules.input.application.service.interfaces.InputService;
import org.gfinnovation.dealsafe.modules.input.domain.InputEntity;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonMulti;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.ComparisonSingular;
import org.gfinnovation.dealsafe.modules.tree.comparison.domain.factory.interfaces.ComparisonFactory;
import org.gfinnovation.dealsafe.modules.tree.node.domain.Node;
import org.gfinnovation.dealsafe.modules.tree.root.application.service.interfaces.RootTreeService;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeDynamic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.root.domain.valueobjects.PredefinedTypeEnum;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Handles Comparisons creation.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ComparisonOperationFactory
 * @since 30/10/2024
 */

@Component
public class ComparisonFactoryImpl implements ComparisonFactory {
    private final RootTreeService rootTreeService;
    private final InputService inputService;

    public ComparisonFactoryImpl(
            RootTreeService rootTreeService,
            InputService inputService
    ) {
        this.rootTreeService = rootTreeService;
        this.inputService = inputService;
    }

    /**
     * This method creates an operation,
     * however before creating it checks the node in order to find the root node,
     * then through the root it checks if the jsonPath exists inside the input variable.
     * After all this validation, the Comparison is created.
     *
     * @param type     Comparison type.
     * @param jsonPath JsonPath to compared variable.
     * @param variable Variable value.
     * @param parent   Parent node.
     * @return Comparison
     * @throws FactoryException                  Thrown when something wrong happened on factory layer.
     * @throws BadRequestException               Thrown when there's something wrong in user input.
     * @throws RepositoryEntityNotFoundException Thrown when something wrong happened on reading entities.
     * @throws ValidationException               Thrown when something wrong happened on factory layer.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    public ComparisonSingular produce(
            ComparisonSingular.ComparisonSingularTypeEnum type,
            String jsonPath,
            String variable,
            Node<?> parent
    ) throws FactoryException, BadRequestException {
        try {
            Object rootTreeEntity = this.rootTreeService
                    .readGenericRoot(this.rootTreeService
                            .findRootIdByNodeId(parent.getId())
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

            return new ComparisonSingular(type, jsonPath, variable, parent);
        } catch (BadRequestException | RepositoryEntityNotFoundException e) {
            throw new FactoryException(e.getMessage());
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a comparison operation.", e);
        }
    }

    public ComparisonMulti produce(
            ComparisonMulti.ComparisonMultiTypeEnum comparisonTypeEnum,
            String jsonVariablePath,
            List<String> expectedVars,
            Node<?> parent
    ) throws FactoryException, BadRequestException {
        try {
            Object rootTreeEntity = this.rootTreeService
                    .readGenericRoot(this.rootTreeService
                            .findRootIdByNodeId(parent.getId())
                            .orElseThrow(() -> new RepositoryEntityNotFoundException("Factory: Root parent not found!")));
            if (rootTreeEntity instanceof RootTreeStatic) {
                PredefinedTypeEnum predefinedTypeEnum = ((RootTreeStatic) rootTreeEntity).getInput_type();
                try {
                    predefinedTypeEnum.validateJsonPath(predefinedTypeEnum, jsonVariablePath);
                } catch (NoSuchFieldException e) {
                    throw new BadRequestException("Factory: field not found: " + e.getMessage());
                }
            } else if (rootTreeEntity instanceof RootTreeDynamic) {
                InputEntity inputEntity = this.inputService.read(((RootTreeDynamic) rootTreeEntity).getDynamicInputId());
                for (String variable : expectedVars) inputEntity.validateJsonPathAndType(jsonVariablePath, variable);
            }

            return new ComparisonMulti(comparisonTypeEnum, jsonVariablePath, expectedVars, parent);
        } catch (BadRequestException | RepositoryEntityNotFoundException e) {
            throw new FactoryException(e.getMessage());
        } catch (Exception e) {
            throw new FactoryException("Factory: Something went wrong creating a comparison operation.", e);
        }
    }
}