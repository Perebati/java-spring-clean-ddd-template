package org.gfinnovation.dealsafe.domains.input.application.business;

import lombok.RequiredArgsConstructor;
import org.gfinnovation.dealsafe.domains.input.application.business.interfaces.InputBusiness;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;
import org.gfinnovation.dealsafe.domains.input.entity.factory.interfaces.InputFactory;
import org.gfinnovation.dealsafe.domains.input.entity.repository.InputRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * This class handles dynamic input structures for a validation tree.
 * Inputs can be dynamic or predefined.
 * If the input is dynamic, it passes through this business class.
 * There's no business class for static input, all validations for that
 * type of input is done inside an enumerator.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputBusinessImpl
 * @since 30/10/2024
 */
@Service
@RequiredArgsConstructor
class InputBusinessImpl implements InputBusiness {
    private final InputRepository inputRepository;
    private final InputFactory inputFactory;

    @Override
    public InputEntity create(UUID user_id, UUID company_id, String name, String json) {
        return this.inputRepository.create(this.inputFactory.produce(user_id, company_id, name, json));
    }

    @Override
    public Optional<InputEntity> read(UUID id) {
        return this.inputRepository.read(id);
    }

    @Override
    public InputEntity update(InputEntity entity) {
        return this.inputRepository.update(entity);
    }

    @Override
    public void delete(UUID id) {
        this.inputRepository.delete(id);
    }

    @Override
    public Optional<List<InputEntity>> readAll() {
        return this.inputRepository.findAll();
    }

    @Override
    public Optional<List<InputEntity>> readAllByIds(List<UUID> ids) {
        return this.inputRepository.findAllByIds(ids);
    }

    @Override
    public void check(UUID id) {
        this.inputRepository.check(id);
    }

    @Override
    public void checkAll(Set<UUID> ids) {
        this.inputRepository.checkAll(ids);
    }
}