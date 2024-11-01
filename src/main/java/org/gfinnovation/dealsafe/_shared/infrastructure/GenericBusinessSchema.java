package org.gfinnovation.dealsafe._shared.infrastructure;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * JPA class used for saving business information on business classes.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericBusinessSchema
 * @since 30/10/2024
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class GenericBusinessSchema extends org.gfinnovation.dealsafe._shared.infrastructure.GenericSchema {
    private UUID userId;
    private UUID companyId;
}