package org.gfinnovation.dealsafe.authentication;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RepositoryAuth
 * @since 08/11/2024
 */

public record RepositoryAuth(@NotNull UUID user_id, @NotNull UUID company_id, @NotNull UUID request_id){}
