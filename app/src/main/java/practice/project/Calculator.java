package practice.project;

import java.util.Random;

public class Calculator {
    private static Calculator instance;
    private double output;

    private Calculator(){}
    public static Calculator getInstance(){
        if (instance == null){
            instance = new Calculator();
        }
        return instance;
    }

    public double getOutput() {return output;}
    public CalculatorOutputs randomNumber(CalculatorInputs inputs){
        output = inputs.getValue(0);
        int size = inputs.getValues().length;
        Random r =  new Random();
        for (int i=1; i<size; i++){
            switch (r.nextInt(5)){
                case 0:
                    output = output + inputs.getValue(i);
                    break;
                case 1:
                    output = output - inputs.getValue(i);
                    break;
                case 2:
                    output = output * inputs.getValue(i);
                    break;
                case 3:
                    output = output / inputs.getValue(i);
                    break;
                case 4:
                    output = output % inputs.getValue(i);
                    break;
                case 5:
                    output = Math.pow(output, inputs.getValue(i));
                    break;

            }
        }
        return new CalculatorOutputs(output);
    }
    public CalculatorOutputs sum(CalculatorInputs inputs){
        output = 0;
        int size = inputs.getValues().length;
        for (int i=0; i<size; i++){
            output += inputs.getValue(i);
        }
        return new CalculatorOutputs(output);
    }
    public CalculatorOutputs minus(CalculatorInputs inputs){
        output = inputs.getValue(0);
        int size = inputs.getValues().length;
        for (int i=1; i<size; i++){
            output -= inputs.getValue(i);
        }
        return new CalculatorOutputs(output);
    }
    public CalculatorOutputs multiply(CalculatorInputs inputs){
        output = inputs.getValue(0);
        int size = inputs.getValues().length;
        for (int i=1; i<size; i++){
            output *= inputs.getValue(i);
        }
        return new CalculatorOutputs(output);
    }
    public CalculatorOutputs divide(CalculatorInputs inputs){
        output = inputs.getValue(0);
        int size = inputs.getValues().length;
        for (int i=1; i<size; i++){
            output /= inputs.getValue(i);
        }
        return new CalculatorOutputs(output);
    }
    public CalculatorOutputs polynomial(CalculatorInputs inputs){
        output = 0;
        int size = inputs.getValues().length;
        int count = 1;
        double x = inputs.getValue(0);

        while (count < size){
            double constant = inputs.getValue(count);
            double degree = 0;
            if (count+1 < size){
                count++;
                degree = inputs.getValue(count);
                output += constant * Math.pow(x, degree);
            }
            else {output += constant;}
            count++;
        }

        return new CalculatorOutputs(output);
    }
}
