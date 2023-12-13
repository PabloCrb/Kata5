package software.ulpgc;

import spark.Spark;

public class Main {
    public static void main(String[] args) {
        CommandExecutor.put("primes", new PrimesCommand());
        Spark.port(1111);
        Spark.get("/primes", (request, response) -> CommandExecutor.with(request, response).execute("primes"));
    }
}
