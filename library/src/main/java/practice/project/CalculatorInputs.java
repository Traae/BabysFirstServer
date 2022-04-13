package practice.project;

import java.io.Serializable;
import java.util.Arrays;

public class CalculatorInputs implements Serializable {
    public int size;
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
        values = new double[size];
        for(int i=0; i < size; i++){
            values[i] = inputs[i];
        }
    }

    public double[] getValues() {
        return values;
    }
    public double getValue(int index){
        return values[index];
    }
    @Override
    public String toString() {
        return "CalculatorInputs{" +
                "size=" + size +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
