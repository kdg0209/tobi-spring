package com.spring.tobi;

import com.spring.tobi.calculator.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class CalculatorSumTest {

    @Test
    public void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        int sum = calculator.calculatorSum("/Users/gimdong-gyun/IdeaProjects/study/tobi-spring/src/main/resources/numbers.txt");
        int multiply = calculator.calculatorMultiply("/Users/gimdong-gyun/IdeaProjects/study/tobi-spring/src/main/resources/numbers.txt");
        System.out.println(sum + ", " + multiply);
    }
}
