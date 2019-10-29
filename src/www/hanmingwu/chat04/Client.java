package www.hanmingwu.chat04;
/**
 * 多次消息，多个用户正常收发消息
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("----client----");
        BufferedReader str=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名：");
        String name=str.readLine();

        //建立连接
        Socket client = new Socket("localhost", 8888);
        //客户端发送消息
        new Thread(new Send(client,name)).start();
        new Thread(new Receive(client)).start();
    }
}