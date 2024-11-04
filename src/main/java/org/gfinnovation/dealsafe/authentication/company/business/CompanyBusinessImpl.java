package org.gfinnovation.dealsafe.authentication.company.business;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.gfinnovation.dealsafe.authentication.company.business.interfaces.CompanyBusiness;
import org.gfinnovation.dealsafe.authentication.company.entity.CompanyEntity;
import org.gfinnovation.dealsafe.authentication.company.entity.factory.CompanyFactory;
import org.gfinnovation.dealsafe.authentication.company.entity.repository.CompanyRepository;
import org.gfinnovation.dealsafe.authentication.user.business.interfaces.UserBusiness;
import org.gfinnovation.dealsafe.authentication.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class CompanyBusinessImpl
 * The way authentication works in DealSafe will change in the future,
 * so don't even bother trying to understand this.
 * @since 30/10/2024
 */

@Service
@RequiredArgsConstructor
class CompanyBusinessImpl implements CompanyBusiness {
    private final CompanyRepository companyRepository;
    private final CompanyFactory companyFactory;
    private final UserBusiness userBusiness;

    @Transactional
    @Override
    public CompanyEntity create(String name, Set<UUID> users_id) throws BadRequestException {
        CompanyEntity newCompany = companyFactory.createCompany(name, users_id);
        for (UUID user_id : users_id) {
            UserEntity userEntity = userBusiness.read(user_id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
            userEntity.setCompanyId(newCompany.getId());
            userBusiness.update(userEntity);
        }
        return this.companyRepository.create(newCompany);
    }

    @Override
    public Optional<CompanyEntity> read(UUID id) throws RuntimeException {
        return companyRepository.read(id);
    }

    @Override
    public Optional<List<CompanyEntity>> readAll() throws RuntimeException {
        return companyRepository.findAll();
    }

    @Override
    public Optional<List<CompanyEntity>> readAllByIds(List<UUID> ids) throws RuntimeException {
        return companyRepository.findAllByIds(ids);
    }

    @Override
    public CompanyEntity update(CompanyEntity entity) throws RuntimeException {
        return companyRepository.update(entity);
    }

    @Override
    public void delete(UUID id) throws RuntimeException {
        companyRepository.delete(id);
    }

    @Override
    public void check(UUID id) throws RuntimeException {
        companyRepository.check(id);
    }

    @Override
    public void checkAll(Set<UUID> ids) throws RuntimeException {
        companyRepository.checkAll(ids);
    }
}
