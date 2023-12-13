package software.ulpgc;

public interface Command {
    Output execute(Input input);

    interface Output {
        String result();
        int response();
    }

    interface Input {
        String get(String key);
    }
}
