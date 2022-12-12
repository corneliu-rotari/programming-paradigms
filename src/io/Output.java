package io;

import java.io.File;
import java.io.IOException;

public final class Output {
    File outFile;
    public Output() {
        this.outFile = new File("results.out");

        try {
            this.outFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
