package com.faculdade.crud.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoDTO(
        @NotBlank String nome,
        @NotNull @Min(0) Integer idade,
        @NotBlank String curso
) {}
