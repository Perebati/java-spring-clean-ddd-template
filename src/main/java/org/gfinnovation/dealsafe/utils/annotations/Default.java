package org.gfinnovation.dealsafe.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @annotation @Default
 * @authorNote This class only exists so silly MapStruct
 * can create standard mappers for each entity.
 * It requires a default constructor for each mapped class,
 * this annotation flags which is the default constructor.
 * @since 30/10/2024
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.CLASS)
public @interface Default {
}