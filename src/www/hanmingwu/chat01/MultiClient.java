package www.hanmingwu.chat01;
/**
 * 多次消息
 */

import java.io.*;
import java.net.Socket;

public class MultiClient {
    public static void main(String[] args) throws IOException {
        System.out.println("----client----");
        //建立连接
        Socket client =new Socket("localhost",8888);
        //客户端发送消息
        BufferedReader console=new BufferedReader(new InputStreamReader(System.in));
        //接受信息
        DataOutputStream dos=new DataOutputStream(client.getOutputStream());
        DataInputStream dis=new DataInputStream(client.getInputStream());
        //一直接受信息和发送信息
        boolean isRunning=true;
        while (isRunning){
            String msg=console.readLine();
            dos.writeUTF(msg);
            dos.flush();
            //接受消息
            String msgback=dis.readUTF();
            System.out.println(msgback);
        }






        dis.close();
        dos.close();
        client.close();

    }
}
