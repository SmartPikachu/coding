package cn.jjx.coding.springboot.io;

import com.google.common.io.Files;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @Description: 逐行读取文件性能对比
 * @Author: Seven-Steven
 * @Date: 19-1-25
 **/
public class ReadByLineFromFileTest {

    public static void main(String[] args) {
        ReadByLineFromFileTest test = new ReadByLineFromFileTest();
        String filePath = "./testFile.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            // 随机写入 1000000 行内容
            test.writeRandom(filePath, 1000000);
        }

        long before, after, time;
        // 使用 BufferedReader 逐行读取文件
        before = System.currentTimeMillis();
        test.bufferedReader(filePath);
        after = System.currentTimeMillis();
        time = after - before;
        System.out.println("BufferedReader 耗时: " + time + "ms");

        // 使用 Scanner 逐行读取文件
        before = System.currentTimeMillis();
        test.scanner(filePath);
        after = System.currentTimeMillis();
        time = after - before;
        System.out.println("Scanner 耗时: " + time + "ms");

        // 使用 Apache Commons IO 流逐行读取文件
        before = System.currentTimeMillis();
        test.apacheCommonsIo(filePath);
        after = System.currentTimeMillis();
        time = after - before;
        System.out.println("Apache Commons IO 耗时: " + time + "ms");

        // 使用 InputStreamReader 逐字符读取文件
        before = System.currentTimeMillis();
        test.inputStreamReader(filePath);
        after = System.currentTimeMillis();
        time = after - before;
        System.out.println("InputStreamReader 耗时: " + time + "ms");

        // 使用 FileInputStream 逐字符读取文件
        before = System.currentTimeMillis();
        test.fileInputStream(filePath);
        after = System.currentTimeMillis();
        time = after - before;
        System.out.println("FileInputStream 耗时: " + time + "ms");


        // 使用 BufferedInputStream 逐字符读取文件
        before = System.currentTimeMillis();
        test.bufferedInputStream(filePath);
        after = System.currentTimeMillis();
        time = after - before;
        System.out.println("BufferedInputStream 耗时: " + time + "ms");

        // 使用 FileUtils 一次性读取文件所有行
        before = System.currentTimeMillis();
        test.fileUtils(filePath);
        after = System.currentTimeMillis();
        time = after - before;
        System.out.println("FileUtils 耗时: " + time + "ms");

        // 使用 Files 一次性读取文件所有行
        before = System.currentTimeMillis();
        test.files(filePath);
        after = System.currentTimeMillis();
        time = after - before;
        System.out.println("Files 耗时: " + time + "ms");
    }

    /**
     * @Description: 使用 Apache Commons IO 流逐行读取文件
     * Maven 依赖:
     *         <!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
     *         <dependency>
     *             <groupId>commons-io</groupId>
     *             <artifactId>commons-io</artifactId>
     *             <version>2.6</version>
     *         </dependency>
     * @Param: [filePath] 文件路径
     * @Author: Seven-Steven
     * @Date: 19-1-24
     **/
    public void apacheCommonsIo(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        try {
            LineIterator iterator = FileUtils.lineIterator(file, "UTf-8");
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                // TODO
                // System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 使用 Scanner 类逐行读取
     * @Param: [filePath] 文件路径
     * @Author: Seven-Steven
     * @Date: 19-1-24
     **/
    public void scanner(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        FileInputStream fileInputStream = null;
        Scanner scanner = null;
        try {
            fileInputStream = new FileInputStream(file);
            scanner = new Scanner(fileInputStream, "UTF-8");

            while (scanner.hasNextLine()) {
                // TODO things
                String line = scanner.nextLine();
                // System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * @Description: 使用 Files 一次性读取所有行
     * Maven 依赖:
     *         <!-- https://mvnrepository.com/artifact/com.google.guava/guava -->
     *         <dependency>
     *             <groupId>com.google.guava</groupId>
     *             <artifactId>guava</artifactId>
     *             <version>r05</version>
     *         </dependency>
     * @Param: [filePath] 文件路径
     * @Author: Seven-Steven
     * @Date: 19-1-24
     **/
    public void files(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        try {
            List<String> fileLines = Files.readLines(file, Charsets.toCharset("UTF-8"));
            for (String str : fileLines) {
                // TODO things
                // System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 使用 FileUtils 一次性将文件所有行读入内存
     * Maven 依赖:
     *         <!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
     *         <dependency>
     *             <groupId>commons-io</groupId>
     *             <artifactId>commons-io</artifactId>
     *             <version>2.6</version>
     *         </dependency>
     * @Param: [filePath] 文件路径
     * @Author: Seven-Steven
     * @Date: 19-1-24
     **/
    public void fileUtils(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        try {
            List<String> fileLines = FileUtils.readLines(file, Charsets.UTF_8);
            for (String str : fileLines) {
                // TODO
                // System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bufferedInputStream(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);

            int temp;
            char character;
            String line = "";
            while ((temp = bufferedInputStream.read()) != -1) {
                character = (char) temp;
                if (character != '\n') {
                    line += character;
                } else {
                    // TODO
                    // System.out.println(line);
                    line = "";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @Description: 使用 FileInputStream 逐字符读取文件
     * @Param: [filePath] 文件路径
     * @Author: Seven-Steven
     * @Date: 19-1-23
     **/
    public void fileInputStream(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            int temp;
            char character;
            String line = "";
            while ((temp = fileInputStream.read()) != -1) {
                character = (char) temp;
                if (character != '\n') {
                    line += character;
                } else {
                    // TODO
                    // System.out.println(line);
                    line = "";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @Description: 使用 InputStreamReader 逐行读取文件
     * @Param: [filePath] 文件路径
     * @Author: Seven-Steven
     * @Date: 19-1-23
     **/
    public void inputStreamReader(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            int temp;
            char character;
            String line = "";
            while ((temp = inputStreamReader.read()) != -1) {
                character = (char) temp;
                if (character != '\n') {
                    line += character;
                } else {
                    // TODO
                    // System.out.println(line);
                    line = "";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @Description: 使用 BufferedReader 逐行读取文件内容
     * @Param: [filePath] 文件路径
     * @Author: Seven-Steven
     * @Date: 19-1-23
     **/
    public void bufferedReader(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                // TODO things
                // System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @Description: 随机往文件中写入 totalLines 行内容
     * @Param: [filePath, totalLines] 文件路径, 内容行数
     * @Author: Seven-Steven
     * @Date: 19-1-23
     **/
    public void writeRandom(String filePath, int totalLines) {
        RandomAccessFile file = null;
        Random random = new Random();
        try {
            file = new RandomAccessFile(filePath, "rw");
            long length = file.length();
            for (int i = 0; i < totalLines; i++) {
                file.seek(length);
//                int number = random.nextInt(1000000);
                String[] ss=new String[4];
                ss[0]="5324877543758974554afsfa  202323432424 F 3254253253252352 发阿斯蒂芬 424243324324s423 分开发发的" + "\n";
                ss[1]="5324877543758974554afdsffff  202323432424 F 3254253253252fad352 发阿斯蒂芬 42424332s4324423 分开发发的" + "\n";
                ss[2]="53248775437589745dfaf54357  202323432424 F 3254253253252352 发阿斯蒂芬 4242433ss24324423 分开发发的" + "\n";
                ss[3]="53248775437fads58974554357  202323432424 F 325425325325fdsaf2352 发阿斯蒂芬 42424332432442fa3 分开发发的" + "\n";
                String line = ss[random.nextInt(4)];
                file.writeBytes(line);
                length += line.length();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

