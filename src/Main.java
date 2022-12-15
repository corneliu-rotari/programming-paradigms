import app.App;
import io.input.action.Action;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.input.Input;
import io.output.Output;

import java.io.File;
import java.io.IOException;

public final class Main {

    private Main() {
    }

    /**
     * Entry point in the system
     * @param args - paths to input and output file
     */
    public static void main(final String[] args) throws IOException {
        /* Creates Input and Output */
        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.readValue(new File(args[0]), Input.class);
        Output.createInstance(args[1]);

        /* Application creation and use */
        App application = App.getInstance(input);

        for (Action action : input.getActions()) {
            application.getCurrentPage().takeAction(action);
        }
        application.end();
    }
}
