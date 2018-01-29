package CsvUtll;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by sherry on 2018/1/29.
 */
public class CsvWriterUtil {
    private Writer outputStream;
    private String fileName;
    public char delimiter = ',';// 分隔符
    public Charset charset;
    private boolean initialized;
    private boolean closed;
    private boolean firstColumn;

    public CsvWriterUtil(String fileName, char delimiter, Charset charset) {

        this.closed = false;
        this.initialized = false;
        this.firstColumn = true;

        if (fileName == null) {
            throw new IllegalArgumentException("Parameter fileName can not be null.");
        }
        if (charset == null) {
            throw new IllegalArgumentException("Parameter charset can not be null.");
        }
        this.fileName = fileName;
        this.delimiter = delimiter;
        this.charset = charset;
    }

    public CsvWriterUtil(String fileName) {
        this(fileName, ',', Charset.forName("UTF-8"));
    }

    public CsvWriterUtil(String fileName, char delimiter) {
        this(fileName, delimiter, Charset.forName("UTF-8"));
    }

    public char getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }// 自定义分隔符

    private void checkClosed() throws IOException {
        if(this.closed) {
            throw new IOException("This instance of the CsvWriter class has already been closed.");
        }
    }

    private void checkInit() throws IOException {
        if(!this.initialized) {
            if(this.fileName != null) {
                this.outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName), this.charset));
            }
            this.initialized = true;
        }

    }

    public void write(String var1) throws IOException {

        this.checkClosed();
        this.checkInit();
        if(var1 == null) {
            var1 = "";
        }

        if(!this.firstColumn) {
            this.outputStream.write(this.delimiter);
        }

        if(var1.length() > 0) {
            var1 = var1.trim();
        }

        this.outputStream.write(var1);
        this.firstColumn = false;
    }


    public void writeRecord(String[] var1) throws IOException {
        if(var1 != null && var1.length > 0) {
            for(int i = 0; i < var1.length; ++i) {
                this.write(var1[i]);
            }
            this.endRecord();
        }
    }

    public void endRecord() throws IOException {
        this.checkClosed();
        this.checkInit();
        this.firstColumn = true;
    }

    public void flush() throws IOException {
        this.outputStream.flush();
    }

    public void close() {
        if(!this.closed) {
            this.charset = null;
            try {
                if (this.initialized) {
                    this.outputStream.close();
                }
            } catch (Exception var3) {
                ;
            }

            this.outputStream = null;
            this.closed = true;
        }
    }

}
