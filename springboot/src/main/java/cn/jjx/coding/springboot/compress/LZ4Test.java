package cn.jjx.coding.springboot.compress;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class LZ4Test {

    @Test
    public void testLz41() throws IOException {
        LZ4Factory factory = LZ4Factory.fastestInstance();

        byte[] data = ("aaaaaaaaaaajfsldfjslfjslfsfjslfkjdsfjdslffdsajfldksajfdlskajfsdlfjajfldskjfsdlkfj" +
                "adsfjdsalkfjdlsakfjkdlasfjldskajfkladsjfldaksfjlkadsjflkadsfjkasd;lf" +
                "jlksfjlksdajflaksdjflkasdjflaksjflkasdjflsakdjfldsakfjlksadfjlksajflksajfds" +
                "afadsfasdfdasfdsafdsafdsaffdsafdasfasdfdsafsdafdlksafjlkasdjflkadsjflaskdjflaksdjflkasdj").getBytes("UTF-8");

        String pathName = "C:\\Users\\dell\\Desktop\\picture\\test.jpg";
        byte[] data1 = Files.readAllBytes(Paths.get(pathName));
        final int decompressedLength = data.length;
        final int decompressedLength1 = data1.length;
//        System.out.println("压缩前字节数组长度 decompressedLength_bytes: "+decompressedLength);
        System.out.println("压缩前字节数组长度 decompressedLength1_bytes: "+decompressedLength1);

//        System.out.println("压缩前转成字符串的长度 decompressedLength_string: "+new String(data,0,decompressedLength).length());
//        System.out.println("压缩前转成字符串的长度 decompressedLength1_string: "+new String(data1,0,decompressedLength1).length());


        // compress data
        LZ4Compressor compressor = factory.fastCompressor();
        int maxCompressedLength = compressor.maxCompressedLength(decompressedLength);
        int maxCompressedLength1 = compressor.maxCompressedLength(decompressedLength1);
        byte[] compressed = new byte[maxCompressedLength];
        byte[] compressed1 = new byte[maxCompressedLength1];
//        int compressedLength = compressor.compress(data, 0, decompressedLength, compressed, 0, maxCompressedLength);
        int compressedLength1 = compressor.compress(data1, 0, decompressedLength1, compressed1, 0, maxCompressedLength1);
//        System.out.println("压缩后字节数组长度 compressedLength: "+compressedLength+"  压缩率 compress ratio  "+ compressedLength/(double) decompressedLength);
        System.out.println("压缩后字节数组长度 compressedLength1: "+compressedLength1+"  压缩率 compress ratio  "+ compressedLength1/(double) decompressedLength1);

//        System.out.println("压缩后转成字符串的长度 compressed_String: "+new String(compressed,0,compressedLength).length());
//        System.out.println("压缩后转成字符串的长度 compressed1_String: "+new String(compressed1,0,compressedLength1).length());

        // decompress data
        // - method 1: when the decompressed length is known
        LZ4FastDecompressor decompressor = factory.fastDecompressor();
        byte[] restored = new byte[decompressedLength];
        byte[] restored1 = new byte[decompressedLength1];
//        int restoreLength = decompressor.decompress(compressed, 0, restored, 0, decompressedLength);
        int restoreLength1 = decompressor.decompress(compressed1, 0, restored1, 0, decompressedLength1);
//        System.out.println("解压缩后字节数组的长度 restoreLength:"+restoreLength);
        System.out.println("解压缩后字节数组的长度 restoreLength1:"+restoreLength1);

    }

}
