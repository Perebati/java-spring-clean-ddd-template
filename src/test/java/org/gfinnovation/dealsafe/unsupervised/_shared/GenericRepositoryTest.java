package org.gfinnovation.dealsafe.unsupervised._shared;

import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.entity.GenericEntity;
import org.gfinnovation.dealsafe._shared.entity.GenericRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericRepositoryTest
 * This class should be used to test if an entity is being handled
 * correctly by the genericRepository.
 * @since 30/10/2024
 */
public abstract class GenericRepositoryTest<
        E extends GenericEntity> {

    protected GenericRepository<E> repository;

    protected abstract E createEntity() throws BadRequestException;

    protected abstract GenericRepository<E> createRepository();

    @BeforeEach
    public void setUp() {
        repository = createRepository();
    }

    @Test
    @Transactional
    public void testCreate() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.create(entity);

        assertNotNull(createdEntity.getId(), "A entidade criada deveria ter um ID gerado.");
        assertNotNull(createdEntity.getCreatedAt(), "A entidade criada deveria ter a data de criação definida.");
    }

    @Test
    @Transactional
    public void testRead() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.create(entity);
        Optional<E> readEntity = repository.read(createdEntity.getId());

        assertTrue(readEntity.isPresent(), "A entidade deveria ser encontrada pelo ID.");
        assertEquals(createdEntity.getId(), readEntity.get().getId(), "O ID da entidade lida deveria corresponder ao ID da entidade criada.");
    }

    @Test
    @Transactional
    public void testReadDeleted() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.create(entity);
        repository.delete(createdEntity.getId());

        Optional<E> readEntity = repository.read(createdEntity.getId());

        assertTrue(readEntity.isEmpty(), "A entidade deletada logicamente não deveria ser encontrada.");
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.create(entity);

        createdEntity.setDeleted(false);
        E updatedEntity = repository.update(createdEntity);

        assertNotNull(updatedEntity.getId(), "O ID da entidade atualizada não deve ser nulo.");
        assertNotEquals(createdEntity.getUpdatedAt(), updatedEntity.getUpdatedAt(), "A data de atualização deveria ser alterada.");
    }

    @Test
    @Transactional
    public void testUpdateDeleted() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.create(entity);
        repository.delete(createdEntity.getId());

        Exception exception = assertThrows(RuntimeException.class, () -> repository.update(createdEntity));
        assertNotNull(exception, "Deveria ser lançada uma exceção ao tentar atualizar uma entidade deletada.");
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.create(entity);
        repository.delete(createdEntity.getId());

        Optional<E> readEntity = repository.read(createdEntity.getId());
        assertTrue(readEntity.isEmpty(), "A entidade deletada não deve ser retornada ao ser lida.");
    }

    @Test
    @Transactional
    public void testFindAllAfterDelete() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.create(entity);

        repository.delete(createdEntity.getId());
        Optional<List<E>> allEntities = repository.findAll();

        assertTrue(allEntities.isEmpty(), "Deveria retornar uma lista de vazia.");
    }

    @Test
    @Transactional
    public void testFindAll() throws Exception {
        E entity = createEntity();
        repository.create(entity);

        Optional<List<E>> allEntities = repository.findAll();

        assertTrue(allEntities.isPresent(), "A busca por todas as entidades não deveria retornar nulo.");
        assertFalse(allEntities.get().isEmpty(), "A lista de entidades não deve estar vazia.");
    }
}