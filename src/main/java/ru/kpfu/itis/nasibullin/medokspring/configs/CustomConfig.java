package ru.kpfu.itis.nasibullin.medokspring.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kpfu.itis.nasibullin.medokspring.converters.StringToEditConverter;

@Configuration
public class CustomConfig implements WebMvcConfigurer {

    @Autowired
    private StringToEditConverter stringToEditConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToEditConverter);
    }

}
