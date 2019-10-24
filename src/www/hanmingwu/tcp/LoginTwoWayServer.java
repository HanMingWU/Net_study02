package www.hanmingwu.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
public class LoginTwoWayServer {
    public static void main(String[] args) throws IOException {
        System.out.println("---server---");
        ServerSocket server=new ServerSocket(8888);
        //阻塞式接受
        Socket client= server.accept();
        System.out.println("一个客户端建立了连接");
        String uname="";
        String upwd="";

        //操作
        DataInputStream dis=new DataInputStream(client.getInputStream());
        String data =  dis.readUTF();
        //分析
        String[] dataArray=data.split("&");
        for(String info:dataArray){
            String[] useInfo=info.split("=");
            if(useInfo[0].equals("uname")){
                System.out.println("你的用户名："+useInfo[1]);
                uname=useInfo[1];

            }else if(useInfo[0].equals("upwd")){
                System.out.println("你的密码："+useInfo[1]);
                upwd =useInfo[1];
            }
        }
    //输出
        DataOutputStream dos=new DataOutputStream(client.getOutputStream());
        if(uname.equals("hanming") && upwd.equals("danting")){
            dos.writeUTF("欢迎回来");

        }else {
            dos.writeUTF("密码或用户名错误！");
        }
        dos.flush();

        dis.close();

        client.close();
        server.close();
    }
}
