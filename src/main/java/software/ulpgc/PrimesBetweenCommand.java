package software.ulpgc;
import java.util.List;
import java.util.stream.IntStream;
import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.*;

public class PrimesBetweenCommand implements Command {
    @Override
    public Output execute(Input input) {
        try {
            return outputOf(primesBetween(input.get("lowerBound"), input.get("upperBound")));
        } catch (Exception e) {
            return outputException();
        }
    }

    private Output outputException() {
        return new Output() {
            @Override
            public String result() {
                return null;
            }

            @Override
            public int response() {
                return 403;
            }
        };
    }

    private Output outputOf(String result) {
        return new Output() {
            @Override
            public String result() {
                return result;
            }

            @Override
            public int response() {
                return 200;
            }
        };
    }

    private String primesBetween(String lowerBound, String upperBound) {
        return format(
                IntStream.rangeClosed(parseInt(lowerBound), parseInt(upperBound)).
                filter(this::isPrime).boxed().
                        collect(toList())
        );
    }

    private boolean isPrime(int number) {
        for (int i = 2; i < Math.sqrt(number); i++) {
            if(number%i == 0) return false;
        }
        return true;
    }

    private String format(List<Integer> primes) {
        return primes.stream().
                map(String::valueOf).
                collect(joining(", "));
    }
}
