package org.gfinnovation.dealsafe._shared.infrastructure;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RepositoryAuth
 * @since 08/11/2024
 */
public record RepositoryAuth(@NotNull UUID userId, @NotNull UUID whitelabelId, @NotNull UUID requestId) {
}