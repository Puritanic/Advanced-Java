package FileIO;

import java.io.*;

public class FileTextWrittingDemo {
    public static void main(String[] args) {
        File f = new File("input.txt");
        PrintWriter pw = null;

        try {
            // f argument here must be instance of File class
            pw = new PrintWriter(new FileWriter(f, true));
            pw.println(42);
            pw.println("String");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if(pw != null) pw.close();
        }
    }
}
