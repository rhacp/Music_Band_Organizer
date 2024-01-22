package com.anghel.music_band_organizer.utils.enum_converters;

import com.anghel.music_band_organizer.utils.enums.Availability;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class AvailabilityEnumConverter implements AttributeConverter<Availability, String> {

    @Override
    public String convertToDatabaseColumn(Availability availability) {
        if (availability == null) {
            return null;
        }

        return availability.getAvailabilityLabel();
    }

    @Override
    public Availability convertToEntityAttribute(String availabilityLabel) {
        if (availabilityLabel == null) {
            return null;
        }

        return Stream.of(Availability.values())
                .filter(availability -> availability.getAvailabilityLabel().equals((availabilityLabel)))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
