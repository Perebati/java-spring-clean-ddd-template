package org.gfinnovation.dealsafe._sandbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;
import org.gfinnovation.dealsafe.domains.operation.application.business.interfaces.OperationBusiness;
import org.gfinnovation.dealsafe.domains.operation.entity.ComparisonOperationEntity;
import org.gfinnovation.dealsafe.domains.operation.entity.comparison.ComparisonTypeEnum;
import org.gfinnovation.dealsafe.domains.tree.application.business.interfaces.TreeBusiness;
import org.gfinnovation.dealsafe.domains.tree.entity.NodeTreeEntity;
import org.gfinnovation.dealsafe.domains.tree.entity.RootTreeDynamicEntity;
import org.gfinnovation.dealsafe.engine.Engine;
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
public class TreeTest {
    private final TreeBusiness treeBusiness;
    private final UserBusiness userBusiness;
    private final CompanyBusiness companyBusiness;
    private final OperationBusiness operationBusiness;
    private final InputTest inputController;
    private final Neo4jTest neo4jTest;
    private final Engine engine;


    @PostMapping("tree2")
    public boolean testeTree2(@RequestBody JsonNode jsonNode) throws RuntimeException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, JsonProcessingException, BadRequestException {
        try {
            UserEntity createdUser = this.userBusiness.create("Lucas Teste");

            CompanyEntity createdCompany = this.companyBusiness.create("Teste", Collections.singleton(createdUser.getId()));

            InputEntity inputEntity = this.inputController.create2();

            CompletableFuture<RootTreeDynamicEntity> createdRoot = this.treeBusiness.getRootTreeBusiness().create("ROOT Teste", inputEntity.getUser_id());

            NodeTreeEntity createdNode1 = this.treeBusiness.getNodeTreeBusiness().create("NODE 1", 0, createdRoot.get().getId());

            NodeTreeEntity createdNode2 = this.treeBusiness.getNodeTreeBusiness().create("NODE 2", 0, createdNode1.getId());

            ComparisonOperationEntity comparisonOperationEntity = this.operationBusiness.getComparisonOperationBusiness().create(ComparisonTypeEnum.GREATERTHANOREQUAL, "/idade", "18", createdNode2.getId());

            ComparisonOperationEntity comparisonOperationEntity2 = this.operationBusiness.getComparisonOperationBusiness().create(ComparisonTypeEnum.LESSTHANOREQUAL, "/idade", "65", createdNode2.getId());

            //OperationActionEntity operationActionEntity = this.operationActionFactory.createOperationAction("http://localhost:8081/teste", "Olá mundo", operationEntity.getId());

            //OperationActionEntity operationActionEntity2 = this.operationActionFactory.createOperationAction("http://localhost:8081/teste", "Olá mundo", operationEntity2.getId());

            NodeTreeEntity createdNode3 = this.treeBusiness.getNodeTreeBusiness().create("NODE 3", 0, createdNode1.getId());

            ComparisonOperationEntity comparisonOperationEntity3 = this.operationBusiness.getComparisonOperationBusiness().create(ComparisonTypeEnum.DIFFERENT, "/CPF", "11330176650", createdNode3.getId());

            NodeTreeEntity createdNode4 = this.treeBusiness.getNodeTreeBusiness().create("Node4", 0, createdNode3.getId());

            ComparisonOperationEntity comparisonOperationEntity1 = this.operationBusiness.getComparisonOperationBusiness().create(ComparisonTypeEnum.EQUAL, "/endereco/rua", "Rua do Limão", createdNode4.getId());

            ComparisonOperationEntity comparisonOperationEntity4 = this.operationBusiness.getComparisonOperationBusiness().create(ComparisonTypeEnum.EQUAL, "/endereco/bairro", "Bairro do Limão", createdNode4.getId());

            //OperationActionEntity operationActionEntity3 = this.operationActionFactory.createOperationAction("http://localhost:8081/teste", "Olá mundo", operationEntity4.getId());

            ObjectMapper mapper = new ObjectMapper();

            mapper.findAndRegisterModules();

            createdRoot = (CompletableFuture<RootTreeDynamicEntity>) this.treeBusiness.getRootTreeBusiness().readGenericRoot(createdRoot.get().getId());

            String validationTreeJson = mapper.writeValueAsString(this.treeBusiness.getRootTreeBusiness().readGenericRoot(createdRoot.get().getId()));

            JsonNode validationRoot = mapper.readTree(validationTreeJson);

            Optional<UUID> rootId = this.treeBusiness.getRootTreeBusiness().findRootIdByNodeId(createdNode2.getId());

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