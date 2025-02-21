package org.aleks;

public class CLParser {
    private final String[] input;
    private Function function;
    private String argument;
    private String additionalArgument;

    public CLParser(String[] input){
        this.input = input;
        parseInput();
    }

    private void parseInput() {
        parseFunction();
        parseArgument();
        parseAdditionalArgument();
    }

    private void parseFunction() {
        String functionToken = input[0].toUpperCase();
        try {
            function = Function.valueOf(functionToken);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid function");
        }
    }

    private void parseArgument() {
        if (input.length > 1){
            argument = input[1];
        } else {
            argument = null;
        }
    }

    private void parseAdditionalArgument() {
        if (input.length > 2) {
            additionalArgument = input[2];
        }
    }

    public Function getFunction(){
        return function;
    }

    public String getArgument(){
        return argument;
    }

    public String getAdditionalArgument(){
        return additionalArgument;
    }
}
