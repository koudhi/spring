package com.teste.devDojo.requests;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimesPostRequestBody {
    @NotEmpty(message = "The anime must have a name")
    private String name;
}
