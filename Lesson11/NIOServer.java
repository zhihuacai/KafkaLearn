package com.kafka.learning.Lesson11;
import java.nio.*;
import java.nio.channels.*;
//import java.nio.channels.spi.*;
import java.nio.file.*;
import java.io.*;
import java.net.InetSocketAddress;
//import java.nio.file.spi.*;
import java.util.EnumSet;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * Created by hadoop on 9/23/18.
 */
public class NIOServer {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Usage : java -jar ServerTcpListener.jar port");
            return;
        }
        args[0] = "12345";
        try {
            final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(Integer.parseInt(args[0])));


            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        serverSocketChannel.configureBlocking(false);

                        while(true){
                            SocketChannel socketChannel =
                                    serverSocketChannel.accept();
                            if(socketChannel != null){
                                receiveFile(socketChannel);
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
            th.run();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void receiveFile(SocketChannel socketChannel) throws IOException {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间：" + sdf.format(d));

        final Path filePath = Paths.get("test");    //要将接收的文件写到当前目录的test文件中

        FileChannel fileChannel = (FileChannel.open(filePath,
                EnumSet.of(StandardOpenOption.CREATE_NEW,
                        StandardOpenOption.WRITE,
                        StandardOpenOption.TRUNCATE_EXISTING)));

        //先获取文件大小，这里我约定前8个字节表示文件大小
        ByteBuffer buf = ByteBuffer.allocate(8);
        socketChannel.read(buf);
        buf.flip();
        long fileSize = buf.getLong();
        System.out.println("fileSize :"  + fileSize);

        //接收文件内容
        fileChannel.transferFrom(socketChannel, 0, fileSize);
        fileChannel.close();

        System.out.println("当前时间：" + sdf.format(d));
    }


}
