package testcase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCase {

    Logger logger = LoggerFactory.getLogger(TestCase.class);
    File file;
    private String filePath;

    public TestCase(String filePath) {
        file = new File(filePath);
    }

    public String getFilePath() {
        return filePath;
    }

       public List<String> readFile() throws IOException {
        List<String> lines = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(file);
            
            while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
            }
            sc.close();
            }
        catch (IOException ioe) {
            logger.error("IOException error while scanning " + file.getPath());
        }
        return lines;
       }



    public String getLine(int line) throws IOException {
        List<String> listing = this.readFile();
        return listing.get(line);
    }


    public int getSize() throws IOException{
	    return readFile().size();
    }


}
