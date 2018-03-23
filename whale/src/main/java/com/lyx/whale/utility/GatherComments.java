package com.lyx.whale.utility;

import com.lyx.whale.service.SpiderCommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.List;

public class GatherComments {
    @Autowired
    private SpiderCommentService spiderCommentService;
    private int offset = 0;

    public static void main(String[] args) {
        GatherComments gatherComments = new GatherComments();
        try {
            gatherComments.gather();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void gather() throws IOException {
        List<String> list = spiderCommentService.spiderComments("1352438", 100);
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\zoe\\Desktop\\20180317" + "\\"
                + offset + ".txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,
                "utf-8"));
        for (String s : list) {
            System.out.println(s);
            bw.write(s);
            bw.write("\r\n");
        }
        bw.close();
    }
}
