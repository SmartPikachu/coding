package cn.jjx.coding.springboot.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ApacheCommonIOTest {
    public static void main(String[] args) throws IOException {
        FileUtils.lineIterator(new File("xxx"));
    }
}
