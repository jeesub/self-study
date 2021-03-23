package basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
/**
 * Read URL Example.
 * Read HTML file from a given URL and print it.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class ReadUrlExample {
    private static final String URL_STRING = "https://www.jeesub.com";

    private static void ReadUrl() throws IOException {
        URL url = new java.net.URL(URL_STRING);
        InputStream is = url.openStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bf = new BufferedReader(isr);
        boolean eol = false;

        while (!eol) {
            String string = bf.readLine();
            if (string == null) {
                eol = true;
            } else {
                System.out.println(string);
            }
        }

        bf.close();
        isr.close();
        is.close();
    }

    public static void main(String[] args){
        try {
            ReadUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
