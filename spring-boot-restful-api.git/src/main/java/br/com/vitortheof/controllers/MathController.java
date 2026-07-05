package br.com.vitortheof.controllers;

import br.com.vitortheof.exception.UnsupportedMathOperationException;
import br.com.vitortheof.utils.MathUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping(value ="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        MathUtils.validateNumbers(numberOne, numberTwo);
        return MathUtils.toDouble(numberOne) + MathUtils.toDouble(numberTwo);
    }

    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double subtraction(@PathVariable("numberOne") String numberOne,
                              @PathVariable("numberTwo") String numberTwo) throws Exception {
       MathUtils.validateNumbers(numberOne, numberTwo);
       return MathUtils.toDouble(numberOne) - MathUtils.toDouble(numberTwo);
    }

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double multiplication(@PathVariable("numberOne") String numberOne,
                              @PathVariable("numberTwo") String numberTwo) throws Exception {
        MathUtils.validateNumbers(numberOne, numberTwo);
        return MathUtils.toDouble(numberOne) * MathUtils.toDouble(numberTwo);
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable String numberOne, @PathVariable String numberTwo) {
        MathUtils.validateNumbers(numberOne, numberTwo);
        double divisor = MathUtils.toDouble(numberTwo);
        if (divisor == 0) {
            throw new UnsupportedMathOperationException("Cannot divide by zero");
        }
        return MathUtils.toDouble(numberOne) / divisor;
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double mean(@PathVariable("numberOne") String numberOne,
                           @PathVariable("numberTwo") String numberTwo) throws Exception {
        MathUtils.validateNumbers(numberOne, numberTwo);
        return (MathUtils.toDouble(numberOne) + MathUtils.toDouble(numberTwo)) / 2.0;
    }

    @RequestMapping(value = "/squareRoot/{numberOne}", method=RequestMethod.GET)
    public Double squareRoot(@PathVariable("numberOne") String numberOne) throws Exception {
        MathUtils.validateNumbers(numberOne);
        return Math.sqrt(MathUtils.toDouble(numberOne));
    }

}
