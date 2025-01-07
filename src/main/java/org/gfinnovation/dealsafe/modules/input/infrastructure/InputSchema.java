package org.gfinnovation.dealsafe.modules.input.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gfinnovation.dealsafe._shared.modules.infrastructure.GenericBusinessSchema;
import org.gfinnovation.dealsafe._shared.utils.converter.HashMapConverter;

import java.util.HashMap;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InputSchema
 * @since 30/10/2024
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "dynamic_input")
public class InputSchema extends GenericBusinessSchema {

    @Column(name = "name", nullable = false)
    private String name;

    @Convert(converter = HashMapConverter.class)
    @Column(name = "fields", columnDefinition = "TEXT")
    private HashMap<String, Object> fields = new HashMap<>();
}