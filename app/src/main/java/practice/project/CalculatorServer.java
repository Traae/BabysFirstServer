/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package practice.project;
import com.google.gson.Gson;
import io.javalin.Javalin;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import java.util.Objects;

/**
 * This is the server for the Diagram Composer Microservice.
 *
 * @author Traae
 * @version 0.1.0
 */
public class CalculatorServer {
    private static final int MAX_THREADS = 20;
    private static final int MIN_THREADS = 2;
    private static final int TIMEOUT = 60000;


    public static void main(String[] args) {
        System.out.println("Fucked up on sever read in");

        String defaultMessage ="OK";
        String apiInfo = "This api is for the Calculator Microservice. \n" +
                "We can return to you an output of up to 10 inputs. \n" +
                "COMMAND LIST:";

        Calculator calculator = Calculator.getInstance();
        Gson gson = new Gson();


        QueuedThreadPool queuedThreadPool = new QueuedThreadPool(MAX_THREADS, MIN_THREADS,TIMEOUT);
        Javalin app = Javalin.create(config ->
                config.server(() ->
                        new Server(queuedThreadPool))).start(7000);
        app.routes(() -> {
            // DUE TO MERGE ISSUES my work was deleted AGAIN.
            // It's fine, but ignore what is present below. I'll work it out as I do the composer.


            // Basic info calls
            app.get(CalculatorApi.root.path(), ctx -> ctx.result(defaultMessage));
            app.get(CalculatorApi.getInfo.path(), ctx -> ctx.result(apiInfo));

            // Check status and Error message
            app.get(CalculatorApi.getError.path(), ctx -> ctx.result("0"/* CALL FileExporter . get current status code ()*/));
            app.get(CalculatorApi.getStatus.path(), ctx -> ctx.result("All good"/* CALL FileExporter . get error message ()*/));

            app.get(CalculatorApi.getOutput.path(), ctx -> {
                ctx.result(String.valueOf(calculator.getOutput()));
            });

            app.post(CalculatorApi.postForRandom.path(), ctx -> {
                if (Objects.equals(ctx.contentType(), "application/json")) {
                    CalculatorInputs inputs = ctx.bodyAsClass(CalculatorInputs.class);
                    CalculatorOutputs output = calculator.randomNumber(inputs);
                    ctx.json(output);
                }
            });
            app.post(CalculatorApi.postForSum.path(), ctx -> {
                if (Objects.equals(ctx.contentType(), "application/json")) {
                    CalculatorInputs inputs = gson.fromJson(ctx.body(), CalculatorInputs.class);
                    CalculatorOutputs output = calculator.sum(inputs);
                    ctx.json(output);
                }else {
                    System.out.println("Fucked up on sever read in");
                }
            });
            app.post(CalculatorApi.postForDifference.path(), ctx -> {
                if (Objects.equals(ctx.contentType(), "application/json")) {
                    CalculatorInputs inputs = ctx.bodyAsClass(CalculatorInputs.class);
                    CalculatorOutputs output = calculator.minus(inputs);
                    ctx.json(output);
                }
            });
            app.post(CalculatorApi.postForMultiply.path(), ctx -> {
                if (Objects.equals(ctx.contentType(), "application/json")) {
                    CalculatorInputs inputs = ctx.bodyAsClass(CalculatorInputs.class);
                    CalculatorOutputs output = calculator.multiply(inputs);
                    ctx.json(output);
                }
            });
            app.post(CalculatorApi.postForDivide.path(), ctx -> {
                if (Objects.equals(ctx.contentType(), "application/json")) {
                    CalculatorInputs inputs = ctx.bodyAsClass(CalculatorInputs.class);
                    CalculatorOutputs output = calculator.divide(inputs);
                    ctx.json(output);
                }
            });
            app.post(CalculatorApi.postForPolynomial.path(), ctx -> {
                if (Objects.equals(ctx.contentType(), "application/json")) {
                    CalculatorInputs inputs = ctx.bodyAsClass(CalculatorInputs.class);
                    CalculatorOutputs output = calculator.polynomial(inputs);
                    ctx.json(output);
                }
            });


        });

    }
}
