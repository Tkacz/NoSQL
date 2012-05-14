/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nosql;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rafal Tkaczyk
 */
public class CSVtoJSON {

    String headers[];

    public void convert(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            FileWriter fileWriter = new FileWriter(fileName.replace(".csv", ".json"));
            BufferedReader br = new BufferedReader(fileReader);
            String str, s = br.readLine();
            headers = s.split(",");
            int headersNumber = headers.length;
            while ((s = br.readLine()) != null) {
                fileWriter.write("{ ");
                for (int i = 0, h = 0; h < headersNumber; h++) {
                    str = "";

                    if (h == 0 && headers[0].length() == 2) {
                        fileWriter.write("\"id\": ");
                    } else {
                        fileWriter.write(headers[h] + ": ");
                    }

                    if (s.charAt(i) == '"') {//tekst
                        str += s.charAt(i++);
                        while (s.charAt(i) != '"') {
                            str += s.charAt(i++);
                        }
                        fileWriter.write(str + "\"");
                        i += 2;
                    } else if (s.charAt(i) == 'N') {//null
                        fileWriter.write("\"\"");
                        i += 3;
                    } else {//liczba lub data
                        while (s.charAt(i) != ',') {
                            str += s.charAt(i++);
                        }
                        if (str.indexOf('-') > 0) {//data
                            fileWriter.write("\"" + str + "\"");
                        } else {//liczba
                            fileWriter.write(str);
                        }
                        i++;
                    }
                    if (h < headersNumber - 1) {
                        fileWriter.write(", ");
                    }
                }
                fileWriter.write(" }\n");
            }
            fileReader.close();
            fileWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}
