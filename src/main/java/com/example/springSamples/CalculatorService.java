package com.example.springSamples;

import org.springframework.stereotype.Service;

enum State {
    INIT, FIRSTFIGURE, SECONDFIGURE, RESULT
}

@Service
public class CalculatorService {
    private State currentState = State.INIT;
    private int n1 = 0;
    private int n2 = 0;
    private String op = "";
    private int result = 0;

    CalculatorService() {
    }
    
    public int getN1(){
        return this.n1;
    }

    public int getN2(){
        return this.n2;
    }

    public String getOp(){
        return this.op;
    }

    public int getResult(){
        return this.result;
    }

    public boolean process(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i))) {
                int digit = Character.getNumericValue(expression.charAt(i));
                this.handleNumber(digit);
            } else {
                this.handleSymbol(expression.charAt(i));
            }
        }

        return (this.currentState == State.RESULT);

    }

    private void handleNumber(int digit) {
        switch (this.currentState) {
            case INIT:
                this.n1 = digit;
                this.currentState = State.FIRSTFIGURE;
                break;
            case FIRSTFIGURE:
                this.n1 = this.n1 * 10 + digit;
                break;
            case SECONDFIGURE:
                this.n2 = this.n2 * 10 + digit;
                break;
            case RESULT:
                break;
            default:
                break;
        }
    }

    private void handleSymbol(char symbol) {
        // TODO
        switch (this.currentState) {
            case INIT:
                break;
            case FIRSTFIGURE:
                if (this.isOperator(symbol)) {
                    this.op = Character.toString(symbol);
                    this.currentState = State.SECONDFIGURE;
                }
                break;
            case SECONDFIGURE:
                if (symbol == '=') {
                    this.result = this.resolve();
                    this.currentState = State.RESULT;
                }
                break;
            case RESULT:
                break;
            default:
                break;
        }
    }

    public boolean isOperator(char symbol) {
        return symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/';
    }

    private int resolve() {
        switch (this.op) {
            case "+":
                return this.n1 + this.n2;
            case "-":
                return this.n1 - this.n2;
            case "*":
                return this.n1 * this.n2;
            case "/":
                return this.n1 / this.n2;
            default:
                return 0;
        }
    }

    public void clearState() {
        this.currentState = State.INIT;
        this.n1 = 0;
        this.n2 = 0;
        this.op = "";
        this.result = 0;
    }

}
