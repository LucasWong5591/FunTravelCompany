package utilities;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Driver;

public class FileUtil {

    public String read(String filePath){

        ClassLoader classLoader = getClass().getClassLoader();

        String data = "";

        try {
            File myObj = new File(Objects.requireNonNull(classLoader.getResource(filePath)).getFile());
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }

    public void write(String filePath, String json) {

        ClassLoader classLoader = getClass().getClassLoader();

        try {

            PrintWriter pw = new PrintWriter(Objects.requireNonNull(classLoader.getResource(filePath)).getFile());
            pw.print("");
            pw.print(json);
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
