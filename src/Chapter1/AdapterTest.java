package Chapter1;

import java.io.*;
import java.util.Properties;

public class AdapterTest {
    public static void main(String[] args) {
        FileIO f = new FileProperties();
        try {
            f.readFromFile("file.txt");
            f.setValue("width","1024");
            f.setValue("height","512");
            f.setValue("depth","32");
            f.writeToFile("newfile.txt");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

interface FileIO {
    public void readFromFile(String filename) throws IOException;
    public void writeToFile(String filename) throws IOException;
    public void setValue(String key,String value);
    public String getValue(String key);
}

class FileProperties implements FileIO {
    private Properties properties;

    FileProperties() {
        properties = new Properties();
    }

    @Override
    public void readFromFile(String filename) throws IOException {
        properties.load(new FileReader(filename));
    }

    @Override
    public void writeToFile(String filename) throws IOException {
        properties.store(new FileWriter(filename),"written by FileProperties");
    }

    @Override
    public void setValue(String key, String value) {
        properties.getProperty(key,value);
    }

    @Override
    public String getValue(String key) {
        return properties.getProperty(key);
    }
}