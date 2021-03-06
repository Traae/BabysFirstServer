/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package practice.project;
import com.google.gson.Gson;
import io.javalin.Javalin;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

/**
 * This is the server for the Diagram Composer Microservice.
 *
 * @author Traae
 * @version 0.1.0
 */
public class desktopClient {
    public static void quickformat(String s){
        s.toLowerCase();
        s.stripLeading();
        s.stripTrailing();
        char i = s.charAt(0);
        s = "" + i;

    }
    public static void main(String[] args) {
        Connection connection = Connection.instance();
        Scanner scan = new Scanner(System.in);
        do{
            System.out.println("Please enter the address:\n");
            String address = scan.nextLine();
            System.out.println("Please enter the port:\n");
            String port = scan.nextLine();
            connection.initialize(address, port);
        }while (!connection.test());




        boolean continueApp = true;
        System.out.println("Greetings, This is the Baby's First Server calculator app\n");
        String option;
        while (continueApp){
            option = "0";
            System.out.println("What would you like to do?\n" +
                    "[enter the symbol on left for the operation on right]\n" +
                    "e - Exist\n" +
                    "1 - Sum - All inputs will be added.\n" +
                    "2 - Difference - All inputs will be subtracted (starting from the first).\n" +
                    "3 - Multiply - All inputs will be multiplied.\n" +
                    "4 - Divided - All inputs will be divided (starting from the first).\n" +
                    "5 - Randomize - Perform random operations between each input. \n" +
                    "6 - Polynomial Function - input 1 will be treated as x,\n" +
                    "    subsequent inputs will be treated as constants and powers in alternating fashion,\n" +
                    "    Example: [input 2]*[input 1]^[input 3] + [input 4]*[input 1]^[input 5] + ect...\n"
            );
            option = scan.nextLine();
            quickformat(option);

            String input;
            LinkedList<Double> values = new LinkedList<>();

            if (option.matches("[1-6]")){
                System.out.println("\nNow please input a number and press enter.\n" +
                        "When you are done entering numbers, enter any letter.\n");

                while (true){
                    input = scan.nextLine();
                    quickformat(input);
                    if (input.matches("[a-zA-Z]")){
                        break;
                    }else{
                        values.add(Double.valueOf(input));
                    }
                }
                System.out.println("Your entered values are: \n" + values.toString());
            }

            CalculatorInputs ci;

            if (values.size() > 0){
                double[] v = new double[values.size()];

                for (int i=0; i< values.size(); i++){
                    v[i] = values.get(i);
                }


                ci = new CalculatorInputs(v);
                System.out.println("CI IS" + ci.toString());
            }
            else{
                ci = new CalculatorInputs();
            }

            switch (option){
                case "0":
                    System.out.println("\nUnable to read input.\n");
                    break;
                case "e":
                    System.out.println("\nThank you come again.\n");
                    continueApp = false;
                    break;
                case "1":

                    CalculatorOutputs out = connection.sum(ci);
                    System.out.println("\nYour summation is:\n" + out.getValue());
                    break;
                case "2":
                    System.out.println("\nYour Difference is:\n" + connection.difference(ci).getValue());
                    break;
                case "3":
                    System.out.println("\nYour multiplication is:\n" + connection.multiply(ci).getValue());
                    break;
                case "4":
                    System.out.println("\nYour Division is:\n" + connection.divide(ci).getValue());
                    break;
                case "5":
                    System.out.println("\nYour Randomized output is:\n" + connection.random(ci).getValue());
                    break;
                case "6":
                    System.out.println("\nYour F(x) is:\n" + connection.random(ci).getValue());
                    break;
            }

        }
    }
}
