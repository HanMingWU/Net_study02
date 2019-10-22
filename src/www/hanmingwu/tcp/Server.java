package www.hanmingwu.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器、
 * 1.指点窗口，使用serversocket创建服务器
 * 2.阻塞式接受
 * 操作：输入输出流操作
 * 释放资源
 */
public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("---server---");
        ServerSocket server=new ServerSocket(8888);
        //阻塞式接受
        Socket client= server.accept();
        System.out.println("一个客户端建立了连接");
        //操作
        DataInputStream dis=new DataInputStream(client.getInputStream());
        String data=  dis.readUTF();
        System.out.println(data);
        dis.close();

        client.close();
        server.close();
    }
}
