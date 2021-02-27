package basics;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StandardInputExample {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = bufferedReader.readLine().split(" ");
        String secondLine = bufferedReader.readLine();
        bufferedReader.close();

        System.out.println(Arrays.toString(firstLine));
        System.out.println(secondLine);
        System.out.println("finished");
    }
}
