package learn.library.domain;

import learn.library.data.FileOpenReader;

import java.io.FileNotFoundException;
import java.util.List;

public class RecordService {
    private final FileOpenReader reader;
    private String category;

    public RecordService(String path) throws FileNotFoundException {
        reader = new FileOpenReader(path);
    }

    public List<String> getAllRecords(){
        return reader.readFile();
    }


}

