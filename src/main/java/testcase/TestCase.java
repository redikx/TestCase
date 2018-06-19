package testcase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCase implements Iterable<String>{

    Logger logger = LoggerFactory.getLogger(TestCase.class);
    File file;
    private String filePath;

    private List<String> lines = null;
    
    public TestCase(String filePath) {
	//file = new File(filePath);
	ClassLoader classLoader = getClass().getClassLoader();
	file = new File(classLoader.getResource(filePath).getFile());
    }

    public String getFilePath() {
	return filePath;
    }

    public void readFile() throws IOException {
	lines = new ArrayList<String>();
	try {
	    Scanner sc = new Scanner(file);

	    while (sc.hasNextLine()) {
		lines.add(sc.nextLine());
	    }
	    sc.close();
	} catch (IOException ioe) {
	    logger.error("IOException error while scanning " + file.getPath());
	}
    }

    public String getLine(int line) throws IOException {
	if (lines==null) {
	    throw new IOException("File has not been read");
	}
	return lines.get(line);
    }

    public int getSize() throws IOException {
	if (lines==null) {
	    throw new IOException("File has not been read");
	}
	return lines.size();
    }


    	public Iterator<String> iterator() {
        Iterator<String> it = new Iterator<String>() {

            private int currentIndex = 0;

            public boolean hasNext() {
                return lines != null && currentIndex < lines.size() && lines.get(currentIndex) != null;
            }

            public String next() {
                return lines.get(currentIndex++);
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

}
