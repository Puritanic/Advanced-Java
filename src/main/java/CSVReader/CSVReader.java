package CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;

public class CSVReader {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(Objects.requireNonNull(CSVReader.class.getResource("SacramentocrimeJanuary2006.csv")).getFile()));

            while(br.readLine() != null){
                String line = br.readLine();
                String[] values = line.split(",");
                System.out.println("Date: " + values[0] + ", Crime description: " + values[5]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
