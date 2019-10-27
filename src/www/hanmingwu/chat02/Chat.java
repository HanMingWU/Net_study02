package www.hanmingwu.chat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat {
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(8888);
        //阻塞式接受
        Socket client =server.accept();
        System.out.println("一个客户端建立了连接");
        //接受信息
        DataInputStream dis=new DataInputStream(client.getInputStream());
        String msg=dis.readUTF();
        System.out.println(msg);
        //返回消息
        DataOutputStream dos=new DataOutputStream(client.getOutputStream());
        dos.writeUTF(msg);

        dis.close();
        dos.close();
        client.close();
    }
}
