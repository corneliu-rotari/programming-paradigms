package io.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.output.response.Response;

import java.io.File;
import java.io.IOException;

/**
 * Output handler write to a file specified in constructor
 * Synchronized Singleton
 */
public final class Output {
    private static Output outputInstance = null;
    private final ObjectMapper objectMapper;
    private final String path;
    private final ArrayNode jsonTree;

    /**
     * Creates an Output instance
     *
     * @param path - output file
     */
    public static synchronized void createInstance(final String path) {
        if (outputInstance == null) {
            outputInstance = new Output(path);
        }
    }


    public static synchronized Output getInstance() {
        return outputInstance;
    }

    private Output(final String path) {
        this.objectMapper = new ObjectMapper();
        this.path = path;
        this.jsonTree = this.objectMapper.createArrayNode();
    }

    /**
     * Adds a response to the output
     * @param response - the response to output
     */
    public void addToTree(final Response response) {
        jsonTree.add(this.objectMapper.valueToTree(response));
    }

    /**
     * Writes all the stored output to the file
     * @throws IOException - if the path is null
     */
    public void writeToFile() throws IOException {
        ObjectWriter objectWriter = this.objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(this.path), jsonTree);
    }

    /**
     * Deletes the output object
     */
    public static void destroy() {
        outputInstance = null;
    }
}
