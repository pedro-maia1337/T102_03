package HelloWorld;

import static spark.Spark.*;

public class helloWorld {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World MMM");
    }
}