package org.gfinnovation.dealsafe.domains.input.infrastructure.inbound;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.configuration.exception.models.EntityNotFoundException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.RepositoryException;
import org.gfinnovation.dealsafe.domains.input.application.business.interfaces.InputBusiness;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;
import org.gfinnovation.dealsafe.domains.input.infrastructure.inbound.interfaces.InputController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputController
 * @since 30/10/2024
 */

@Controller
class InputControllerImpl implements InputController {
    private final InputBusiness inputBusiness;

    public InputControllerImpl(InputBusiness inputBusiness) {
        this.inputBusiness = inputBusiness;
    }

    @Override
    public ResponseEntity<InputEntity> createInput(@RequestParam String name, @RequestBody String json) throws FactoryException, ValidationException, RepositoryException, BusinessException {
        try {
            CompletableFuture<InputEntity> input = this.inputBusiness.create(name, json);
            input.get().setName("teste");
            input.get().setDeletedAt(LocalDateTime.now());
            input = this.inputBusiness.update(input.get());
            this.inputBusiness.delete(input.get().getId());
            return ResponseEntity.ok(this.inputBusiness.read(input.get().getId()));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param read_id
     * @return
     * @throws FactoryException
     * @throws ValidationException
     * @throws RepositoryException
     * @throws BusinessException
     * @author Lucas Batista Pereira
     * @since 06/11/2024
     */
    @Override
    public InputEntity readInput(UUID read_id) throws FactoryException, ValidationException, RepositoryException, BusinessException {
        try {
            return this.inputBusiness.read(read_id);
        } catch (FactoryException | ValidationException | RepositoryException | BusinessException |
                 EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Error", e);
        }
    }
}
