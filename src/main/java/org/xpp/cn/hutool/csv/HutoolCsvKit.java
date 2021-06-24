package org.xpp.cn.hutool.csv;

import cn.hutool.core.annotation.Alias;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.*;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;

import java.util.List;

/**
 * 使用Hutool工具操作csv文件
 */
public class HutoolCsvKit {
    public static void main(String[] args) {
        // 读取csv文件
        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file("hutool_reader.csv"));
        List<CsvRow> rows = data.getRows();
        //遍历行
        for (CsvRow csvRow : rows) {
            //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
            Console.log(csvRow.getRawList());
        }

        // 读取为Bean列表
        //假设csv文件在classpath目录下
        final List<TestBean> result = reader.read(
                ResourceUtil.getUtf8Reader("hutool_reader.csv"), TestBean.class);
        System.out.println(result);

        // 写文件
        //指定路径和编码
        CsvWriter writer = CsvUtil.getWriter("hutool_writer.csv", CharsetUtil.CHARSET_UTF_8);
//按行写出
        writer.write(
                new String[]{"a1", "b1", "c1"},
                new String[]{"a2", "b2", "c2"},
                new String[]{"a3", "b3", "c3"}
        );
    }

    /**
     * 用户类
     */
    private static class TestBean {
        // 如果csv中标题与字段不对应，可以使用alias注解设置别名
        @Alias("姓名")
        private String name;
        private String gender;
        private String focus;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getFocus() {
            return focus;
        }

        public void setFocus(String focus) {
            this.focus = focus;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return JSONUtil.toJsonStr(this);
        }
    }
}