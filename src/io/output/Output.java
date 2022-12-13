package io.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.output.response.Response;

import java.io.File;
import java.io.IOException;

public final class Output {
    private static Output outputInstance = null;
    private ObjectMapper objectMapper;
    private String path;
    private ArrayNode jsonTree;


    public synchronized static Output getInstance(String path) {
        if (outputInstance == null) {
            outputInstance = new Output(path);
        }
        return getInstance();
    }

    public synchronized static Output getInstance() {
        return outputInstance;
    }

    private Output(String path) {
        this.objectMapper = new ObjectMapper();
        this.path = path;
        this.jsonTree = this.objectMapper.createArrayNode();
    }

    public void addToTree(Response response) {
        jsonTree.add(this.objectMapper.valueToTree(response));
    }
    public void writeToFile() throws IOException {
        ObjectWriter objectWriter = this.objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(this.path), jsonTree);
    }

    public static void destory() {
        outputInstance = null;
    }
}
