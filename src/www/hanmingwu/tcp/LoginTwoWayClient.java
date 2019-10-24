package www.hanmingwu.tcp;

import java.io.*;
import java.net.Socket;

public class LoginTwoWayClient {
    public static void main(String[] args) throws IOException {
        System.out.println("client");
        BufferedReader console =new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入用户名：");
        String uname=console.readLine();
        System.out.println("输入密码");
        String upwd=console.readLine();

        //建立连接，用socket
        Socket client=new Socket("localhost",8888);
        //操作,创建了输出流对象
        DataOutputStream dos=new DataOutputStream(client.getOutputStream());
        dos.writeUTF("uname="+uname+"&"+"upwd="+upwd);
        dos.flush();
        //接受错误信息
        DataInputStream dis=new DataInputStream(client.getInputStream());
        String result=dis.readUTF();
        System.out.println(result);
        dis.close();


        dos.close();

        client.close();


    }
}
