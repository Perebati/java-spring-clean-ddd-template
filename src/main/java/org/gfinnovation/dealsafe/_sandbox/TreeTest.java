package org.gfinnovation.dealsafe._sandbox;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums.PredefinedTypeEnum;
import org.gfinnovation.dealsafe.modules.tree.structure.adapter.dto.request.NodeCreationDTO;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTree;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.NodeTreeAction;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.node.service.interfaces.NodeTreeService;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.RootTreeStatic;
import org.gfinnovation.dealsafe.modules.tree.structure.domain.root.service.interfaces.RootTreeService;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.UUID;

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
    private final UserBusiness userBusiness;
    private final CompanyBusiness companyBusiness;
    private final RootTreeService rootTreeService;
    private final NodeTreeService nodeTreeService;

    @PostMapping("tree2")
    public boolean testeTree2() throws RuntimeException {
        try {
            UserEntity createdUser = this.userBusiness.create("Lucas Teste");

            CompanyEntity createdCompany = this.companyBusiness.create("Teste", Collections.singleton(createdUser.getId()));

            MDC.put("user_id", createdUser.getId().toString());
            MDC.put("company_id", createdCompany.getId().toString());
            MDC.put("request_id", UUID.randomUUID().toString());

            RootTreeStatic createdRoot = this.rootTreeService.create("ROOT Teste", PredefinedTypeEnum.TESTE);

            NodeTree<?> node1 = this.nodeTreeService.create(new NodeCreationDTO("NODE 1", createdRoot.getId()));

            NodeTree<?> node2 = this.nodeTreeService.create(new NodeCreationDTO("NODE 3", createdRoot.getId()));

            NodeTree<?> createdNode2 = this.nodeTreeService.create(new NodeCreationDTO("NODE 2", node1.getId()));

            NodeTreeAction action = this.nodeTreeService.createAction(new NodeCreationDTO("Action 1", createdNode2.getId()));

            createdRoot = this.rootTreeService.readRootStatic(createdRoot.getId()).get();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}