package com.acquisio.basic.java.question07;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {

    @Test
    public void testOperatorAdd() {
        assertEquals(3, Calculator.applyOperation(1, 2, '+').intValue());
    }

    @Test
    public void testOperatorSubstract() {
        assertEquals(1, Calculator.applyOperation(2, 1, '-').intValue());

    }

    @Test
    public void testOperatorMultiply() {
    	assertEquals(2, Calculator.applyOperation(2, 1, '*').intValue());
    }

    @Test
    public void testOperatorDivide() {
    	assertEquals(2, Calculator.applyOperation(2, 1, '/').intValue());
    }
    
    @Test
    public void testOperatorDivideReturnNull() {
    	assertEquals(null, Calculator.applyOperation(2, 0, '/'));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testThrowException() {
    	Calculator.applyOperation(1, 2, '#');
    }
}
