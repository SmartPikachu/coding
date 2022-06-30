package cn.jjx.coding.springboot.compress;

import com.github.luben.zstd.Zstd;
import com.github.luben.zstd.ZstdInputStream;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class ZstdTest {

    public static long compressFile(String inFile, String outFolder, int compressionLevel) throws IOException {
        File file = new File(inFile);
        File outFile = new File(outFolder, file.getName() + ".zs");
        long numBytes = 0l;
        ByteBuffer inBuffer = ByteBuffer.allocateDirect(8 * 1024 * 1024);//要被压缩的字节缓冲区
        ByteBuffer compressedBuffer = ByteBuffer.allocateDirect(8 * 1024 * 1024);//压缩后放置到该缓冲区
        try (RandomAccessFile inRaFile = new RandomAccessFile(file, "r");//读取文件
             RandomAccessFile outRaFile = new RandomAccessFile(outFile, "rw");
             FileChannel inChannel = inRaFile.getChannel();//通道
             FileChannel outChannel = outRaFile.getChannel()) {
            inBuffer.clear();
            while (inChannel.read(inBuffer) > 0) {//当文件还有字节未压缩时
                inBuffer.flip();//反转缓冲区的读写模式
                compressedBuffer.clear();
                //将 inBuffer的0-inBuffer.limit()压缩到compressedBuffer的0-compressedBuffer.capacity()。
                long compressedSize = Zstd.compressDirectByteBuffer(compressedBuffer, 0, compressedBuffer.capacity(), inBuffer, 0, inBuffer.limit(), compressionLevel);
                numBytes += compressedSize;
                compressedBuffer.position((int) compressedSize);//
                compressedBuffer.flip();
                outChannel.write(compressedBuffer);//把压缩后得到的缓冲区写入文件输出通道
                inBuffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numBytes;
    }

    public static void decompressFile(String afterCompressFile, String decompressFile) {
        File file = new File(afterCompressFile); //待解压文件
        File out = new File("xxx");  //解压后文件

        byte[] buffer = new byte[1024 * 1024 * 8];
        FileInputStream fi = null;
        FileOutputStream fo = null;
        ZstdInputStream zs = null;
        try {
            fo = new FileOutputStream(out);
            fi = new FileInputStream(file.getPath());
            zs = new ZstdInputStream(fi); //将文件输入流复制到zs
            while (true) {
                int count = zs.read(buffer, 0, buffer.length);//zs中重写了read方法，该方法包含解压过程，将0-buffer.length读入buffer
                if (count == -1) {
                    break;
                }
                fo.write(buffer, 0, count);//将buffer中的0-count写入文件输出流
            }
            fo.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zs != null) {
                try {
                    zs.close();
                } catch (Exception x) {
                }
            }
            if (fi != null) {
                try {
                    fi.close();
                } catch (Exception x) {
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (Exception x) {
                }
            }
        }
    }
}