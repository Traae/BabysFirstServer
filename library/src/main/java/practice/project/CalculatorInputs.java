package practice.project;

import java.io.Serializable;

public class CalculatorInputs implements Serializable {
    private int size;
    private double[] values;

    public CalculatorInputs(){
        size = 10;
        values = new double[size];
        for(int i=0; i < size; i++){
            values[i] = i;
        }
    }
    public CalculatorInputs(double[] inputs){
        size = inputs.length;
        values = inputs.clone();
    }

    public double[] getValues() {
        return values;
    }
    public double getValue(int index){
        return values[index];
    }
}
