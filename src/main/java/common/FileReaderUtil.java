package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileReaderUtil {

    public List<String> readFileToString(String path) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        assert inputStream != null;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        List<String> fileLines = bufferedReader.lines().toList();
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        return fileLines;
    }

}
