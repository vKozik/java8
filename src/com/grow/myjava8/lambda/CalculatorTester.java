package com.grow.myjava8.lambda;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class CalculatorTester {
    private Calculator calculator = new Calculator();
    private Function<Double, Double> factorial = n -> n > 1d ? this.factorial.apply(n - 1) * n : 1d;

    CalculatorTester(){
        initOperatuions();
    }

    public void doTest(int operationCount){
        List<String> allOperators = calculator.getAllOperators();

        Stream.generate(Math::random)
                .limit(operationCount)
                .map(n -> n * allOperators.size() - 1)
                .map(Math::round)
                .map(Math::abs)
                .map(Long::intValue)
                .map(allOperators::get)
                .forEach(this::randomCalculstor);
    }

    private void initOperatuions(){
        calculator.addBiOperation("+", (a, b) -> a + b);
        calculator.addBiOperation("-", (a, b) -> a - b);
        calculator.addBiOperation("/", (a, b) -> a / b);
        calculator.addBiOperation("*", (a, b) -> a * b);

        calculator.addOperation("abs", Math::abs);
        calculator.addOperation("sin", Math::sin);
        calculator.addOperation("cos", Math::cos);
        calculator.addOperation("!", factorial);
    }

    private void randomCalculstor(String operator){
        final Double[] operands;
        final String line;
        if (calculator.isOperator(operator)){
            operands = new Double[1];
            operands[0] = !operator.equals("!") ? randomDouble() : randomRounDouble();
            line = String.format("%s(%.2f) = ",operator, operands[0]);
        }else{
            operands = new Double[2];
            operands[0] = randomDouble();
            operands[1] = randomDouble();
            line = String.format("%.2f %s %.2f = ",operands[0], operator, operands[1]);
        }

        System.out.println(line + calculator.calculate(operator, operands));
    }

    private static Double randomDouble(){
        return Math.round(Math.random() * 100d) / 10d;
    }

    private static Double randomRounDouble(){
        return (double) Math.round(Math.random() * 10);
    }

}
