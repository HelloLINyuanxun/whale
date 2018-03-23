package com.lyx.whale.utility;

import java.io.*;

public class SpiltComments {
    public static void main(String[] args) throws IOException {
        File root = new File("C:\\Users\\zoe\\Desktop\\finalcomm");
        if (!root.exists()) throw new IllegalArgumentException(String.format("目录 %s 不存在", root.getAbsolutePath()));
        if (!root.isDirectory())
            throw new IllegalArgumentException(String.format("目录 %s 不是一个目录", root.getAbsolutePath()));
        File[] filess = root.listFiles();
        for (File file : filess) {
            if (!file.isFile()) continue;
            String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            int offset = 0;
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file),
                    "utf-8");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\zoe\\Desktop\\finalcomm\\commentsForHanLP\\" + fileName + "\\"
                        + offset + ".txt"));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,
                        "utf-8"));
                bw.write(line);
                bw.write("\r\n");
                offset++;
                bw.close();
            }
        }
        System.out.println("评论分离成功");
    }
}
