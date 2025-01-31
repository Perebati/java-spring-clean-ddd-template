package org.gfinnovation.dealsafe._shared.modules.application;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RepositoryAuth
 * @since 08/11/2024
 */

public record RepositoryAuth(@NotNull UUID userId, @NotNull UUID whitelabelId, @NotNull UUID requestId) {}