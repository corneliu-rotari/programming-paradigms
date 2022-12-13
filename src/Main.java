import app.App;
import com.fasterxml.jackson.databind.ObjectMapper;
import components.user.User;
import io.output.Output;
import io.input.Input;
import io.output.error.Error;

import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() {
    }

    /**
     * Entry point in the system
     * @param args - command line arguments
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input input;
        Output output = Output.getInstance(args[1]);

        try {
            input = objectMapper.readValue(new File(args[0]), Input.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (User user:
                input.getUsers()) {
            System.out.println(user.getCredentials().getName());
        }

        App application = App.getInstance(input);

        output.writeToFile();
    }
}
