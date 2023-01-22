package json;

//import com.googlecode.json-sample

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class JsonParsing {


    public static void main(String[] args) throws IOException {

        new JsonParsing().parse("");

    }
    public void parse(String text) throws IOException {

        String s = "\"foo\" is not \"bar\". specials: \b\r\n\f\t\\/";

        Path file = Paths.get("/Users/daisuke.harada/desk/untitled");

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
}
