
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by sherry on 2018/1/29.
 * @noinspection ALL
 */
public class testJavaCsvAPI {
    public static void main(String[] args) throws IOException {

        String writerCsvFilePath = "src/resource/writer.csv";
        CsvWriter csvWriter = new CsvWriter(writerCsvFilePath, ',', Charset.forName("UTF-8"));
        String[] contents = {"Lily","五一","90","女"};
        csvWriter.writeRecord(contents);
        csvWriter.close();


        String readerCsvFilePath = "src/resource/reader.csv";
        CsvReader csvReader = new CsvReader(readerCsvFilePath, ',', Charset.forName("UTF-8"));
        csvReader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
        String[] head = csvReader.getHeaders(); //获取表头
        while (csvReader.readRecord())
        {
            for (int i = 0; i < head.length; i++)
            {
                System.out.println(head[i] + ":" + csvReader.get(head[i]));
            }

        }
        csvReader.close();
    }
}
