package com.kafka.learning.Lesson11;
import java.nio.*;
import java.nio.channels.*;
//import java.nio.channels.spi.*;
import java.nio.file.*;
import java.io.*;
import java.net.InetSocketAddress;
//import java.nio.file.spi.*;
import java.util.EnumSet;




/**
 * Created by hadoop on 9/23/18.
 */
public class NIOClient {
    public static void main(String[] args) {

        if(args.length != 3){
            System.out.println("Usage :  java -jar ClientTcpSend.jar ipaddress port  filename");
            return;
        }
        args [0] = "spark1234";
        args [1] = "12345";
        args [2] = "/home/hadoop/derby.log";
        SocketChannel socketChannel;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(args [0], Integer.parseInt(args[1])));

            final Path filePath = Paths.get(args[2]);

            FileChannel fileChannel = (FileChannel.open(filePath,
                    EnumSet.of(StandardOpenOption.READ)));

            long start = System.currentTimeMillis();

            //用来发送文件大小
            ByteBuffer buf = ByteBuffer.allocate(8);
            buf.asLongBuffer().put(fileChannel.size());
            socketChannel.write(buf);

            //发送文件内容
            fileChannel.transferTo(0, fileChannel.size(), socketChannel);
            fileChannel.close();
            socketChannel.close();

            long end = System.currentTimeMillis();

            System.out.println("Total use " + (end - start) + "ms");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}






