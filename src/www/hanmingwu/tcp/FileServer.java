package www.hanmingwu.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器、
 * 1.指点窗口，使用serversocket创建服务器
 * 2.阻塞式接受
 * 操作：输入输出流操作
 * 释放资源
 */
public class FileServer {
    public static void main(String[] args) throws IOException {
        System.out.println("---server---");
        ServerSocket server=new ServerSocket(8888);
        //阻塞式接受
        Socket client= server.accept();
        System.out.println("一个客户端建立了连接");
        //操作:文件拷贝存储
        InputStream is=new BufferedInputStream(client.getInputStream());
        OutputStream os=new BufferedOutputStream(new FileOutputStream("src/tcp.png"));
        //使用容器保存读取的文件数据
        byte[] flush=new byte[1024];
        int len=-1;
        //通过read读取数据到数组中
        while ((len=is.read(flush))!=-1){
            os.write(flush,0,len);

        }

        os.flush();
        //释放资源
        os.close();
        is.close();

        client.close();
        server.close();

    }
}
