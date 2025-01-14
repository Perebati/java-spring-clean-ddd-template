package org.gfinnovation.dealsafe._sandbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.engine.Engine;
import org.gfinnovation.dealsafe.modules.input.domain.InputEntity;
import org.gfinnovation.dealsafe.modules.operation.application.service.interfaces.OperationService;
import org.gfinnovation.dealsafe.modules.operation.domain.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.modules.operation.domain.comparison.ComparisonTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.application.service.interfaces.TreeService;
import org.gfinnovation.dealsafe.modules.tree.domain.TreeNode;
import org.gfinnovation.dealsafe.modules.tree.domain.valueobjects.TreeDynamicRoot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class TreeTest
 * @since 30/10/2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("teste")
@Tag(name = "Teste")
public class TreeTest {
    private final TreeService treeService;
    private final UserBusiness userBusiness;
    private final CompanyBusiness companyBusiness;
    private final OperationService operationService;
    private final InputTest inputController;
    private final Neo4jTest neo4jTest;
    private final Engine engine;


    @PostMapping("tree2")
    public boolean testeTree2(@RequestBody JsonNode jsonNode) throws RuntimeException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, JsonProcessingException, BadRequestException {
        try {
            UserEntity createdUser = this.userBusiness.create("Lucas Teste");

            CompanyEntity createdCompany = this.companyBusiness.create("Teste", Collections.singleton(createdUser.getId()));

            InputEntity inputEntity = this.inputController.create2();

            CompletableFuture<TreeDynamicRoot> createdRoot = this.treeService.getRootTreeBusiness().create("ROOT Teste", inputEntity.getUser_id());

            TreeNode createdNode1 = this.treeService.getNodeTreeBusiness().create("NODE 1", 0, createdRoot.get().getId());

            TreeNode createdNode2 = this.treeService.getNodeTreeBusiness().create("NODE 2", 0, createdNode1.getId());

            ComparisonOperationEntity comparisonOperationEntity = this.operationService.getComparisonOperationBusiness().create(ComparisonTypeEnum.GREATERTHANOREQUAL, "/idade", "18", createdNode2.getId());

            ComparisonOperationEntity comparisonOperationEntity2 = this.operationService.getComparisonOperationBusiness().create(ComparisonTypeEnum.LESSTHANOREQUAL, "/idade", "65", createdNode2.getId());

            //OperationActionEntity operationActionEntity = this.operationActionFactory.createOperationAction("http://localhost:8081/teste", "Olá mundo", operationEntity.getId());

            //OperationActionEntity operationActionEntity2 = this.operationActionFactory.createOperationAction("http://localhost:8081/teste", "Olá mundo", operationEntity2.getId());

            TreeNode createdNode3 = this.treeService.getNodeTreeBusiness().create("NODE 3", 0, createdNode1.getId());

            ComparisonOperationEntity comparisonOperationEntity3 = this.operationService.getComparisonOperationBusiness().create(ComparisonTypeEnum.DIFFERENT, "/CPF", "11330176650", createdNode3.getId());

            TreeNode createdNode4 = this.treeService.getNodeTreeBusiness().create("Node4", 0, createdNode3.getId());

            ComparisonOperationEntity comparisonOperationEntity1 = this.operationService.getComparisonOperationBusiness().create(ComparisonTypeEnum.EQUAL, "/endereco/rua", "Rua do Limão", createdNode4.getId());

            ComparisonOperationEntity comparisonOperationEntity4 = this.operationService.getComparisonOperationBusiness().create(ComparisonTypeEnum.EQUAL, "/endereco/bairro", "Bairro do Limão", createdNode4.getId());

            //OperationActionEntity operationActionEntity3 = this.operationActionFactory.createOperationAction("http://localhost:8081/teste", "Olá mundo", operationEntity4.getId());

            ObjectMapper mapper = new ObjectMapper();

            mapper.findAndRegisterModules();

            createdRoot = (CompletableFuture<TreeDynamicRoot>) this.treeService.getRootTreeBusiness().readGenericRoot(createdRoot.get().getId());

            String validationTreeJson = mapper.writeValueAsString(this.treeService.getRootTreeBusiness().readGenericRoot(createdRoot.get().getId()));

            JsonNode validationRoot = mapper.readTree(validationTreeJson);

            Optional<UUID> rootId = this.treeService.getRootTreeBusiness().findRootIdByNodeId(createdNode2.getId());

            return engine.bfsValidation(validationRoot, jsonNode);

        } catch (RuntimeException | NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                 InstantiationException | JsonProcessingException | BadRequestException e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("tree3")
    public void testeTree2() {
        this.neo4jTest.printGreeting("hello world!");
    }
}