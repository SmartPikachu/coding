package cn.jjx.coding.springboot.netty.shandianxia.chapter2;




import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    //下面代码是用Java NIO的代码实现服务端，但是不推荐这么做。
    public static void main(String[] args) throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();
        new Thread(()->{
            try {
                ServerSocketChannel listenerChannel=ServerSocketChannel.open();
                listenerChannel.socket().bind(new InetSocketAddress(8000));
                listenerChannel.configureBlocking(false);
                listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                while(true){
                    //监测是否有新连接，这里的1是指阻塞的时间为1ms
                    if(serverSelector.select(1)>0){
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();
                        while(keyIterator.hasNext()){
                            SelectionKey key = keyIterator.next();
                            if(key.isAcceptable()){
                                try {
                                    SocketChannel clientChannel =
                                            ((ServerSocketChannel)key.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(clientSelector,SelectionKey.OP_READ);
                                }finally {
                                    keyIterator.remove();
                                }
                            }
                        }
                    }
                }
            }catch (IOException ignored){
            }
        }).start();

        new Thread(()->{
            try {
                while (true){
                    if(clientSelector.select(1)>0){
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();
                        while(keyIterator.hasNext()){
                            SelectionKey key = keyIterator.next();
                            if(key.isReadable()){
                                try {
                                    SocketChannel clientChannel = (SocketChannel) key.channel();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    clientChannel.read(byteBuffer);
                                    byteBuffer.flip();
                                    System.out.println(
                                            Charset.defaultCharset().newDecoder().
                                                    decode(byteBuffer).toString());
                                }finally {
                                    keyIterator.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }
                        }
                    }
                }
            }catch (IOException ignored){

            }
        }).start();
    }


}
