package www.hanmingwu.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 创建客户端
 * 1.建立连接：使用Socket创建客户端+服务地址和端口
 * 2.操作：输入输出流操作
 * 3释放资源
 *
 */

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("--client---");
        //请求连接
        Socket client=new Socket("localhost",8888);
        //操作,创建了输出流对象
        DataOutputStream dos=new DataOutputStream(client.getOutputStream());

        String data="hello";
        dos.writeUTF(data);
        dos.flush();
        dos.close();

        client.close();





    }
}
