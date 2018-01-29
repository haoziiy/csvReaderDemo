
import CsvUtll.CsvWriterUtil;

import java.io.IOException;

/**
 * Created by sherry on 2018/1/29.
 */
public class testDemo {

    public static void main(String[] args) throws IOException {
        String writerCsvFilePath = "src/resource/writer.csv";
        CsvWriterUtil csvWriterUtil = new CsvWriterUtil(writerCsvFilePath);
        String[] contents = {"Lily","五一","90","女"};
        try {
            csvWriterUtil.writeRecord(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
        csvWriterUtil.close();

        String readerCsvFilePath = "src/resource/reader.csv";
        CsvReaderUtil csvReaderUtil = new CsvReaderUtil(readerCsvFilePath);
        csvReaderUtil.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
        String[] head = csvReaderUtil.getHeaders(); //获取表头
        while (csvReaderUtil.readRecord())
        {
            for (int i = 0; i < head.length; i++)
            {
                System.out.println(head[i] + ":" + csvReaderUtil.get(head[i]));
            }

        }
        csvReaderUtil.close();

    }
}
