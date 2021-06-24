package org.xpp.com.opencsv;

import cn.hutool.json.JSONUtil;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class OpencsvKit {
    public static void main(String[] args) throws Exception {
        // 读取csv
        CSVReader reader = new CSVReader(new FileReader("D:\\github\\csv\\src\\main\\resources\\opencsv_reader.csv"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            System.out.println(nextLine[0] + "  " + nextLine[1]);
        }
        reader.close();

        System.out.println("--------------------");
        // 使用数组接收readAll
        CSVReader reader2 = new CSVReader(new FileReader("D:\\github\\csv\\src\\main\\resources\\opencsv_reader.csv"));
        List<String[]> myEntries = reader2.readAll();
        System.out.println(JSONUtil.toJsonPrettyStr(myEntries));
        reader2.close();

        System.out.println("--------------------");
        // 使用自定义Bean收
        List<User> beans = new CsvToBeanBuilder(new FileReader("D:\\github\\csv\\src\\main\\resources\\opencsv_reader.csv"))
                .withType(User.class).build().parse();
        System.out.println(JSONUtil.toJsonPrettyStr(beans));

        System.out.println("--------------------");
        System.out.println("写Csv");

        CSVWriter writer = new CSVWriter(new FileWriter("D:\\github\\csv\\src\\main\\resources\\opencsv_writer.csv"));
        // feed in your array (or convert your data to an array)
        String[] entries = "first#second#third".split("#");
        writer.writeNext(entries);
        writer.close();


    }
    public static class User {
        @CsvBindByName
        public String name;
        @CsvBindByName(column = "age", required = true)
        public int age2;
    }
}
