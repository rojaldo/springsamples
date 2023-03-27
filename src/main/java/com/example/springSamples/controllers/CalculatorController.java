package com.example.springSamples.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springSamples.services.CalculatorService;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;


    @GetMapping("/calculator")
    public String greeting(
            @RequestParam(name = "num1", required = false, defaultValue = "0") String op1,
            @RequestParam(name = "num2", required = false, defaultValue = "0") String op2,
            @RequestParam(name = "op", required = false, defaultValue = "") String operator,
            Model myView) {
        // cast int op1
        int num1 = Integer.parseInt(op1);
        // cast int op2
        int num2 = Integer.parseInt(op2);
        // switch case for operator
        switch (operator) {
            case "+":
                myView.addAttribute("result", num1 + num2);
                break;
            case "-":
                myView.addAttribute("result", num1 - num2);
                break;
            case "*":
                myView.addAttribute("result", num1 * num2);
                break;
            case "/":
                myView.addAttribute("result", num1 / num2);
                break;
            default:
                myView.addAttribute("result", "ERROR");
                break;
        }
        myView.addAttribute("n1", op1);
        myView.addAttribute("n2", op2);
        myView.addAttribute("op", operator);
        return "calculator";
    }

    @GetMapping("/evaluate")
    public String evaluate(
            @RequestParam(name = "exp", required = false, defaultValue = "12*3=") String expression,
            Model myView) {
        int n1 = 0;
        int n2 = 0;
        String op = "+";
        int result = 0;
        if(this.calculatorService.process(expression)){
            n1 = this.calculatorService.getN1();
            n2 = this.calculatorService.getN2();
            op = this.calculatorService.getOp();
            result = this.calculatorService.getResult();
            this.calculatorService.clearState();
        }else {
            n1 = 0;
            n2 = 0;
            op = "ERROR: ";
            result = 0;  
            this.calculatorService.clearState();
        }
        myView.addAttribute("n1", n1);
        myView.addAttribute("n2", n2);
        myView.addAttribute("op", op);
        myView.addAttribute("result", result);
        return "calculator";
    }

}
