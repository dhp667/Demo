package com.example.demo.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NetCodeSingle {
    private ServerSocketChannel server = null;
    private Selector selector = null;   //linux 多路复用器（select poll epoll） nginx  event{}
    int port = 9090;

    public void initServer() {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            selector = Selector.open();  //  select  poll  *epoll
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        initServer();
        System.out.println("服务器启动了。。。。。");
        try {
            while (true) {
                Set<SelectionKey> keys = selector.keys();
//                System.out.println(keys.size()+"   size");
                while (selector.select() > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = selectionKeys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);  //只处理了  read  并注册 关心这个key的write事件

                        }
//                        iter.remove();
                    }

                }
//                Thread.sleep(100);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void acceptHandler(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel client = ssc.accept();
            client.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(10);
            client.register(selector, SelectionKey.OP_READ, buffer);
            System.out.println("-------------------------------------------");
            System.out.println("新客户端：" + client.getRemoteAddress());
            System.out.println("-------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
        key.cancel();
    }

    public void readHandler(SelectionKey key) {
        new Thread(() -> {
            System.out.println("read handler.....");
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            int read = 0;
            try {
                while (true) {
                    read = client.read(buffer);
                    if (read > 0) {
                        buffer.flip();
                        int size = buffer.limit();
                        byte[] bytes = new byte[size];
                        buffer.get(bytes);
                        System.out.print("client发送的信息为" + new String(bytes));
                        buffer.clear();
                        //关心  OP_WRITE 其实就是关系send-queue是不是有空间
                    } else if (read == 0) {
                        break;
                    } else {
                        client.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            key.cancel();
        }).start();


    }

    public static void main(String[] args) {
        NetCodeSingle single = new NetCodeSingle();
        single.start();
    }
}
