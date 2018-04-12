package com.grow.myjava8.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Calculator {
    private Map<String, BiFunction> biOperators = new HashMap<>();
    private Map<String, Function> operators = new HashMap<>();

    public void addBiOperation(String name, BiFunction<Double, Double, Double> operator){
        biOperators.put(name, operator);
    }

    public void addOperation(String name, Function<Double, Double> operator){
        operators.put(name, operator);
    }

    public Double calculate(String operation, Double ... operands){
        if (operands.length == 1){
            final Function<Double, Double> operator = operators.get(operation);
            return process(operands[0], operator);
        }else if (operands.length == 2){
            final BiFunction<Double, Double, Double> operator = biOperators.get(operation);
            return process(operands[0], operands[1], operator);
        } else{
            throw new RuntimeException("Numbers on operands is incorect");
        }
    }

    public List<String> getAllOperators(){
        final List<String> result = new ArrayList<>();
        result.addAll(biOperators.keySet());
        result.addAll(operators.keySet());
        return result;
    }

    public boolean isBiOperator(String operator){
        return biOperators.keySet().contains(operator);
    }

    public boolean isOperator(String operator){
        return operators.keySet().contains(operator);
    }

    private double process(Double operandA, Double OperantB, BiFunction<Double, Double, Double> operator){
        if (operator == null){
            throw new RuntimeException("Operator not found");
        }
        return operator.apply(operandA, OperantB);
    }

    private double process(Double operand, Function<Double, Double> operator){
        if (operator == null){
            throw new RuntimeException("Operator not found");
        }
        return operator.apply(operand);
    }

}
