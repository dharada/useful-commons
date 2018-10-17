package parse;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.Consumer;

public class JsonReplace {


    public void execute(String inFilePath, String outFilePath) throws IOException {


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Consumer<? super String> action = new Consumer<String>() {

            StringBuilder bodybuilder = new StringBuilder();
            int bodycount = 0;

            @Override
            public void accept(String line) {

                if (bodycount == 0 && line.contains("\"body\"") && line.contains("\"\"\"")) {

                    bodycount++;

                    String newline = line.replace("\"\"\"", "\"");
                    newline = newline.replace("\n", "");

                    bodybuilder.setLength(0);
                    bodybuilder.append(newline);

                } else if (bodycount > 0) {

                    if (line.contains("\"\"\",")) {

                        String newline = line.replace("\"\"\"", "\"");
                        bodybuilder.append(newline);

                        try {

                            byteArrayOutputStream.write(bodybuilder.toString().getBytes("UTF-8"));
//                            System.out.print(bodybuilder.toString());

                            bodybuilder.setLength(0);
                            bodycount = 0;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        String newline = line.replace("\n", "");
                        bodybuilder.append(newline);
                    }

                } else {

                    try {
                        byteArrayOutputStream.write(line.getBytes("UTF-8"));
                        byteArrayOutputStream.write("\n".getBytes());

//                        System.out.println(line);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        FileUtils.readLines(new File(inFilePath), "UTF-8").forEach(action);

        try(FileOutputStream outputStream = new FileOutputStream(outFilePath)) {
            byteArrayOutputStream.writeTo(outputStream);
        }
    }



    public void replaceHhtmlsourceField(String inFilePath, String outFilePath) throws IOException {


        //"htmlsource": "　（７月１日）最高顧問、沢田秀雄",

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Consumer<? super String> action = new Consumer<String>() {

            StringBuilder bodybuilder = new StringBuilder();

            @Override
            public void accept(String line) {

                if (line.contains("\"htmlsource\"") && line.contains("\",")) {

                    bodybuilder.append("          \"htmlsource\": \"　（７月１日）最高顧問、沢田秀雄\",\n");

                    try {
                        byteArrayOutputStream.write(bodybuilder.toString().getBytes("UTF-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    bodybuilder.setLength(0);

                } else {

                    try {
                        byteArrayOutputStream.write(line.getBytes("UTF-8"));
                        byteArrayOutputStream.write("\n".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        FileUtils.readLines(new File(inFilePath), "UTF-8").forEach(action);

        try(FileOutputStream outputStream = new FileOutputStream(outFilePath)) {
            byteArrayOutputStream.writeTo(outputStream);
        }


    }
}
