package org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.enums;

import lombok.Getter;
import org.gfinnovation.dealsafe.modules.input.domain.valueobjects.predefined.PredefinedInputExampleEntity;

import java.lang.reflect.Field;

/**
 * If the selected input for a root node is static, it means that
 * operations of the validation tree will go be made respecting the
 * fields in the predefined object.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class PredefinedTypeEnum
 * @since 30/10/2024
 */

@Getter
public enum PredefinedTypeEnum {
    TESTE(PredefinedInputExampleEntity.class);

    private final Class<?> clazz;

    PredefinedTypeEnum(Class<?> clazz) {
        this.clazz = clazz;
    }

    private static Field getFieldOrThrow(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new NoSuchFieldException("Campo '" + fieldName + "' não encontrado na classe " + clazz.getSimpleName());
        }
    }

    /**
     * Verifies if the jsonPath is mapped inside the predefined class.
     *
     * @param predefinedTypeEnum Enum that pre-maps predefined input types.
     * @param jsonPath           Path to a variable.
     * @throws NoSuchFieldException Thrown when a path doesn't match the input.
     * @author Lucas Batista Pereira
     * @since 30/10/2024
     */

    public void validateJsonPath(PredefinedTypeEnum predefinedTypeEnum, String jsonPath) throws NoSuchFieldException {
        // Remove o prefixo '/' ou '.' para facilitar a manipulação e divide o jsonPath em partes para percorrer cada campo.
        jsonPath = jsonPath.replaceFirst("^[/.]", "");
        String[] fields = jsonPath.split("[/.]");

        // Obtém a classe a partir do PredefinedTypeEnum
        Class<?> currentClass = predefinedTypeEnum.getClazz();

        // Verifica cada campo no jsonPath
        for (String fieldName : fields) {
            Field field = getFieldOrThrow(currentClass, fieldName); // Tenta obter o campo na classe atual
            currentClass = field.getType(); // Avança para o próximo nível de classe (se existir)
        }
    }
}