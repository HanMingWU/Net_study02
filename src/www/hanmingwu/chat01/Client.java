package www.hanmingwu.chat01;
/**
 * 一次消息
 */

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("----client----");
        //建立连接
        Socket client =new Socket("localhost",8888);
        //客户端发送消息
        BufferedReader console=new BufferedReader(new InputStreamReader(System.in));

        String msg=console.readLine();
        DataOutputStream dos=new DataOutputStream(client.getOutputStream());
        dos.writeUTF(msg);


        //接受信息
        DataInputStream dis=new DataInputStream(client.getInputStream());
        String msgback=dis.readUTF();
        System.out.println(msgback);

        dis.close();
        dos.close();
        client.close();

    }
}
