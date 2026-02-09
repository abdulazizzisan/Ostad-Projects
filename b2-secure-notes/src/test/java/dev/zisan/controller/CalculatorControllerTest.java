package dev.zisan.controller;

import dev.zisan.payload.CalculationResult;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorControllerTest {

    CalculatorController controller = new CalculatorController();

    @Test
    void testAdd(){
        ResponseEntity<CalculationResult> resultResponseEntity = controller.add(1, 2);
        CalculationResult result = resultResponseEntity.getBody();

        assertNotNull(result);
        assertEquals( 3, result.getResult());
        assertEquals(1, result.getOperand1());
        assertEquals(2, result.getOperand2());
        assertEquals("add", result.getOperation());
    }

    @Test
    void testSubtract() {

        int a = 5;
        int b = 2;
        ResponseEntity<CalculationResult> resultResponseEntity = controller.subtract(a, b);
        CalculationResult result = resultResponseEntity.getBody();

        assertNotNull(result);
        assertEquals(a - b, result.getResult());
        assertEquals(a, result.getOperand1());
        assertEquals(b, result.getOperand2());
        assertEquals("subtract", result.getOperation());

        assertNotEquals(a + b, result.getResult());

//        assertThrows()
    }


}