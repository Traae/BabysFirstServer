package practice.project;

import java.io.Serializable;

public class CalculatorOutputs implements Serializable {
    double value;

    public CalculatorOutputs(double output){
        value = output;
    }
    public double getValue(){
        return value;
    }
}
