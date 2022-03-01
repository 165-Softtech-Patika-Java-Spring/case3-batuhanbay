package com.softtechbootcamp.case3.app.prd.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PrdProductUpdateDto {
    @NotNull(message = "Product price parameter can not be null")
    @DecimalMin(value = "0.0", message = "Price must be greater than 0!")
    private BigDecimal price;
}
