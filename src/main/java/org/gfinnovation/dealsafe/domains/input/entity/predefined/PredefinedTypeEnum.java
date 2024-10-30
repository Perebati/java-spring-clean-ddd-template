package org.gfinnovation.dealsafe.domains.input.entity.predefined;

import lombok.Getter;
import org.gfinnovation.dealsafe.domains.input.entity.predefined.models.PredefinedInputExampleEntity;

import java.lang.reflect.Field;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class PredefinedTypeEnum
 * @authorNote If the selected input for a root node is static, it means that
 * operations of the validation tree will go be made respecting the
 * fields in the predefined object.
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

    public void validateJsonPath(PredefinedTypeEnum predefinedTypeEnum, String jsonPath) throws NoSuchFieldException {
        // Remove o prefixo '/' ou '.' para facilitar a manipulação
        jsonPath = jsonPath.replaceFirst("^[/.]", "");

        // Divide o jsonPath em partes para percorrer cada campo
        String[] fields = jsonPath.split("[/.]");

        // Obtém a classe a partir do PredefinedTypeEnum
        Class<?> currentClass = predefinedTypeEnum.getClazz(); // Aqui você pode precisar de um getter para a classe

        // Verifica cada campo no jsonPath
        for (String fieldName : fields) {
            // Tenta obter o campo na classe atual
            Field field = getFieldOrThrow(currentClass, fieldName);
            currentClass = field.getType(); // Avança para o próximo nível de classe (se existir)
        }
    }
}