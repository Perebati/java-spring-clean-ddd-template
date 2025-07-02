package com.git.spring_boot_ddd_template._shared.infrastructure;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @since 08/11/2024
 */
public record RepositoryAuth(@NotNull UUID userId, @NotNull UUID whitelabelId, @NotNull UUID requestId) {
}