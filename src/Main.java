import app.App;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.input.Input;
import io.input.action.Action;
import io.output.Output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class Main {

    private Main() {
    }

    private static void copyToOutput(String path) throws IOException {
        FileInputStream in = new FileInputStream("results.out");
        FileOutputStream out = new FileOutputStream(path.replace("\\in","\\out"));

        try {
            int n;
            while ((n = in.read()) != -1) {
                out.write(n);
            }
        }
        finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        in = null;
        out = null;
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
        copyToOutput(args[0]);
    }
}
