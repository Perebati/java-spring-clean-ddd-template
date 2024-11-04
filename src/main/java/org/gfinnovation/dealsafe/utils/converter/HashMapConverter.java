package org.gfinnovation.dealsafe.utils.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.HashMap;

/**
 * Useful for converting Map to string and vice versa.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class HashMapConverter
 * @since 30/10/2024
 */

@Converter
public class HashMapConverter implements AttributeConverter<HashMap<String, Object>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(HashMap<String, Object> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter HashMap para String", e);
        }
    }

    @Override
    public HashMap<String, Object> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<HashMap<String, Object>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter String para HashMap", e);
        }
    }
}