package json;

//import com.googlecode.json-sample

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class JsonParsing {


    public static void main(String[] args) throws IOException {

        Path file = Paths.get("/Users/daisuke.harada/desk/untitled-BACKUP2");

        String fileStringWithLF = Files.readString(file);
        new JsonParsing().descape(fileStringWithLF);
       // new JsonParsing().parse("");

    }
    public void parse(String text) throws IOException {

//        String s = "\"foo\" is not \"bar\". specials: \b\r\n\f\t\\/";

//        Path file = Paths.get("/Users/daisuke.harada/desk/untitled");
        Path file = Paths.get("/Users/daisuke.harada/desk/untitled-BACKUP");

        String fileStringWithLF = Files.readString(file);
        String fileStrWithCRLF = fileStringWithLF.replaceAll("\n", "\r\n");


        System.out.println(JSONObject.toString("script", fileStrWithCRLF));

//        JSONObject jsonObject = new JSONObject();
//        String text = "Text with special character /\"\'\b\f\t\r\n.";
//        System.out.println(text);
//        System.out.println("After escaping.");
//        text = jsonObject.escape(text);
//        System.out.println(text);
    }

    public void descape(String json) {

        try {
            System.out.println(new JSONParser().parse(json));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
}
