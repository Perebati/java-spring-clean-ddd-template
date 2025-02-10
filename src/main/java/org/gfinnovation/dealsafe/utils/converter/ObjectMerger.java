package org.gfinnovation.dealsafe.utils.converter;

import java.lang.reflect.Field;

public class ObjectMerger {

    /**
     * Atualiza os campos de 'target' com os valores de 'source' para todos os campos
     * que possuam o mesmo nome. Porém, caso algum campo em 'source' seja null,
     * esse campo não será copiado para 'target'.
     *
     * @param source objeto de origem (valores a serem copiados)
     * @param target objeto de destino (que receberá os valores)
     * @param <A>    tipo do objeto de origem
     * @param <B>    tipo do objeto de destino
     */
    public static <A, B> void mergeObjects(A source, B target) {
        if (source == null || target == null) {
            return;
        }

        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();

        Field[] sourceFields = sourceClass.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            try {
                sourceField.setAccessible(true);
                String fieldName = sourceField.getName();

                Field targetField;
                try {
                    targetField = targetClass.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    continue;
                }

                targetField.setAccessible(true);

                Object value = sourceField.get(source);

                if (value != null && isAssignable(targetField.getType(), sourceField.getType())) {
                    targetField.set(target, value);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private static boolean isAssignable(Class<?> targetType, Class<?> sourceType) {
        return targetType.isAssignableFrom(sourceType);
    }
}
