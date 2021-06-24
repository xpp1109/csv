package org.xpp.net.sourceforge.javacsv;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 使用net.sourceforge.javacsv操作csv文件
 * <a href="http://javacsv.sourceforge.net/"> 官网 </a>
 */
public class CsvKit {
    public static void main(String[] args) throws Exception {
        // 主要有7个构造函数
        // 1 CsvReader(InputStream inputStream, char delimiter, Charset charset)
        FileInputStream fileInputStream = new FileInputStream(new File("D:\\github\\csv\\src\\main\\resources\\reader.csv"));
        CsvReader csvReader = new CsvReader(fileInputStream, ',', StandardCharsets.UTF_8);
        // 读表头
        // 读取第一行数据作为表头
        boolean b = csvReader.readHeaders();
        System.out.println("是否成功读取表头:" + b);

        String[] headers = csvReader.getHeaders();
        System.out.println("表头数据是:" + Arrays.toString(headers));

        int headCount = csvReader.getHeaderCount();
        System.out.println("header数目是:" + headCount);

        char delimiter = csvReader.getDelimiter();
        System.out.println("reader的分隔符是:" + delimiter);

        while (csvReader.readRecord()) {
            // 当前记录的index值
            long currentRecord = csvReader.getCurrentRecord();
            System.out.println("当前记录index号:" + currentRecord);
            // 读一整行
            System.out.println("---读取一整行的数据---");
            String rawRecord = csvReader.getRawRecord();
            System.out.println(rawRecord);
            // 读这行的某一列
            // 通过表头读取某一列的值
            System.out.println("---通过表头名称读取某一列的值---");
            System.out.println(csvReader.get("name"));

            System.out.println("---通过index获取值---");
            System.out.println(csvReader.get(0));

            int columnCount = csvReader.getColumnCount();
            System.out.println("本条记录中的列数:" + columnCount);

            // 获取该行记录，返回数组
            System.out.println("---获取该行记录，返回数组---");
            String[] values = csvReader.getValues();
            System.out.println(Arrays.toString(values));
        }
        fileInputStream.close();
        csvReader.close();


        System.out.println("------------------写csv文件-------------------------");
        System.out.println("有四个writer构造函数，自行查看官方文档");
        OutputStream os = new FileOutputStream(new File("src/main/resources/writer.csv"));
        CsvWriter csvWriter = new CsvWriter(os , ',', StandardCharsets.UTF_8);
        csvWriter.writeRecord(new String[]{"a", "b", "c", "d"});
        csvWriter.close();

    }
}
