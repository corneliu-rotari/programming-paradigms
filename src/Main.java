import app.App;
import app.action.Action;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.output.Output;
import io.input.Input;

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
            in.close();
            out.close();
        }
        in = null;
        out = null;
    }

    /**
     * Entry point in the system
     * @param args - paths to input and output file
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.readValue(new File(args[0]), Input.class);
        Output output = Output.getInstance(args[1]);


        App application = App.getInstance(input);

        for (Action action : input.getActions()) {
//            System.out.println(application.getCurrentPage().getClass());
            application.getCurrentPage().takeAction(action);
        }

        application.end();
        copyToOutput(args[0]);
    }
}
