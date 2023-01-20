package com.teste.devDojo.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePutRequestBody {
    private Long id;
    @NotEmpty(message = "The anime must have a name")
    private String name;
}
