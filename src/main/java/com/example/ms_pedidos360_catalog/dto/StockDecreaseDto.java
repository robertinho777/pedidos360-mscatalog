package com.example.ms_pedidos360_catalog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDecreaseDto {

    @NotNull(message = "Quantity to decrease cannot be null")
    @Min(value = 1, message = "Quantity to decrease must be greater than or equal to 1")
    private Integer quantity;
}
