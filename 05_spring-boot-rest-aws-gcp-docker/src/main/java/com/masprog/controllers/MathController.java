package com.masprog.controllers;

import com.masprog.request.NumberConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception{

        if(NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo)) throw new UnsupportedOperationException("Please set a numeric values");
        return NumberConverter.convertToDouble(numberOne) + NumberConverter.convertToDouble(numberTwo);
    }

    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable("numberOne") String numberOne,
                               @PathVariable("numberTwo") String numberTwo) throws Exception{

        if(NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo)) throw new UnsupportedOperationException("Please set a numeric values");

        if(!NumberConverter.isGreaterOrEqual(numberOne, numberTwo)) throw new UnsupportedOperationException("The first number must be Greater or equal than the second number");

        return NumberConverter.convertToDouble(numberOne) - NumberConverter.convertToDouble(numberTwo);

    }

    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable("numberOne") String numberOne,
                               @PathVariable("numberTwo") String numberTwo) throws Exception{

        if(NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo)) throw new UnsupportedOperationException("Please set a numeric values");

        return NumberConverter.convertToDouble(numberOne) * NumberConverter.convertToDouble(numberTwo);

    }

    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable("numberOne") String numberOne,
                               @PathVariable("numberTwo") String numberTwo) throws Exception{

        if(NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo)) throw new UnsupportedOperationException("Please set a numeric values");

        if(NumberConverter.convertToDouble(numberTwo) == 0) throw new UnsupportedOperationException("Division by zero is not allowed");

        return NumberConverter.convertToDouble(numberOne) / NumberConverter.convertToDouble(numberTwo);

    }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable("numberOne") String numberOne,
                               @PathVariable("numberTwo") String numberTwo) throws Exception{

        if(NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo)) throw new UnsupportedOperationException("Please set a numeric values");

        return (NumberConverter.convertToDouble(numberOne) + NumberConverter.convertToDouble(numberTwo)) / 2;

    }

    @RequestMapping("/squareRoot/{number}")
    public Double squareRoot(@PathVariable("number") String number) throws Exception {

        if(NumberConverter.isNumeric(number)) throw new UnsupportedOperationException("Please set a numeric value");

        double value = NumberConverter.convertToDouble(number);

        if (value < 0 ) throw new UnsupportedOperationException("Is not possible to calculate the square root of a negative number");

        return (Math.sqrt(NumberConverter.convertToDouble(number)));

    }







}
