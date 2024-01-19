package com.anghel.music_band_organizer.config;

import com.anghel.music_band_organizer.models.OpenAI;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }

    @Bean
    public OpenAI openAI() { return new OpenAI(); }
}
