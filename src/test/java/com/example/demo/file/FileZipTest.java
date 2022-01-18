package com.example.demo.file;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author banyue
 * date 2020-08-31
 */
public class FileZipTest {

    public static void main(String[] args) {
        String ZIP_FILE = "d:/sql.zip";
        String FILE_NAME = "sql";
        zipFileBuffer(ZIP_FILE, FILE_NAME);
    }

    public static void zipFileBuffer(String ZIP_FILE, String FILE_NAME) {
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut)) {
            //开始时间
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("D:/sql.txt"))) {
                    zipOut.putNextEntry(new ZipEntry(FILE_NAME + i + ".txt"));
                    byte[] b = new byte[1024*2];
                    int a = 0;
                    while((a = bufferedInputStream.read(b)) != -1){
                        bufferedOutputStream.write(a);
                    }
//                    int temp = 0;
//                    while ((temp = bufferedInputStream.read()) != -1) {
//                        bufferedOutputStream.write(temp);
//                    }
                }
            }
            printInfo(beginTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void printInfo(long time){
        System.out.println("consume time:" + (System.currentTimeMillis() - time));
    }

}
