package dev.zisan.controller;

import dev.zisan.payload.CalculationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    @GetMapping("/add")
    public ResponseEntity<CalculationResult> add(@RequestParam double a, @RequestParam double b) {
        CalculationResult result = CalculationResult.builder()
                .operation("add")
                .operand1(a)
                .operand2(b)
                .result(a + b)
                .build();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/subtract")
    public ResponseEntity<CalculationResult> subtract(@RequestParam double a, @RequestParam double b) {
        CalculationResult result = CalculationResult.builder()
                .operation("subtract")
                .operand1(a)
                .operand2(b)
                .result(a - b)
                .build();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/multiply")
    public ResponseEntity<CalculationResult> multiply(@RequestParam double a, @RequestParam double b) {
        CalculationResult result = CalculationResult.builder()
                .operation("add")
                .operand1(a)
                .operand2(b)
                .result(a * b)
                .build();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/divide")
    public ResponseEntity<?> divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Cannot divide by zero");
            return ResponseEntity.badRequest().body(error);
        }

        CalculationResult result = CalculationResult.builder()
                .operation("add")
                .operand1(a)
                .operand2(b)
                .result(a / b)
                .build();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/power")
    public ResponseEntity<CalculationResult> power(@RequestParam double a, @RequestParam double b) {
        CalculationResult result = CalculationResult.builder()
                .operation("add")
                .operand1(a)
                .operand2(b)
                .result(Math.pow(a, b))
                .build();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sqrt")
    public ResponseEntity<Map<String, Object>> sqrt(@RequestParam double number) {
        if (number < 0) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Cannot calculate square root of negative number");
            return ResponseEntity.badRequest().body(error);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("operation", "square root");
        result.put("number", number);
        result.put("result", Math.sqrt(number));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculate(@RequestBody CalculationRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("operation", request.getOperation());
        result.put("operand1", request.getA());
        result.put("operand2", request.getB());

        double calculationResult = switch (request.getOperation().toLowerCase()) {
            case "add" -> request.getA() + request.getB();
            case "subtract" -> request.getA() - request.getB();
            case "multiply" -> request.getA() * request.getB();
            case "divide" -> {
                if (request.getB() == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
                yield request.getA() / request.getB();
            }
            default -> throw new IllegalArgumentException("Invalid operation: " + request.getOperation());
        };

        result.put("result", calculationResult);
        return ResponseEntity.ok(result);
    }

    public static class CalculationRequest {
        private String operation;
        private double a;
        private double b;

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public double getA() {
            return a;
        }

        public void setA(double a) {
            this.a = a;
        }

        public double getB() {
            return b;
        }

        public void setB(double b) {
            this.b = b;
        }
    }
}
