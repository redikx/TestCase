package testcase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestCase {

    Logger logger = LoggerFactory.getLogger(TestCase.class);

    private String filePath;

    TestCase(String filePath) {
        this.filePath = filePath;
    }


    public String getFilePath() {
        return filePath;
    }

    public String[] readFile() {
        List<String> lines = new ArrayList<String>();
        String line;
        try {
            //*             ClassLoader classLoader = getClass().getClassLoader();
            //            File file = new File(classLoader.getResource(filePath).getFile());
            //            FileReader fileReader = new FileReader(file);

            File file = new File(getFilePath());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return lines.toArray(new String[lines.size()]);
    }



    public String getLine(int line) {
        String[] listing = this.readFile();
        return listing[line];
    }


    public int getSize() {
        return readFile().length;
    }

}
