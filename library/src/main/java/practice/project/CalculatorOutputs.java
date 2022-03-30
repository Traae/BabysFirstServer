package practice.project;

import org.checkerframework.common.reflection.qual.GetMethod;

import java.io.Serializable;

public class CalculatorOutputs implements Serializable {


    private double value;

    public CalculatorOutputs(double output){
        value = output;
    }

    public double getValue() {
        return value;
    }
}
