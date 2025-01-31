package org.gfinnovation.dealsafe.tests._shared;

import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe._shared.modules.application.RepositoryAuth;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericClass;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.interfaces.GenericBusinessRepository;
import org.gfinnovation.dealsafe.exception.models.layered.RepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class should be used to test if an entity is being handled
 * correctly by the genericRepository.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericRepositoryTest
 * @since 30/10/2024
 */
public abstract class GenericBusinessRepositoryTest<
        E extends GenericClass> {

    protected GenericBusinessRepository<E> repository;
    protected RepositoryAuth repositoryAuth;

    protected abstract E createEntity() throws BadRequestException;

    protected abstract GenericBusinessRepository<E> createRepository();

    protected abstract RepositoryAuth createRepositoryAuth() throws BadRequestException;

    @BeforeEach
    public void setUp() throws BadRequestException {
        repository = createRepository();
        repositoryAuth = createRepositoryAuth();
    }

    @Test
    @Transactional
    public void testCreate() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.createSync(entity, repositoryAuth);

        assertNotNull(createdEntity.getId(), "A entidade criada deveria ter um ID gerado.");
        assertNotNull(createdEntity.getCreatedAt(), "A entidade criada deveria ter a data de criação definida.");
    }

    @Test
    @Transactional
    public void testRead() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.createSync(entity, repositoryAuth);
        E readEntity = repository.read(createdEntity.getId(), repositoryAuth);

        assertEquals(createdEntity.getId(), readEntity.getId(), "O ID da entidade lida deveria corresponder ao ID da entidade criada.");
    }

    @Test
    @Transactional
    public void testReadDeleted() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.createSync(entity, repositoryAuth);
        repository.deleteSync(createdEntity.getId(), repositoryAuth);

        assertThrows(RepositoryException.class, () -> repository.read(createdEntity.getId(), repositoryAuth));
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.createSync(entity, repositoryAuth);

        E updatedEntity = repository.updateSync(createdEntity, repositoryAuth);

        assertNotNull(updatedEntity.getId(), "O ID da entidade atualizada não deve ser nulo.");
        assertNotEquals(createdEntity.getUpdatedAt(), updatedEntity.getUpdatedAt(), "A data de atualização deveria ser alterada.");
    }

    @Test
    @Transactional
    public void testUpdateDeleted() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.createSync(entity, repositoryAuth);
        repository.deleteSync(createdEntity.getId(), repositoryAuth);

        Exception exception = assertThrows(RuntimeException.class, () -> repository.updateSync(createdEntity, repositoryAuth));
        assertNotNull(exception, "Deveria ser lançada uma exceção ao tentar atualizar uma entidade deletada.");
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.createSync(entity, repositoryAuth);
        repository.deleteSync(createdEntity.getId(), repositoryAuth);

        assertThrows(RepositoryException.class, () -> repository.read(createdEntity.getId(), repositoryAuth));
    }

    @Test
    @Transactional
    public void testFindAllAfterDelete() throws Exception {
        E entity = createEntity();
        E createdEntity = repository.createSync(entity, repositoryAuth);

        repository.deleteSync(createdEntity.getId(), repositoryAuth);
        Optional<List<E>> allEntities = repository.findAll(repositoryAuth);

        assertTrue(allEntities.isEmpty(), "Deveria retornar uma lista de vazia.");
    }

    @Test
    @Transactional
    public void testFindAll() throws Exception {
        E entity = createEntity();
        repository.createSync(entity, repositoryAuth);

        Optional<List<E>> allEntities = repository.findAll(repositoryAuth);

        assertTrue(allEntities.isPresent(), "A busca por todas as entidades não deveria retornar nulo.");
        assertFalse(allEntities.get().isEmpty(), "A lista de entidades não deve estar vazia.");
    }
}