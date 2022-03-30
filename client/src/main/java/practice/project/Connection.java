package practice.project;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

/**
 * Singleton Helper class for connecting to the microservice backend
 *
 * @author Isaac D. Griffith
 * @version 1.0.0
 */
public class Connection {
    private static final String httpThing = "http://%s:%s";
    private static final String STATUS_CALL = httpThing + CalculatorApi.root.path();
    private static final String OUTPUT_CALL = httpThing + CalculatorApi.getOutput.path();
    private static final String RANDOMIZE_CALL = httpThing + CalculatorApi.postForRandom.path();
    private static final String SUM_CALL = httpThing + CalculatorApi.postForSum.path();
    private static final String DIFFERENCE_CALL = httpThing + CalculatorApi.postForDifference.path();
    private static final String MULTIPLY_CALL = httpThing + CalculatorApi.postForMultiply.path();
    private static final String DIVIDE_CALL = httpThing + CalculatorApi.postForMultiply.path();
    private static final String POLYNOMIAL_CALL = httpThing + CalculatorApi.postForPolynomial.path();


    String address;
    String port;
    boolean initialized = false;
    HttpClient client;

    /**
     * Private default constructor
     */
    private Connection() {
    }
    /**
     * Singleton helper class
     */
    private static class ConnectionHelper {
        private static final Connection INSTANCE = new Connection();
    }
    /**
     * Singleton instance method
     *
     * @return The single instance of this class
     */
    public static Connection instance() {
        return ConnectionHelper.INSTANCE;
    }

    /**
     * Initializes the singleton with the proper address and port to connect to the microservice backend
     *
     * @param address Address of the service
     * @param port    Port for the service
     */
    public void initialize(String address, String port) {
        this.address = address;
        this.port = port;
        initialized = true;

        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }
    /**
     * Disconnects by setting the address, port, and client to null
     */
    public void disconnect() {
        address = null;
        port = null;
        client = null;
    }

    private double getLastOutput () throws IOException, InterruptedException {
        HttpRequest request = createGet(OUTPUT_CALL);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        double output = Double.valueOf(response.body());
        return output;
    }

    public CalculatorOutputs random(CalculatorInputs input) {
        try{
            return sendInputs(input, RANDOMIZE_CALL);
        }catch(Exception e){
            return new CalculatorOutputs(0);
        }
    }
    public CalculatorOutputs sum(CalculatorInputs input) {
        try{
            return sendInputs(input, SUM_CALL);
        }catch(Exception e){
            return new CalculatorOutputs(0);
        }
    }
    public CalculatorOutputs difference(CalculatorInputs input) {
        try{
            return sendInputs(input, DIFFERENCE_CALL);
        }catch(Exception e){
            return new CalculatorOutputs(0);
        }
    }
    public CalculatorOutputs multiply(CalculatorInputs input) {
        try{
            return sendInputs(input, MULTIPLY_CALL);
        }catch(Exception e){
            return new CalculatorOutputs(0);
        }
    }
    public CalculatorOutputs divide(CalculatorInputs input) {
        try{
            return sendInputs(input, DIVIDE_CALL);
        }catch(Exception e){
            return new CalculatorOutputs(0);
        }
    }
    public CalculatorOutputs polynomial(CalculatorInputs input) {
        try{
            return sendInputs(input, POLYNOMIAL_CALL);
        }catch(Exception e){
            return new CalculatorOutputs(0);
        }
    }

    public CalculatorOutputs sendInputs(CalculatorInputs input, String call) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(input);
        HttpRequest request = createPost(call, json);
        return getOutput(request);
    }

    /**
     * Sends a GET request to obtain the current state of the game using the provided HttpRequest object
     *
     * @param request the request object for the GET call
     * @return The current game state
     * @throws IOException          if there was an error connecting to the service via the network
     * @throws InterruptedException if the request timed out
     */
    private CalculatorOutputs getOutput(HttpRequest request) throws IOException, InterruptedException {
        Gson gson = new Gson();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        CalculatorOutputs output = gson.fromJson(response.body(), CalculatorOutputs.class);
        return output;
    }




    /**
     * Constructs a new HttpRequest object using the POST method, for the provided format string of the api call, and the provided json data to be sent
     *
     * @param apiCall Format string for the api call
     * @param json    json data to be sent
     * @return A HttpRequest object ready to be used with the service
     */
    private HttpRequest createPost(String apiCall, String json) {
        return HttpRequest.newBuilder()
                .uri(URI.create(String.format(apiCall, address, port)))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    /**
     * Constructs a new HttpRequest object using the GET method, for the provided format string of the api call
     *
     * @param apiCall Format string for the api call
     * @return The newly constructed HttpRequest object ready to be used with the service
     */
    private HttpRequest createGet(String apiCall) {
        return HttpRequest.newBuilder()
                .uri(URI.create(String.format(apiCall, address, port)))
                .timeout(Duration.ofSeconds(30))
                .GET()
                .build();
    }



    /**
     * Method to test whether the server is up and running and we have the correct address and port to connect to it.
     *
     * @return True if the client can connect to the service, false otherwise
     */
    public boolean test() {
        HttpRequest request = createGet(STATUS_CALL);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().equals("OK");
        } catch (IOException | InterruptedException ex) {
            return false;
        }
    }
}
