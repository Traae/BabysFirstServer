package practice.project;

/**
 * General Api Paths
 *
 * An Enumeration of Api Paths
 * Corresponding to the command that they call from the server.
 *
 * Use: "GeneralApi.[enum].path()" in place of the string you need.
 *
 * @author Traae
 * @version 0.1.0
 */
public enum CalculatorApi {
    root("/"),
    getInfo("/api/info"),
    getStatus("/api/get/status"),
    getError("/api/get/error"),
    getOutput("/api/get/output"),
    postForRandom("/api/post/random"),
    postForSum("/api/post/sum"),
    postForDifference("/api/post/difference"),
    postForMultiply("/api/post/multiply"),
    postForDivide("/api/post/divide"),
    postForPolynomial("/api/post/polynomial");


    private final String address;
    private CalculatorApi(String s) {
        this.address = s;
    }
    public String path(){
        return address;
    };
}
