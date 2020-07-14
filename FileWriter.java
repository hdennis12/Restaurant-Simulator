package data;

import java.io.*;

public class FileWriter {

    public void writeInFile(String string, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.println(string);
        writer.close();
    }

}