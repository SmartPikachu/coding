package cn.jjx.coding.springboot.compress;

import com.github.luben.zstd.Zstd;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class ZstdTest1 {

    @Test
    public void zstdTest1() throws IOException {
        String pathName = "C:\\Users\\dell\\Desktop\\picture\\test.jpg";
        byte[] data = Files.readAllBytes(Paths.get(pathName));
        final int decompressedLength = data.length;
        byte[] compressData = new byte[decompressedLength];
        long compressDataLength = Zstd.compress(compressData,data,4);
        System.out.println("压缩前字节数组长度:  "+decompressedLength+"    压缩后字节数组的长度:  "+compressDataLength);
    }

    public static void main(String[] args) throws IOException{
        String pathName = "C:\\Users\\dell\\Desktop\\picture\\test.jpg";
        byte[] data = Files.readAllBytes(Paths.get(pathName));

        byte[] data1 = new byte[1000000];
        Random random = new Random(11);
        for(int i=0;i<1000000;i++){
            data1[i]= (byte) ('0'+random.nextInt(129));
        }

//        FileInputStream fi = new FileInputStream(pathName);
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(fi);
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        byte[] bytes = new byte[1024];
//        int len=0;
//        while((len=(bufferedInputStream.read(bytes)))!=-1){
//            byteArrayOutputStream.write(bytes,0,len);
//        }
//        byte[] data1 = byteArrayOutputStream.toByteArray();

        final int decompressedLength = data.length;
        byte[] compressData = new byte[decompressedLength*10];
        long compressDataLength = Zstd.compress(compressData,data,3);
        System.out.println("压缩前字节数组长度:  "+decompressedLength+"    压缩后字节数组的长度:  "+compressDataLength
        +"压缩比率: "+compressDataLength/(double)decompressedLength);

        final int decompressedLength1 = data1.length;
        byte[] compressData1 = new byte[decompressedLength*10];
        long compressDataLength1 = Zstd.compress(compressData1,data1,3);
        System.out.println("压缩前字节数组长度:  "+decompressedLength1+"    压缩后字节数组的长度:  "+compressDataLength1
                +"压缩比率: "+compressDataLength1/(double)decompressedLength1);
    }
}
