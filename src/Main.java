import app.App;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.input.Input;
import io.input.request.Request;
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

        for (Request request : input.getActions()) {
            application.receiveRequest(request);
            application.applyStrategy();
        }

        application.end();
    }
}
