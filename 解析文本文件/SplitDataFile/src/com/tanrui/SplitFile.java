package com.tanrui;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class SplitFile {

//    public static final String path = "D:/设计模式/";

    public static final String path = "/Users/tanrui/Desktop/";

    public static final String fileName = "movies.txt";

    public static final String defaultOutFile = "movie_out_";

    public static final String fileType = ".csv";

    private InputStream is;

    private BufferedWriter os;

    private Scanner sc;

    public static final int DefaultLines = 8 * 50000;

    private FileWriter CreateFile(String fn){

        FileWriter file = null;
        try {
            file = new FileWriter(fn);
        } catch (IOException e) {
            System.out.println("SDF error : Cannot create file \'" + fn + "\'!");
            e.printStackTrace();
        }

        return file;
    }


    public void ReadAndSplit() throws IOException {

        // 读取data
        try {
            is = new FileInputStream(path + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("SDF error : File \'" + path + fileName + "\' not found!");
            e.printStackTrace();
        }
        // 创建输入流Scanner
        sc = new Scanner(is);

        WriteToFile(sc);

        sc.close();

//
//        int flag = 1;
//        int mount = 0;
//
//        // 创建输出文件
//        String fn = path + defaultOutFile + flag + fileType;
//
//        FileWriter file = CreateFile(fn);
//
//        // 创建输出流
//        try {
//            os = new BufferedWriter(file);
//
//            while (sc.hasNextLine()){
//
//                mount++;
//
//                // 限定每个文件写DefaultLines行数的数据
//                if (mount == DefaultLines){
//                    os.close();
//                    ++flag;
//                    fn = path + defaultOutFile + flag + fileType;
//                    file = CreateFile(fn);
//                    mount = 0;
//                    os = new BufferedWriter(file);
//                    System.out.println("SDF log : file " + flag + " has been written!");
//                }
//
//                // 往文件里面写
//                String line = sc.nextLine();
//                try {
//                    os.write(line + "\n");
//                } catch (IOException e) {
//                    System.out.println("SDF error : Cannot write into file \'" + path + fileName + "\'!");
//                    e.printStackTrace();
//                }
//            }
//
//            os.close();
//
//        } catch (FileNotFoundException e) {
//            System.out.println("SDF error : File \'" + path + fileName + "\' not found!");
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("SDF log : " + flag + " files!");

    }

    public void WriteToFile(Scanner sc) throws IOException {
        String split = ",";
        // 创建输出流文件
        BufferedWriter bufferedWriter = new BufferedWriter(CreateFile("comment.csv"));
        // 是否输出换行
        boolean enter = false;
        // 读取文件
        while (sc.hasNextLine()){
            // 获取下一行
            String line = sc.nextLine();
            // 不为空
            if (line.length()!=0){
                // 获取结果
                String[] values = (line.split(":"));
                String value = values[values.length-1].trim();
                // 双引号全部更换为引号
                value = value.replace('\"', '\'');
                // 结论添加引号
                if (line.split(":")[0].equals("review/summary")){
                    value = "\"" + value + "\"";
                }
                // 添加换行
                if (line.split(":")[0].equals("review/text")){
                    bufferedWriter.write('\n');
                    System.out.print('\n');
                }else {
                    // 写入文件
                    bufferedWriter.write(value + split);
                    System.out.print(value + split);
                }
            }
        }
        bufferedWriter.close();
    }
}
