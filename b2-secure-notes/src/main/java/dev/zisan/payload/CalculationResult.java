package dev.zisan.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculationResult {
    private String operation;
    private double operand1;
    private double operand2;
    private double result;
}
