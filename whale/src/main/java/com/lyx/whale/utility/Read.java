package com.lyx.whale.utility;

import java.io.*;

/**
 * 测试文本文件读取
 */
public class Read {
    public static void main(String[] args) {
        int num = 0;
        File file = new File("C:\\Users\\zoe\\Desktop\\finalcomm\\negtive.txt");
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(
                    new FileInputStream(file),
                    "utf-8");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                System.out.println(num);
                num++;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
