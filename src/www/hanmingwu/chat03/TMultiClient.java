package www.hanmingwu.chat03;
/**
 * 多次消息，多个用户正常收发消息
 */

import java.io.*;
import java.net.Socket;

public class TMultiClient {
    public static void main(String[] args) throws IOException {
        System.out.println("----client----");
        //建立连接
        Socket client = new Socket("localhost", 8888);
        //客户端发送消息
        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();
    }
}