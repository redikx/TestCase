package testcase_Junit;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

// Verify if input files exists
@RunWith(Parameterized.class)
public class testCase_FilesCheck {

    @Parameters
    public static String[] fileList() {
	String files[] = { "c:\\Java\\Projects\\source_file1.txt", "c:\\Java\\Projects\\source_file2.txt" };
	return files;
    }

    private String input;

    public testCase_FilesCheck(String input) {
	super();
	this.input = input;
    }

    @Test
    public void ifFileExists() {
	assertTrue((new File(input).exists()));
    }

    @Test
    public void ifFileReadable() {
	assertTrue(new File(input).canRead());
    }

    @Test
    public void ifFileSizeNotZero() {
	assertTrue(new File(input).length() > 0);
    }
    }

 