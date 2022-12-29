package test;
import java.io.*;
import java.util.Scanner;


public class IOSearcher {

    public static boolean search(String str, String... args)  {

        String[] files = args;

        for (String file : files) {

            try {

                Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)));
                String[] arrWords;
                while (scanner.hasNext()) {

                    arrWords = scanner.next().split(" ");
                    for (String word : arrWords)
                        if (word.equals(str))
                            return true;
                }
                scanner.close();
            }
            catch (FileNotFoundException e) { e.printStackTrace(); }
        }
        return false;
    }
}
