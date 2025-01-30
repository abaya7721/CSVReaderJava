package learn.library.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private Path path;

    public FileManager(String filePath) {
        this.path = Paths.get(filePath);
    }

    public List<String> readFile() {
        List<String> fileRecords = new ArrayList<>();
        try {
            fileRecords.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fileRecords;
    }

}
