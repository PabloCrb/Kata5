package software.ulpgc;

import spark.Spark;

import static spark.Spark.port;

public class Main {
    public static void main(String[] args) {
        CommandExecutor.put("primesBetween", new PrimesBetweenCommand());
        port(1111);
        Spark.get("/primesBetween", (request, response) -> CommandExecutor.with(request, response).execute("primesBetween"));
    }
}
