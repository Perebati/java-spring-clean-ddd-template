package org.gfinnovation.dealsafe._shared.infrastructure;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class GenericBusinessSchema
 * @authorNote JPA class used for saving business information on business classes.
 * @since 30/10/2024
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class GenericBusinessSchema extends org.gfinnovation.dealsafe._shared.infrastructure.GenericSchema {
    private UUID user_id;
    private UUID company_id;
}