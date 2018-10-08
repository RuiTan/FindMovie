package com.tanrui;

import java.io.*;
import java.util.Scanner;

public class SplitFile {

    public static final String path = "D:/设计模式/";

    public static final String fileName = "movies.txt";

    public static final String defaultOutFile = "movie_out_";

    public static final String fileType = ".txt";

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


    public void ReadAndSplit(){

        // 读取data
        try {
            is = new FileInputStream(path + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("SDF error : File \'" + path + fileName + "\' not found!");
            e.printStackTrace();
        }
        // 创建输入流Scanner
        sc = new Scanner(is);

        int flag = 1;
        int mount = 0;

        // 创建输出文件
        String fn = path + defaultOutFile + flag + fileType;

        FileWriter file = CreateFile(fn);

        // 创建输出流
        try {
            os = new BufferedWriter(file);

            while (sc.hasNextLine()){

                mount++;

                // 限定每个文件写DefaultLines行数的数据
                if (mount == DefaultLines){
                    os.close();
                    ++flag;
                    fn = path + defaultOutFile + flag + fileType;
                    file = CreateFile(fn);
                    mount = 0;
                    os = new BufferedWriter(file);
                    System.out.println("SDF log : file " + flag + " has been written!");
                }

                // 往文件里面写
                String line = sc.nextLine();
                try {
                    os.write(line + "\n");
                } catch (IOException e) {
                    System.out.println("SDF error : Cannot write into file \'" + path + fileName + "\'!");
                    e.printStackTrace();
                }

            }

            os.close();

        } catch (FileNotFoundException e) {
            System.out.println("SDF error : File \'" + path + fileName + "\' not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("SDF log : " + flag + " files!");

    }
}
