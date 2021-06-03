package FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileTextReadingDemo {
    public static void main(String[] args) {
        File f = new File("input.txt");
        Scanner s = null;

        try {
            // f argument here must be instance of File class
            s = new Scanner(f);
            String line = s.nextLine();
            System.out.println(line);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
           if(s != null) s.close();
        }
    }
}
