package software.ulpgc;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<String, Command> commands = new HashMap<>();
    private final Request request;
    private final Response response;

    public CommandExecutor(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    public static CommandExecutor with(Request request, Response response) {
        return new CommandExecutor(request, response);
    }

    public static void put(String name, Command command) {
        commands.put(name, command);
    }

    public String execute(String name) {
        Command.Output output = commands.get(name).execute(request::queryParams);
        response.status(output.response());
        return output.result();
    }
}
