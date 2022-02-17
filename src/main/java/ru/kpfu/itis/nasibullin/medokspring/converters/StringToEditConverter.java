package ru.kpfu.itis.nasibullin.medokspring.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.nasibullin.medokspring.dto.user.EditAction;
import ru.kpfu.itis.nasibullin.medokspring.exceptions.IllegalEditAccountActionException;

@Component
public class StringToEditConverter implements Converter<String, EditAction> {
    @Override
    public EditAction convert(String s) {
        try {
            return EditAction.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalEditAccountActionException("Action " + s.toUpperCase() + " is not supported");
        }
    }
}

