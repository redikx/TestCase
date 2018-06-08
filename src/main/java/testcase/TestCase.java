package testcase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCase implements Iterator<String> {

    Logger logger = LoggerFactory.getLogger(TestCase.class);

    private String filePath;

    public TestCase(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String[] readFile() throws IOException {
        List<String> lines = new ArrayList<String>();
        String line;
        File file = new File(getFilePath());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        try {
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
	    logger.error(e.getMessage(), e);
        }
        finally {
        bufferedReader.close();
        }
        return lines.toArray(new String[lines.size()]);
    }



    public String getLine(int line) throws IOException {
        String[] listing = this.readFile();
        return listing[line];
    }


    public int getSize() throws IOException{
	    return readFile().length;
    }

    public boolean hasNext() {
	// TODO Auto-generated method stub
	return false;
    }

    public String next() {
	// TODO Auto-generated method stub
	return null;
    }

}
