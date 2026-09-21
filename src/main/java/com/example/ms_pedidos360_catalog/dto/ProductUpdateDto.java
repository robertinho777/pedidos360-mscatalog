package com.example.ms_pedidos360_catalog.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDto {

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    @NotNull(message = "Product price cannot be null")
    @DecimalMin(value = "0.0", message = "Product price must be greater than or equal to 0")
    private BigDecimal price;
}
