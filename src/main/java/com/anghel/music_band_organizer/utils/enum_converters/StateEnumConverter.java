package com.anghel.music_band_organizer.utils.enum_converters;

import com.anghel.music_band_organizer.utils.enums.State;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StateEnumConverter implements AttributeConverter<State, String> {

    @Override
    public String convertToDatabaseColumn(State state) {
        if (state == null) {
            return null;
        }

        return state.getStateLabel();
    }

    @Override
    public State convertToEntityAttribute(String stateLabel) {
        if (stateLabel == null) {
            return null;
        }

        return Stream.of(State.values())
                .filter(role -> role.getStateLabel().equals(stateLabel))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
