import io.Output;
import io.Input;

import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() {
    }

    /**
     * Entry point in the system
     * @param args - command line arguments
     */
    public static void main(final String[] args) {
        Output output = new Output();
        Input input = new Input(args);

    }
}
